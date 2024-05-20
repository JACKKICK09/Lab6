package Mannagers;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.InputStreamReader;

public class ConsoleReadThread extends Thread {
    @Override
    public void run() {
        try {
            // Создаем BufferedReader для чтения ввода с консоли
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                // Читаем ввод с консоли
                String line = reader.readLine().trim();
                if (line != null) {
                    line = line.toLowerCase();
                    // Обрабатываем ввод
                    if (line.equals("exit")) {
                        System.exit(0);
                    } else if (line.equals("save")) {
                       CommandManager.save();;
                    } else {
                        Server.logger.info("Команда,которую ввели не поддерживается сервером");
                    }
                }
            }

        }catch (java.io.IOException e) {
            if (e instanceof EOFException) {
                Server.logger.info("Ввод завершен (Ctrl+D)");
            } else if (e.getMessage().contains("Stream closed")) {
                Server.logger.info("Ввод прерван (Ctrl+C)");
            } else {
                Server.logger.info("Ошибка ввода");
            }
            System.exit(0);
        } catch (java.lang.NullPointerException E) {
            Server.logger.info("Сервер остановлен");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}