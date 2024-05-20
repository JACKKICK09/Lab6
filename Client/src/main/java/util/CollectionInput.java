package util;

import Errors.IncorrectInput;
import Objects.*;

import java.time.LocalDateTime;
import java.util.Scanner;

public class CollectionInput {



    public CollectionInput(){
    }


    public static String InputName(Scanner InputScanner) throws IncorrectInput {
        while(true) {
            try {
                var name = InputScanner.nextLine();
                if (name.isBlank()){
                    throw new IncorrectInput("Поле не может быть пустым. Введите его ещё раз: ");
                }
                return name.trim();
            }catch(IncorrectInput e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static double askX(Scanner InputScanner) {
        while(true) {
            try {
                return Double.parseDouble(InputScanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Неверный формат числа. Введите его повторно:");
            }
        }
    }

    private static long askY(Scanner InputScanner) {
        while(true) {
            try {
                return Long.parseLong(InputScanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Неверный формат числа. Введите его повторно:");
            }
        }
    }


    public static float InputPrice(Scanner InputScanner) throws IncorrectInput{
        while (true){
            try {
                float price = Float.parseFloat(InputScanner.nextLine());
                if (price > 0){
                    return price;
                } else {
                    throw new IncorrectInput("Цена не может быть меньше нуля!");
                }
            } catch (NumberFormatException e){
                System.out.println("Неверный формат числа. Введите его повторно:");
            } catch (IncorrectInput e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static long InputManufacture(Scanner InputScanner) throws IncorrectInput{
        while (true) {
            try {
                long Manufacture = Long.parseLong(InputScanner.nextLine());
                if (Manufacture > 0){
                    return Manufacture;
                } else {
                    throw new IncorrectInput("Цена не может быть меньше нуля!");
                }
            } catch (NumberFormatException e){
                System.out.println("Неверный формат числа. Введите его повторно:");
            } catch (IncorrectInput e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static UnitOfMeasure InputUnitOfMeasure(Scanner InputScanner) throws IncorrectInput {
        while (true) {
            try {
                String type = InputScanner.nextLine().toUpperCase();
                UnitOfMeasure unitOfMeasure;
                switch (type) {
                    case "CENTIMETERS":
                        unitOfMeasure = UnitOfMeasure.CENTIMETERS;
                        break;
                    case "SQUARE_METERS":
                        unitOfMeasure = UnitOfMeasure.SQUARE_METERS;
                        break;
                    case "LITERS":
                        unitOfMeasure = UnitOfMeasure.LITERS;
                        break;
                    case "GRAMS":
                        unitOfMeasure = UnitOfMeasure.GRAMS;
                        break;
                    default:
                        throw new IncorrectInput("Такого типа единиц измерения не существует. " +
                                "Заполните тип корректно: ");
                }
                return unitOfMeasure;
            } catch (IncorrectInput e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String InputPersonName(Scanner InputScanner) throws IncorrectInput{
        while(true) {
            try {
                var name = InputScanner.nextLine();
                if (name.isBlank()){
                    throw new IncorrectInput("Поле не может быть пустым. Введите его ещё раз: ");
                }
                return name.trim();
            }catch(IncorrectInput e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String InputPasspordId(Scanner InputScanner) throws IncorrectInput{
        while(true) {
            try {
                var name = InputScanner.nextLine();
                if (name.length() > 10 || name != ""){
                    return name.trim();
                } else {
                    throw new IncorrectInput("Поле не может быть пустым или содержать меньше 10 символов");
                }

            }catch(IncorrectInput e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Color1 InputColor1(Scanner InputScanner) throws IncorrectInput{
        while (true) {
            try {
                String type = InputScanner.nextLine().toUpperCase();
                Color1 color1;
                switch (type) {
                    case "GREEN":
                        color1 = Color1.GREEN;
                        break;
                    case "BLACK":
                        color1 = Color1.BLACK;
                        break;
                    case "YELLOW":
                        color1 = Color1.YELLOW;
                        break;

                    default:
                        throw new IncorrectInput("Такого цвета не существует. " +
                                "Заполните тип корректно: ");
                }
                return color1;
            } catch (IncorrectInput e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static Color2 InputColor2(Scanner InputScanner) throws IncorrectInput{
        while (true) {
            try {
                String type = InputScanner.nextLine().toUpperCase();
                Color2 color2;
                switch (type) {
                    case "GREEN":
                        color2 = Color2.GREEN;
                        break;
                    case "RED":
                        color2 = Color2.RED;
                        break;
                    case "BLUE":
                        color2 = Color2.BLUE;
                        break;
                    case "WHITE":
                        color2 = Color2.WHITE;
                        break;
                    case "BROW":
                        color2 = Color2.BROWN;
                        break;

                    default:
                        throw new IncorrectInput("Такого цвета не существует. " +
                                "Заполните тип корректно: ");
                }
                return color2;
            } catch (IncorrectInput e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Country InputCountry(Scanner InputScanner) throws IncorrectInput{
        while (true) {
            try {
                String type = InputScanner.nextLine().toUpperCase();
                Country country;
                switch (type) {
                    case "UNITED_KINGDOM":
                        country = Country.UNITED_KINGDOM;
                        break;
                    case "GERMANY":
                        country = Country.GERMANY;
                        break;
                    case "CHINA":
                        country = Country.CHINA;
                        break;
                    case "ITALY":
                        country = Country.ITALY;
                        break;
                    case "THAILAND":
                        country = Country.THAILAND;
                        break;
                    default:
                        throw new IncorrectInput("Такой страны не существует. " +
                                "Заполните тип корректно: ");
                }
                return country;
            } catch (IncorrectInput e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Person IputPerson(Scanner Input) throws IncorrectInput {
        System.out.println("Введите имя:");
        String name = InputPersonName(Input);
        System.out.println("Введите паспорт:");
        String PasspordId = InputPasspordId(Input);
        System.out.println("Выберете цвет глаз:\n GREEN\n BLACK\n YELLOW");
        Color1 eyecolor = InputColor1(Input);
        System.out.println("Выберете цвет волос:\n GREEN\n RED\n BLUE\n WHITE\n BROWN");
        Color2 haircolor = InputColor2(Input);
        System.out.println("Укажите страну:\n UNITED_KINGDOM\n GERMANY\n CHINA\n ITALY\n THAILAND");
        Country country = InputCountry(Input);


        return new Person(name,PasspordId,eyecolor, haircolor, country);
    }
    public static ProductModel askForProduct() throws IncorrectInput{
        Scanner InputScanner = PromptScan.getUserScanner();
        System.out.println("Введите название:");
        String name = InputName(InputScanner);
        System.out.println("Введите координату x:");
        double x = askX(InputScanner);
        System.out.println("Введите координату у:");
        long y = askY(InputScanner);
        Coordinates coordinates = new Coordinates(x,y);
        System.out.println("Введите цену:");
        float price = InputPrice(InputScanner);
        System.out.println("Введите цену за товар:");
        long manufactureCost = InputManufacture(InputScanner);
        System.out.println("Выберите еденицу измерения:\n CENTIMETRS\n SQUARE_METERS\n LITERS\n GRAMS");
        UnitOfMeasure unitOfMeasure = InputUnitOfMeasure(InputScanner);
//        String namePerson = InputPersonName(InputScanner);
//        String passportID = InputPasspordId(InputScanner);
//        Color1 eyeColor = InputColor1(InputScanner);
//        Color2 hairColor = InputColor2(InputScanner);
//        Country country = InputCountry(InputScanner);
        Person owner = IputPerson(InputScanner);

        return new ProductModel(name, coordinates, LocalDateTime.now(), price, manufactureCost, unitOfMeasure, owner);
    }

}
