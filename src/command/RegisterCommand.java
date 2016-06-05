package command;

import database.dao.PersistException;
import database.dao.entity.User;
import helper.ConsoleHelpe;

import java.io.IOException;
import java.util.List;

/**
 * Created by Паша on 04.06.2016.
 */
public class RegisterCommand extends AbstractUsersCommand {

    @Override
    public String getNameProperties() {
        return "register";
    }

    @Override
    public boolean getAction(boolean flag, List<User> list, String login, String password) {
        boolean isContains = false;
        String confirm = null;
        try {

            ConsoleHelpe.writeMessage("Confirm password");
            confirm = ConsoleHelpe.readString();
        } catch (IOException e) {
            ConsoleHelpe.writeMessage(e.getMessage());
        }
        if (password.equals(confirm)) {
            for (User u : list) {
                if (login.equals(u.getLogin())) {
                    isContains = true;
                }
            }
            if (!isContains) {
                try {
                    User user = getUserDao().create();
                    user.setLogin(login);
                    user.setPassword(password);
                    getUserDao().update(user);
                    flag = true;
                } catch (PersistException e) {
                    ConsoleHelpe.writeMessage(e.getMessage());
                }
            }
        }
        return flag;
    }
}
