package database.dao.mysql;

import database.dao.DaoFactory;
import database.dao.GenericDao;
import database.dao.PersistException;
import database.dao.entity.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MySqlDaoFactory implements DaoFactory<Connection> {

    private static final String NAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/project_z";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private Map<Class, DaoFactory.DaoCreator> creators;

    public Connection getContext() throws PersistException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return  connection;
    }

    @Override
    public GenericDao getDao(Connection connection, Class dtoClass) throws PersistException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
    }

    public MySqlDaoFactory() {
        try {
            Class.forName(DRIVER);//Регистрируем драйвер
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        creators = new HashMap<Class, DaoCreator>();
        creators.put(Group.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlGroupDao(MySqlDaoFactory.this, connection);
            }
        });
        creators.put(Student.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlStudentDao(MySqlDaoFactory.this, connection);
            }
        });
        creators.put(User.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlUserDao(MySqlDaoFactory.this, connection);
            }
        });
        creators.put(Zoo.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlZooDao(MySqlDaoFactory.this, connection);
            }
        });
        creators.put(Animal.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlAnimalDao(MySqlDaoFactory.this, connection);
            }
        });
        creators.put(Type.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlTypeDao(MySqlDaoFactory.this, connection);
            }
        });
        creators.put(Food.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlFoodDao(MySqlDaoFactory.this, connection);
            }
        });
        creators.put(FoodStore.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlFoodStoreDao(MySqlDaoFactory.this, connection);
            }
        });


    }
}
