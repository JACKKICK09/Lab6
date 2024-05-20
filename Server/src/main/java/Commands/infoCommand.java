package Commands;

import NetWork.Response;
import Objects.ProductModel;
import Mannagers.CommandManager;
import Mannagers.Console;

public class infoCommand implements Command {
    private CommandManager commandManager;
    public infoCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
        Console.products.put("info", this);
    }
    @Override
    public Response execute(String command, ProductModel product) {
        return commandManager.info(command,product);
    }
}