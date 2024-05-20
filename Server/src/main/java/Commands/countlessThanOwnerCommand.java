package Commands;

import NetWork.Response;
import Objects.ProductModel;
import Mannagers.CommandManager;
import Mannagers.Console;

public class countlessThanOwnerCommand implements Command {
    public CommandManager commandManager;

    public countlessThanOwnerCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
        Console.products.put("count_less_than_owner", this);
    }

    @Override
    public Response execute(String arg, ProductModel obj){
        return commandManager.countLessThanOwner(arg, obj);
    }
}


