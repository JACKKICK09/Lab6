package Mannagers;

import Errors.NonExistingElementException;
import NetWork.Response;
import Objects.Person;
import Objects.ProductModel;

import java.io.IOException;
import java.util.LinkedList;

public class CommandManager {
    private static CollectionRealizer collectionRealizer;
    private static CSVPars csvPars;
    private static LinkedList<String> commandHistory = new LinkedList<>();

    public CommandManager() {
        this.collectionRealizer = new CollectionRealizer();
        csvPars = new CSVPars(CSVPars.Collection_Path);
    }

    public Response help(String strArgument, ProductModel objArgument){
        if (!strArgument.isBlank() || objArgument != null){
            return new Response("Неверные аргументы команды", "" );

        } else {
            String message =
                    """
                    Список доступных команд:\n
    
                    help - справка по командам
                    info - вывод данных о коллекции (тип, дата инициализации, количество элементов)
                    show - вывести все элементы коллекции
                    add <el> - добавить элемент в коллекцию
                    update <id> <el> - обновить id элемента на заданный
                    remove_by_idd <id> - удалить элемент по id
                    clear - очистить всю коллекцию
                    executeScript <path> - исполнить скрипт
                    exit - завершить работу клиентского приложения
                    remove_lower <id> - удалить из коллекции все элементы, меньше данного по id
                    remove_at <id> - удалить элемент по текущей позиции
                    history - вывести последние 6 команд
                    print_field_descending_unit_of_measure - вывести значения поля unitOfMeasure
                    всех элементов в порядке убывания
                    count_less_than_owner - вывести количество элементов коллекции,название которых ниже заданного
                    filter_starts_with_name <name> - вывести все элементы, имя которых начинается с заданной подстроки
                    ================================================================================================
                    """
                    ;
            return new Response(message, "");
        }
    }


    public Response info(String strArgument, ProductModel objArgument){
        if (!strArgument.isBlank() || objArgument != null){
            return new Response("Неверные аргументы команды", "" );

        } else {
            var message = collectionRealizer.info();
            return new Response(message, "");
        }
    }

    public Response show(String strArgument, ProductModel objArgument){
        if (!strArgument.isBlank() || objArgument != null){
            return new Response("Неверные аргументы команды", "" );

        } else {
            var collection = collectionRealizer.show();

            if (collection.isEmpty()){
                return new Response("В коллекции пока нету ни одного элемента", "");
            } else{
                return new Response("Коллекция успешно распечатана", collection.toString());
            }
        }
    }

    public Response add(String strArgument, ProductModel objArgument){
        if (!strArgument.isBlank() && objArgument == null){
            return new Response("Неверные аргументы команды", "" );

        } else {
            var collection = collectionRealizer.add(objArgument);
            return new Response("Элемент успешно добавлен", collection.toString());
        }
    }

    public Response update(String strArgument, ProductModel objArgument){
        if (strArgument.isBlank() && objArgument == null){
            return new Response("Неверные аргументы команды", "" );

        } else {
            try {
                int current_id = Integer.parseInt(strArgument);

                if (current_id > 0){
                    var collection = collectionRealizer.update(current_id, objArgument);
                    return new Response("элемент c id " + current_id + " успешно обновлён", collection.toString());

                } else {
                    return new Response("id не может быть отрицательным", "" );
                }

            } catch (NumberFormatException e){
                return new Response("Неверный формат аргументов", "" );
            } catch (NonExistingElementException e) {
                return new Response(e.getMessage(), "");
            }
        }
    }

    public Response removeById(String strArgument, ProductModel objArgument){
        if (strArgument.isBlank() || objArgument != null){
            return new Response("Неверные аргументы команды", "" );

        } else {
            try {
                int id = Integer.parseInt(strArgument);

                if (id > 0){
                    var collection = collectionRealizer.removeById(id);
                    return new Response("Элемент с id " + id + " успешно удалён", collection.toString());
                } else {
                    return new Response("id не может быть отрицательным", "" );
                }

            } catch (NumberFormatException e){
                return new Response("Неверный формат аргументов", "" );
            } catch (NonExistingElementException e) {
                return new Response(e.getMessage(), "");
            }
        }
    }

    public Response clear(String strArgument, ProductModel objArgument){
        if (!strArgument.isBlank() || objArgument != null){
            return new Response("Неверные аргументы команды", "" );

        } else {
            var collection = collectionRealizer.clear();
            return new Response("коллекция успешно очищена", collection.toString());
        }
    }
    public static void load(CSVPars csvPars){
        CollectionRealizer.collection = csvPars.readFromFile();
    }
    public static void save() throws IOException {
        csvPars.write(CollectionRealizer.collection);
    }

    public Response removeLower(String strArgument, ProductModel objArgument){
        if (strArgument.isBlank() || objArgument != null){
            return new Response("Неверные аргументы команды", "" );

        } else {
            try {
                int startId = Integer.parseInt(strArgument) + 1;

                if (startId < 0) {
                    var collection = collectionRealizer.removeLower(startId);
                    return new Response("элементы успешно удалены", collection.toString());

                } else {
                    return new Response("id не может быть отрицательным", "");
                }

            } catch (NumberFormatException e){
                return new Response("Неверный формат аргументов", "" );
            } catch (NonExistingElementException e) {
                return new Response(e.getMessage(), "");
            }
        }
    }

    public Response removeAt(String strArgument, ProductModel objArgument) {
        if (strArgument.isBlank() || objArgument != null) {
            return new Response("Неверные аргументы команды", "");
        } else {
            try {
                int id = Integer.parseInt(strArgument);
                if (id > 0) {
                    var removedProduct = collectionRealizer.removeAt(id);
                    if (removedProduct != null) {
                        return new Response("Продукт с id " + id + " успешно удален", "");
                    } else {
                        return new Response("Продукт с id " + id + " не найден", "");
                    }
                } else {
                    return new Response("Id продукта должен быть положительным числом", "");
                }
            } catch (NumberFormatException e) {
                return new Response("Неверный формат id продукта", "");
            } catch (IllegalArgumentException e) {
                return new Response(e.getMessage(), "");
            }
        }
    }



    public Response history(String strArgument, ProductModel objArgument){
        StringBuilder historyList = new StringBuilder();

        if (!strArgument.isBlank() || objArgument != null){
            return new Response("Неверные аргументы команды", "" );

        } else {
            for (String command : commandHistory) {
                historyList.append(command).append("\n");
            }
        }
        return new Response("Последние 7 команд, введённые пользователем: \n" + historyList, "" );
    }

    public Response printFieldDescendingUnitOfMeasure(String strArgument, ProductModel objArgument) {
        if (!strArgument.isBlank() || objArgument != null){
            return new Response("Неверные аргументы команды", "" );

        } else {
            var collection = collectionRealizer.printFieldDescendingUnitOfMeasure();

            if (collection.isEmpty()){
                return new Response("В коллекции пока нету ни одного элемента", "");
            } else{
                return new Response("Коллекция успешно распечатана", collection.toString());
            }
        }
    }




    public Response countLessThanOwner(String strArgument, ProductModel objArgument) {
        if (strArgument.isBlank() || objArgument != null) {
            return new Response("Неверные аргументы команды", "");
        } else {
            try {
                Person owner = objArgument.getOwner();
                if (owner != null) {
                    long count = collectionRealizer.countLessThanOwner(owner);
                    return new Response("Количество продуктов с владельцами, чьи имена алфавитно раньше, чем у указанного владельца", String.valueOf(count));
                } else {
                    return new Response("Указан некорректный владелец", "");
                }
            } catch (NumberFormatException e) {
                return new Response("Неверный формат аргумента", "");
            }
        }
    }


    public Response filterStartsWithName(String strArgument, ProductModel objArgument){
        if (strArgument.isBlank() || objArgument != null){
            return new Response("Неверные аргументы команды", "" );

        } else {
            try {
                var collection = collectionRealizer.filterStartsWithName(strArgument);
                return new Response("Коллекция отсортирована по именам ТС", collection.toString());

            } catch (NonExistingElementException e) {
                return new Response(e.getMessage(), "");
            }

        }
    }


    public static void addCommand(String command){
        if (commandHistory.size() == 6){
            commandHistory.removeFirst();
        }
        commandHistory.addLast(command);
    }
}