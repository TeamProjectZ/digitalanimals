package database.dao.mysql;

import database.dao.AbstractJDBCDao;
import database.dao.DaoFactory;
import database.dao.PersistException;
import database.dao.entity.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;


public class MySqlGroupDao extends AbstractJDBCDao<Group, Integer> {

    private class PersistGroup extends Group {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM project_z.group";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE project_z.group SET number = ?, department = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM project_z.group WHERE id = ?";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO project_z.group (number, department) VALUES(?, ?)";
    }

    @Override
    public Group create() throws PersistException {
        Group g = new Group();
        return persist(g);
    }

    public MySqlGroupDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
    }

    @Override
    protected List<Group> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Group> result = new LinkedList<Group>();
        try {
            while (rs.next()) {
                PersistGroup group = new PersistGroup();
                group.setId(rs.getInt("id"));
                group.setNumber(rs.getInt("number"));
                group.setDepartment(rs.getString("department"));
                result.add(group);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Group object) throws PersistException {
        try {
            statement.setInt(1, object.getNumber());
            statement.setString(2, object.getDepartment());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Group object) throws PersistException {
        try {
            statement.setInt(1, object.getNumber());
            statement.setString(2, object.getDepartment());
            statement.setInt(3, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
