package command;

import database.dao.entity.User;

import java.util.List;

/**
 * Created by Паша on 04.06.2016.
 */
public class LoginCommand extends AbstractUsersCommand {


    @Override
    public String getNameProperties() {
        return "login";
    }

    @Override
    public boolean getAction(boolean flag, List<User> list, String login, String password) {
        for (User u : list) {
            if (login.equals(u.getLogin()) && password.equals(u.getPassword())) {
                flag = true;
                break;
            }
        }
        return flag;
    }


}
