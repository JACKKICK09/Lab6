package Commands;

import NetWork.Response;
import Objects.ProductModel;
import Mannagers.CommandManager;
import Mannagers.Console;

public class addCommand implements Command {
    private CommandManager commandManager;

    public addCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
        Console.products.put("add", this);
    }

    @Override
    public Response execute(String arguments, ProductModel objectArg) {
        return commandManager.add(arguments, objectArg);
    }
}
