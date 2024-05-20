import Mannagers.Server;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
//        Server server = new Server(new InetSocketAddress(2001));
//        server.run(args);

        Server server = new Server(new InetSocketAddress("localhost", 8000));
        server.run(new String[]{"/Users/jackkick/IdeaProjects/Lab6Finaly/collection.csv"});
//        Строчки для теста на локалхосте. Для гелиуса достаточно указать только порт
    }
}