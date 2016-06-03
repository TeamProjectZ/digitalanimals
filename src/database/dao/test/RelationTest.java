package database.dao.test;

import database.dao.DaoFactory;
import database.dao.PersistException;
import database.dao.entity.User;
import database.dao.entity.Zoo;
import database.dao.mysql.MySqlDaoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class RelationTest {
    private static final DaoFactory<Connection> factory = new MySqlDaoFactory();

    private Connection connection;

    @Before
    public void setUp() throws PersistException, SQLException {
        connection = factory.getContext();
        connection.setAutoCommit(false);
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connection.close();
    }

    @Test
    public void testCreate() throws PersistException {
        Zoo zoo = (Zoo) factory.getDao(connection, Zoo.class).create();
        Assert.assertNull("Group is not null.", zoo.getUser());

        User user = new User();
        zoo.setUser(user);
        Assert.assertNotNull("Group is null.", zoo.getUser());
    }

    @Test
    public void testPersist() throws PersistException {
        Zoo zoo = new Zoo();
        User user = (User) factory.getDao(connection, User.class).create();
        zoo.setUser(user);
        user.setLogin("csipon");
        zoo = (Zoo) factory.getDao(connection, Zoo.class).persist(zoo);
        Assert.assertNotNull("Group is null.", zoo.getUser());
        Assert.assertEquals("Wrong group number.", "csipon", zoo.getUser().getLogin());
    }

    @Test
    public void testPersistAll() throws PersistException {
        Zoo zoo = new Zoo();
        zoo.setUser(new User());
        zoo = (Zoo) factory.getDao(connection, Zoo.class).persist(zoo);
        Assert.assertNotNull("Group is null.", zoo.getUser());
        Assert.assertNotNull("Group.id is null.", zoo.getUser().getId());
    }

//    @Test
//    public void testUpdate() throws PersistException {
//        Student student = (Student) factory.getDao(connection, Student.class).create();
//        student.setGroup(new Group());
//        factory.getDao(connection, Student.class).update(student);
//        Assert.assertNotNull("Group is null.", student.getGroup());
//        Assert.assertNotNull("Group.id is null.", student.getGroup().getId());
//    }

    @Test
    public void testUpdateAll() throws PersistException {
        Zoo zoo = (Zoo) factory.getDao(connection, Zoo.class).create();
        User user = (User) factory.getDao(connection, User.class).create();
        user.setLogin("csipon");
        zoo.setUser(user);
        factory.getDao(connection, Zoo.class).update(zoo);
        Assert.assertNotNull("Group is null.", zoo.getUser());
        Assert.assertEquals("Wrong group number.", "csipon", zoo.getUser().getLogin());
    }

    @Test
    public void testRead() throws PersistException {
        Zoo zoo = (Zoo) factory.getDao(connection, Zoo.class).create();
        zoo.setUser(new User());
        factory.getDao(connection, Zoo.class).update(zoo);

        zoo = (Zoo) factory.getDao(connection, Zoo.class).getByPK(zoo.getId());
        Assert.assertNotNull("Student is null.", zoo);
        Assert.assertNotNull("Group is null.", zoo.getUser());
    }

    @Test
    public void testDelete() throws PersistException {
        Zoo zoo = (Zoo) factory.getDao(connection, Zoo.class).create();
        zoo.setUser(new User());
        factory.getDao(connection, Zoo.class).update(zoo);

        User user = zoo.getUser();

        factory.getDao(connection, Zoo.class).delete(zoo);
        user = (User) factory.getDao(connection, User.class).getByPK(user.getId());
        Assert.assertNotNull("Group not found.", user);
    }
}
