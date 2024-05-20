
package Commands;

import NetWork.Response;
import Objects.ProductModel;
import Mannagers.CommandManager;
import Mannagers.Console;

public class historyCommand implements Command {
    private CommandManager commandManager;
    public historyCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
        Console.products.put("history", this);
    }
    @Override
    public Response execute(String command, ProductModel product) {
        return commandManager.history(command, product);
    }
}