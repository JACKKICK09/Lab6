package Commands;

import NetWork.Response;
import Objects.ProductModel;
import Mannagers.CommandManager;
import Mannagers.Console;

public class remove_by_idCommand implements Command {
    private CommandManager commandManager;
    public remove_by_idCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
        Console.products.put("remove_by_id", this);
    }

    @Override
    public Response execute(String command, ProductModel product) {
        return commandManager.removeById(command,product);
    }
}