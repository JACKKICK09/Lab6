package Mannagers;

import NetWork.Response;
import Objects.ProductModel;
import Commands.Command;

import java.util.HashMap;

public class Console {
    private final int History_size = 6;
    public static HashMap<String, Command> products = new HashMap<>();
    private Command help;
    private Command info;
    private Command show;
    private Command add;
    private Command update;
    private Command remove_by_id;
    private Command clear;
    private Command removeAt;
    private Command removeLower;
    private Command history;
    private Command countlessThanOwner;
    private Command filterstarts;
    private Command print_field;

    public Console(Command help,Command Info, Command show, Command add,
                   Command update, Command remove_by_id, Command removeAt, Command clear,
                   Command removeLower, Command history, Command countlessThanOwner, Command filterstarts, Command print_field) {
        this.help = help;
        this.info = Info;
        this.show = show;
        this.add = add;
        this.update = update;
        this.remove_by_id = remove_by_id;
        this.clear = clear;
        this.removeAt = removeAt;
        this.removeLower = removeLower;
        this.history = history;
        this.countlessThanOwner = countlessThanOwner;
        this.filterstarts = filterstarts;
        this.print_field = print_field;

    }

    public Response help(String argument, ProductModel object) {
        return help.execute(argument, object);
    }

    public Response info(String argument, ProductModel object) {
        return info.execute(argument, object);
    }

    public Response show(String argument, ProductModel object) {
        return show.execute(argument, object);
    }

    public Response add(String argument, ProductModel object) {
        return add.execute(argument, object);
    }

    public Response update(String argument, ProductModel object) {
        return update.execute(argument, object);
    }

    public Response remove_by_id(String argument, ProductModel object) {
        return remove_by_id.execute(argument, object);
    }

    public Response clear(String argument, ProductModel object) {
        return clear.execute(argument, object);
    }

    public Response removeAt(String argument, ProductModel object) {
        return removeAt.execute(argument, object);
    }

    public Response removeLower(String argument, ProductModel object) {
        return removeLower.execute(argument, object);
    }

    public Response history(String argument, ProductModel object) {
        return history.execute(argument, object);
    }

    public Response countlessThanOwner(String argument, ProductModel object) {
        return countlessThanOwner.execute(argument, object);
    }

    public Response filterstarts(String argument, ProductModel object) {
        return  filterstarts.execute(argument,object);
    }

    public Response print_field(String argument, ProductModel object) {
        return print_field.execute(argument, object);
    }


}
