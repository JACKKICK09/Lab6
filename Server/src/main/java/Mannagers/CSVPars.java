package Mannagers;

import Objects.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Vector;



public class CSVPars {
    public static Path Collection_Path;

    public CSVPars(Path Collection_Path) {
        this.Collection_Path = Collection_Path;
    }


    // Запись коллекции в CSV с использованием BufferedOutputStream
    public void write(Collection<Product> collection) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Collection_Path.toFile()));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                     .withHeader("id", "name", "x", "y", "creationDate", "price", "manufactureCost", "unitOfMeasure",
                             "owner_name", "owner_passportID", "owner_eyeColor", "owner_hairColor", "owner_nationality"))) {

            for (Product product : collection) {
                csvPrinter.printRecord(
                        product.getId(),
                        product.getName(),
                        product.getCoordinates().getX(),
                        product.getCoordinates().getY(),
                        product.getCreationDate(),
                        product.getPrice(),
                        product.getManufactureCost(),
                        product.getUnitOfMeasure(),
                        product.getOwner().getName(),
                        product.getOwner().getPassportID(),
                        product.getOwner().getEyeColor(),
                        product.getOwner().getHairColor(),
                        product.getOwner().getNationality()
                );
            }

            csvPrinter.flush();
            System.out.println("Коллекция успешно сохранена");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public Vector<Product> readFromFile() {
        Vector<Product> dataVector = new Vector<>();

        if (Collection_Path == null) {
            System.err.println("Путь к файлу коллекции не инициализирован");
            return dataVector;
        }

        try (Scanner fileReader = new Scanner(Collection_Path)) {
            if (!fileReader.hasNextLine()) {
                throw new NoSuchElementException("Файл пуст");
            }

            String headerLine = fileReader.nextLine();
            CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(headerLine.split(",")).withIgnoreSurroundingSpaces();

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                try {
                    CSVRecord record = CSVParser.parse(line, csvFormat).getRecords().get(0);

                    Coordinates coordinates = new Coordinates(
                            Double.parseDouble(record.get("x")),
                            Long.parseLong(record.get("y"))
                    );

                    Person owner = new Person(
                            record.get("owner_name"),
                            record.get("owner_passportID"),
                            Color1.valueOf(record.get("owner_eyeColor")),
                            record.isSet("owner_hairColor") ? Color2.valueOf(record.get("owner_hairColor")) : null,
                            record.isSet("owner_nationality") ? Country.valueOf(record.get("owner_nationality")) : null
                    );

                    Product product = new Product(
                            Integer.parseInt(record.get("id")),
                            record.get("name"),
                            coordinates,
                            LocalDateTime.parse(record.get("creationDate")),
                            Float.parseFloat(record.get("price")),
                            Long.parseLong(record.get("manufactureCost")),
                            UnitOfMeasure.valueOf(record.get("unitOfMeasure")),
                            owner
                    );

                    dataVector.add(product);
                } catch (IOException e) {
                    System.err.println("Ошибка при разборе строки CSV: " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.err.println("Ошибка в формате данных: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.err.println("Файл пуст");
        }

        return dataVector;
    }



    public static String[] getHeader() {
        return new String[]{"id", "name", "x", "y", "creationDate", "price", "manufactureCost", "unitOfMeasure",
                "owner_name", "owner_passportID", "owner_eyeColor", "owner_hairColor", "owner_nationality"};
    }
}
