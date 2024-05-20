
package Commands;

import NetWork.Response;
import Objects.ProductModel;
import Mannagers.CommandManager;
import Mannagers.Console;

public class helpCommand implements Command {
    private CommandManager commandManager;
    public helpCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
        Console.products.put("help", this);
    }

    @Override
    public Response execute(String command, ProductModel product) {
        return commandManager.help(command, product);
    }
}