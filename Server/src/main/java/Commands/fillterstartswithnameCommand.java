
package Commands;

import NetWork.Response;
import Objects.ProductModel;
import Mannagers.CommandManager;
import Mannagers.Console;

public class fillterstartswithnameCommand implements Command {
    private CommandManager commandManager;
    public fillterstartswithnameCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
        Console.products.put("filter_starts_with_name", this);
    }

    @Override
    public Response execute(String command, ProductModel product) {
        return commandManager.filterStartsWithName(command, product);
    }
}