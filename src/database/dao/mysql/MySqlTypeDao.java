package database.dao.mysql;

import database.dao.AbstractJDBCDao;
import database.dao.DaoFactory;
import database.dao.PersistException;
import database.dao.entity.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Паша on 01.06.2016.
 */
public class MySqlTypeDao extends AbstractJDBCDao<Type, Integer>{

    private class PersistType extends Type {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM project_z.type";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE project_z.type SET type = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM project_z.type WHERE id = ?";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO project_z.type (type) VALUES(?)";
    }

    @Override
    public Type create() throws PersistException {
        Type type = new Type();
        return persist(type);
    }

    public MySqlTypeDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
    }

    @Override
    protected List<Type> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Type> result = new LinkedList<Type>();
        try {
            while (rs.next()) {
                PersistType type = new PersistType();
                type.setId(rs.getInt("id"));
                type.setType(rs.getString("type"));
                result.add(type);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Type object) throws PersistException {
        try {
            statement.setString(1, object.getType());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Type object) throws PersistException {
        try {
            statement.setString(1, object.getType());
            statement.setInt(2, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
