package util;

import Errors.IncorrectInput;
import Errors.ScriptRecursionException;
import NetWork.Request;
import NetWork.Response;
import Objects.ProductModel;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Client {
    private Set<Path> scriptsNames = new TreeSet<>();
    private InetAddress host;
    private int port;
    private SocketChannel channel;

    public Client(InetAddress host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run(){
        try {
            SocketAddress address = new InetSocketAddress(host, port);
            channel = SocketChannel.open();
            channel.connect(address);

            PromptScan.setUserScanner(new Scanner(System.in));
            var scanner = PromptScan.getUserScanner();

            System.out.println("Это крутое консольное приложение запущенно специально для пацанов");


            while (true) {
                System.out.print("> ");
                try {
                    do {
                        var command = "";
                        var arguments = "";
                        String[] input = (scanner.nextLine() + " ").trim().split(" ", 2);
                        if (input.length == 2) {
                            arguments = input[1].trim();
                        }
                        command = input[0].trim();

                        processUserPrompt(command, arguments);
                        System.out.print("> ");
                    } while (scanner.hasNext());

                } catch (NoSuchElementException e) {
                    System.out.println("Остановка клиента через консоль");
                    System.exit(1);
                } catch (ClassNotFoundException e) {
                    System.out.println("Объект поступивший в ответ от сервера не найден");
                } catch (SocketException e){
                    System.out.println("Сервер был остановлен во время обработки вашего запроса. Пожалуйста, повторите попытку позже");
                    System.exit(1);
                }
            }

        } catch (ConnectException e){
            System.out.println("Сервер недоступен в данный момент. Пожалуйста, повторите попытку позже");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка ввода/вывода");
        }
    }


    private void processUserPrompt(String command, String arguments) throws IOException, ClassNotFoundException {
        Request request;
        Response response;
        if (command.equalsIgnoreCase("add") || command.equalsIgnoreCase("update")) {
            ProductModel objArgument = null;
            try {
                objArgument = CollectionInput.askForProduct();
            } catch (IncorrectInput e) {
                throw new RuntimeException(e);
            }
            request = new Request(command, arguments, objArgument);
            response = sendAndReceive(request);
            printResponse(response);
        } else if (command.equalsIgnoreCase("exit")) {
            System.out.println("Работа клиентского приложения завершена");
            System.exit(0);
        } else if (command.equalsIgnoreCase("executeScript")) {
            executeScript(arguments);
        } else {
            request = new Request(command, arguments);
            response = sendAndReceive(request);
            printResponse(response);
        }
    }

    private Response sendAndReceive(Request request) throws IOException, ClassNotFoundException {
        // Сериализация и отправка запроса
        try (ByteArrayOutputStream bytes = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bytes)) {
            out.writeObject(request);
            out.flush();
            ByteBuffer dataToSend = ByteBuffer.wrap(bytes.toByteArray());
            channel.write(dataToSend); // отправляем серверу запрос
        }

        // Чтение длины ответа
        ByteBuffer dataToReceiveLength = ByteBuffer.allocate(4);
        channel.read(dataToReceiveLength); // читаем длину ответа от сервера
        dataToReceiveLength.flip();
        int responseLength = dataToReceiveLength.getInt(); // достаём её из буфера

        // Чтение самого ответа
        ByteBuffer responseBytes = ByteBuffer.allocate(responseLength);
        while (responseBytes.hasRemaining()) {
            channel.read(responseBytes);
        }
        responseBytes.flip();

        // Чтение stop-пакета
        ByteBuffer stopPacket = ByteBuffer.allocate(2);
        channel.read(stopPacket);
        stopPacket.flip();
        if (stopPacket.get(0) != 28 || stopPacket.get(1) != 28) {
            throw new IOException("Stop packet not received correctly");
        }

        // Десериализация ответа
        try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(responseBytes.array()))) {
            return (Response) in.readObject();
        }
    }

    private void printResponse(Response response) {
        System.out.println(response.getMessege());
        String collection = response.getGetCollection();
        if (collection != null && !collection.isEmpty()) {
            System.out.println(collection);
        }
    }



    private void executeScript(String path) throws ClassNotFoundException {
        if (path.isBlank()){
            System.out.println("Неверные аргументы команды");

        } else {
            try {
                Path pathToScript = Paths.get(path);

                PromptScan.setUserScanner(new Scanner(pathToScript));
                Scanner scriptScanner = PromptScan.getUserScanner();

                Path scriptFile = pathToScript.getFileName();

                if (!scriptScanner.hasNext()) throw new NoSuchElementException();

                scriptsNames.add(scriptFile);

                do {
                    var command = "";
                    var arguments = "";
                    String[] input = (scriptScanner.nextLine() + " ").trim().split(" ", 2);
                    if (input.length == 2){
                        arguments = input[1].trim();
                    }
                    command = input[0].trim();

                    while (scriptScanner.hasNextLine() && command.isEmpty()){
                        input = (scriptScanner.nextLine() + " ").trim().split(" ", 2);
                        if (input.length == 2){
                            arguments = input[1].trim();
                        }
                        command = input[0].trim();
                    }

                    if (command.equalsIgnoreCase("executeScript")) {
                        try {
                            Path scriptNameFromArgument = Paths.get(arguments).getFileName();

                            if (scriptsNames.contains(scriptNameFromArgument)) {
                                throw new ScriptRecursionException("Один и тот же скрипт не может выполнятся рекурсивно");
                            }
                            executeScript(arguments);

                        } catch (ScriptRecursionException e) {
                            System.out.println(e.getMessage());
                        }

                    }
                    else {
                        processUserPrompt(command, arguments);
                    }

                } while (scriptScanner.hasNextLine());

                scriptsNames.remove(scriptFile);
                PromptScan.setUserScanner(new Scanner(System.in));
                System.out.println("Скрипт " + scriptFile + " успешно выполнен");

            } catch (FileNotFoundException e){
                System.out.println("Файл " + path + " не найден");
            } catch (NoSuchElementException e){
                System.out.println("Файл " + path + " пуст");
            } catch (IllegalStateException e){
                System.out.println("Непредвиденная ошибка");
            } catch (SecurityException e){
                System.out.println("Недостаточно прав для чтения файла " + path);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Ошибка ввода/вывода");
            } catch (InvalidPathException e){
                System.out.println("Проверьте путь к файлу. В нём не должно быть лишних символов");
            }
        }
    }
    }
