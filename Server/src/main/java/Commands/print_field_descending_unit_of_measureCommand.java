package Commands;

import NetWork.Response;
import Objects.ProductModel;
import Mannagers.CommandManager;
import Mannagers.Console;

public class print_field_descending_unit_of_measureCommand implements Command {
    private CommandManager commandManager;
    public print_field_descending_unit_of_measureCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
        Console.products.put("print_field_descending_unit_of_measure", this);
    }

    @Override
    public Response execute(String command, ProductModel product){
        return commandManager.printFieldDescendingUnitOfMeasure(command, product);
    }
}