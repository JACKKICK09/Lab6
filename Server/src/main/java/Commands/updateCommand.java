package Commands;

import NetWork.Response;
import Objects.ProductModel;
import Mannagers.CommandManager;
import Mannagers.Console;

public class updateCommand implements Command {
    private CommandManager commandManager;
    public updateCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
        Console.products.put("update", this);
    }

    @Override
    public Response execute(String command, ProductModel product) {
        return commandManager.update(command,product);
    }
}