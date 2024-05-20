package Commands;

import NetWork.Response;
import Objects.ProductModel;
import Mannagers.CommandManager;
import Mannagers.Console;

public class showCommand implements Command {
    private CommandManager commandManager;
    public showCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
        Console.products.put("show", this);
    }
    @Override
    public Response execute(String command, ProductModel product) {
        return commandManager.show(command,product);
    }
}