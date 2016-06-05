package command;

import database.dao.PersistException;
import database.dao.entity.User;
import database.dao.mysql.MySqlDaoFactory;
import database.dao.mysql.MySqlUserDao;
import helper.ConsoleHelpe;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Паша on 04.06.2016.
 */
public abstract class AbstractUsersCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(getNameProperties(), Locale.ENGLISH);
    private MySqlDaoFactory factory = new MySqlDaoFactory();
    private MySqlUserDao userDao;

    public MySqlUserDao getUserDao() {
        return userDao;
    }

    public abstract String getNameProperties();
    @Override
    public void execute() {
        try {
            userDao = new MySqlUserDao(factory, factory.getContext());
            String login;
            String password;
            int counter = 0;
            boolean flag = false;

            ConsoleHelpe.writeMessage(res.getString("before"));
            ConsoleHelpe.writeMessage(res.getString("enter.login.password"));
            List<User> userList = userDao.getAll();

            while (!flag) {
                if (counter > 0) {
                    ConsoleHelpe.writeMessage(res.getString("try.again.or.exit"));
                }
                counter++;

                login = ConsoleHelpe.readString();
                password = ConsoleHelpe.readString();

                if (login.matches("[0-9_A-Za-z]{3,}") && password.matches("[0-9_A-Za-z]{6,}")) {
                    flag = getAction(false,userList,login,password);
                    if (!flag) {
                        ConsoleHelpe.writeMessage(String.format(res.getString("fail"), login));
                    }else {
                        ConsoleHelpe.writeMessage(String.format(res.getString("success.format"), login));
                    }
                }
                if (!flag) {
                    ConsoleHelpe.writeMessage(res.getString("try.again.with.details"));
                }
            }
        } catch (IOException e) {
            ConsoleHelpe.writeMessage(e.getMessage());
        } catch (PersistException e) {
            ConsoleHelpe.writeMessage(e.getMessage());
        }
    }

    public abstract boolean getAction(boolean flag,List<User> list, String login, String password);

}
