package command;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Паша on 04.06.2016.
 */
public final class CommandExecutor {

    private CommandExecutor(){}

    private static Map<Operation, Command> map;

    static
    {
        map = new HashMap<>();
        map.put(Operation.LOGIN, new LoginCommand());
        map.put(Operation.REGISTER, new RegisterCommand());

    }

    public static void execute(Operation operation) {
        map.get(operation).execute();
    }
}
