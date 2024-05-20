package Commands;

import NetWork.Response;
import Objects.ProductModel;
import Mannagers.CommandManager;
import Mannagers.Console;

public class remove_lowerCommand implements Command{
    private CommandManager commandManager;
    public remove_lowerCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
        Console.products.put("remove_lower_command", this);
    }
    @Override
    public Response execute(String command, ProductModel product) {
        return commandManager.removeLower(command,product);
    }
}