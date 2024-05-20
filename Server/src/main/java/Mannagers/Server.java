package Mannagers;

import Commands.*;
import NetWork.Request;
import NetWork.Response;
import Objects.ProductModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Server {
    private InetSocketAddress address;
    private Selector selector;
    private Console console;
    private Response response;
    private Request request;
    private static Logger logger;
//    private static CollectionRealizer collectionRealizer;

    public Server(InetSocketAddress address) {
        this.address = address;
        this.console = createConsoleApp();
        logger = LoggerFactory.getLogger(Server.class);
    }

    public void run(String[] args) throws IOException {
        try {
            var pathToCollection = args[0]; //"collection.csv"
            CSVPars csvProvider = new CSVPars(Path.of(pathToCollection));
            CommandManager.load(csvProvider);
            logger.info("Коллекция загружена");

            selector = Selector.open();
            logger.info("Селектор открыт");
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.bind(address);
            serverChannel.configureBlocking(false);
            logger.info("Канал сервера готов к работе");
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                selector.select();
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                logger.info("Итератор по ключам селектора успешно получен");


                while (keys.hasNext()){
                    SelectionKey key = keys.next();
                    logger.info("Обработка ключа началась");

                    try {
                        if (key.isValid()){
                            if (key.isAcceptable()){
                                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                                SocketChannel clientChannel = serverSocketChannel.accept();
                                logger.info("Установлено соединение с клиентом " + clientChannel.socket().toString());
                                clientChannel.configureBlocking(false);
                                clientChannel.register(selector,SelectionKey.OP_READ);
                            }

                            if (key.isReadable()) {
                                SocketChannel clientChannel = (SocketChannel) key.channel();
                                clientChannel.configureBlocking(false);

                                ByteBuffer clientData = ByteBuffer.allocate(2048);
                                int bytesRead = clientChannel.read(clientData);
                                logger.info(bytesRead + " байт пришло от клиента");

                                if (bytesRead == -1) {
                                    key.cancel();
                                    clientChannel.close();
                                    return;
                                }

                                clientData.flip(); // Сброс позиции буфера перед чтением
                                try (ObjectInputStream clientDataIn = new ObjectInputStream(new ByteArrayInputStream(clientData.array(), 0, clientData.limit()))) {
                                    request = (Request) clientDataIn.readObject();
                                } catch (StreamCorruptedException e) {
                                    key.cancel();
                                    return;
                                }

                                var commandName = request.getCommandName();
                                var commandStrArg = request.getCommandStrArg();
                                var commandObjArg = (ProductModel) request.getCommandObjArg();


                                if (Console.products.containsKey(commandName)) {
                                    response = Console.products.get(commandName).execute(commandStrArg, commandObjArg);
                                    CommandManager.addCommand(commandName);
                                } else {
                                    response = new Response("Команда не найдена. Используйте help для справки", "");
                                }

                                logger.info("Запрос:\n" + commandName + "\n" + commandStrArg + "\n" + commandObjArg + "\nУспешно обработан");
                                clientChannel.register(selector, SelectionKey.OP_WRITE);
                            }

                            if (key.isWritable()) {
                                SocketChannel clientChannel = (SocketChannel) key.channel();
                                clientChannel.configureBlocking(false);

                                try (ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                                     ObjectOutputStream clientDataOut = new ObjectOutputStream(bytes)) {
                                    clientDataOut.writeObject(response);

                                    byte[] byteResponse = bytes.toByteArray();

                                    // Отправка длины сообщения
                                    ByteBuffer dataLength = ByteBuffer.allocate(4).putInt(byteResponse.length);
                                    dataLength.flip();
                                    clientChannel.write(dataLength);
                                    logger.info("Отправлен пакет с длиной сообщения");

                                    // Отправка данных по частям
                                    int offset = 0;
                                    while (offset < byteResponse.length) {
                                        int length = Math.min(256, byteResponse.length - offset);
                                        ByteBuffer packet = ByteBuffer.wrap(byteResponse, offset, length);
                                        clientChannel.write(packet);
                                        offset += length;
                                        logger.info("Отправлен пакет байтов длины: " + length);
                                    }

                                    // Отправка стоп-пакета
                                    ByteBuffer stopPacket = ByteBuffer.wrap(new byte[]{28, 28});
                                    clientChannel.write(stopPacket);
                                    logger.info("Отправлен стоп пакет\n");
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }

                                clientChannel.register(selector, SelectionKey.OP_READ);
                            }
                        }
                    } catch (SocketException | CancelledKeyException e){
                        logger.info("Клиент " + key.channel().toString() + " отключился");
                        CommandManager.save();
                        key.cancel();
                    }
                    keys.remove();
                }
            }
        } catch (ArrayIndexOutOfBoundsException ignored){
        } catch (NoSuchElementException e) {
            logger.error("Остановка сервера через консоль");
            CommandManager.save();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Ошибка ввода/вывода");
        } catch (ClassNotFoundException e) {
            logger.error("Несоответствующие классы" + e.getStackTrace());
        }
    }



    private Console createConsoleApp() {
        CommandManager commandManager = new CommandManager();
        return new Console(
                new helpCommand(commandManager),
                new infoCommand(commandManager),
                new showCommand(commandManager),
                new addCommand(commandManager),
                new updateCommand(commandManager),
                new remove_by_idCommand(commandManager),
                new clearCommand(commandManager),
                new remove_lowerCommand(commandManager),
                new remove_atCommand(commandManager),
                new historyCommand(commandManager),
                new print_field_descending_unit_of_measureCommand(commandManager),
                new countlessThanOwnerCommand(commandManager),
                new fillterstartswithnameCommand(commandManager)
        );
    }

}
