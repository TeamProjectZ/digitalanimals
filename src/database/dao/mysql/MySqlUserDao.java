package database.dao.mysql;

import database.dao.AbstractJDBCDao;
import database.dao.DaoFactory;
import database.dao.PersistException;
import database.dao.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class MySqlUserDao extends AbstractJDBCDao<User, Integer> {
    private class PersistUser extends User {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM project_z.users";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE project_z.users SET login = ?, password = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM project_z.users WHERE id = ?";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO project_z.users (login, password) VALUES(?, ?)";
    }

    @Override
    public User create() throws PersistException {
        User user = new User();
        return persist(user);
    }

    public MySqlUserDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<User> result = new LinkedList<User>();
        try {
            while (rs.next()) {
                PersistUser user = new PersistUser();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                result.add(user);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws PersistException {
        try {
            statement.setString(1, object.getLogin());
            statement.setString(2, object.getPassword());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws PersistException {
        try {
            statement.setString(1, object.getLogin());
            statement.setString(2, object.getPassword());
            statement.setInt(3, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
