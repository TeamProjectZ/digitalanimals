package database.dao.domain;

import database.dao.PersistException;
import database.dao.entity.Student;
import database.dao.mysql.MySqlDaoFactory;
import database.dao.mysql.MySqlGroupDao;
import database.dao.mysql.MySqlStudentDao;
import helper.ConsoleHelpe;

import java.util.List;

/**
 * Created by Паша on 28.05.2016.
 */
public class Main {

    public static void main(String[] args) throws PersistException {
        MySqlDaoFactory factory = new MySqlDaoFactory();
        MySqlStudentDao studentDao = new MySqlStudentDao(factory, factory.getContext());
        MySqlGroupDao groupDao = new MySqlGroupDao(factory, factory.getContext());
        List<Student> list = studentDao.getAll();
        for (Student student : list) {
            ConsoleHelpe.writeMessage(student.toString());
        }
    }
}
