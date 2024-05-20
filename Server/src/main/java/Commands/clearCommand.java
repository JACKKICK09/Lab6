package Commands;


import NetWork.Response;
import Objects.ProductModel;
import Mannagers.CommandManager;
import Mannagers.Console;

public class clearCommand implements Command {
    private CommandManager commandManager;
    public clearCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
        Console.products.put("clear", this);
    }

    @Override
    public Response execute(String arg, ProductModel obj){
        return commandManager.clear(arg,obj);
    }
}
