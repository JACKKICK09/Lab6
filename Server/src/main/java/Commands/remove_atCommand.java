package Commands;

import NetWork.Response;
import Objects.ProductModel;
import Mannagers.CommandManager;
import Mannagers.Console;

public class remove_atCommand implements Command {
    private CommandManager commandManager;
    public remove_atCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
        Console.products.put("remove_at", this);
    }
    @Override
    public Response execute(String command, ProductModel product) {
        return commandManager.removeAt(command,product);
    }
}