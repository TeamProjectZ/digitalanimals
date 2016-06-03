package database.dao.mysql;

import database.dao.AbstractJDBCDao;
import database.dao.DaoFactory;
import database.dao.PersistException;
import database.dao.entity.User;
import database.dao.entity.Zoo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Паша on 30.05.2016.
 */
public class MySqlZooDao extends AbstractJDBCDao<Zoo, Integer>{
    private Connection connection;

    private class PersistZoo extends Zoo {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlZooDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory,connection);
        this.connection = connection;
        addRelation(Zoo.class, "user");
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM project_z.zoo";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE project_z.zoo SET name = ?, cash = ?, last_activity = ?, user_id = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM project_z.zoo WHERE id = ?";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO project_z.zoo (name, cash, last_activity, user_id) VALUES(?,?,?,?)";
    }

    @Override
    public Zoo create() throws PersistException {
        Zoo zoo = new Zoo();
        return persist(zoo);
    }

    @Override
    protected List<Zoo> parseResultSet(ResultSet rs) throws PersistException {
        List<Zoo> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistZoo persistZoo = new PersistZoo();
                persistZoo.setId(rs.getInt("id"));
                persistZoo.setName(rs.getString("name"));
                persistZoo.setCash(rs.getInt("cash"));
                persistZoo.setLasActivity(rs.getDate("last_activity"));
                persistZoo.setUser((User) getDependence(User.class, rs.getInt("user_id")));
                result.add(persistZoo);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }


    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Zoo object) throws PersistException {
        try {
            Date sqlDate = convert(object.getLasActivity());
            Integer userId = (object.getUser() == null || object.getUser().getId() == null) ? null
                    : object.getUser().getId();
            statement.setString(1, object.getName());
            statement.setInt(2, object.getCash());
            statement.setDate(3, sqlDate);
            statement.setObject(4, userId);
            statement.setInt(5, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Zoo object) throws PersistException {
        try {
            Date sqlDate = convert(object.getLasActivity());
            Integer userId = (object.getUser() == null || object.getUser().getId() == null) ? null
                    : object.getUser().getId();
            statement.setString(1, object.getName());
            statement.setInt(2, object.getCash());
            statement.setDate(3, sqlDate);
            statement.setObject(4, userId);
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    protected Date convert(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new Date(date.getTime());
    }
}
