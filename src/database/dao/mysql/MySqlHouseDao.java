package database.dao.mysql;

import database.dao.AbstractJDBCDao;
import database.dao.DaoFactory;
import database.dao.PersistException;
import database.dao.entity.Animal;
import database.dao.entity.House;
import database.dao.entity.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Паша on 03.06.2016.
 */
public class MySqlHouseDao extends AbstractJDBCDao<House, Integer> {

    private class PersistHouse extends House {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlHouseDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
        addRelation(House.class, "type");
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM project_z.house";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE project_z.house SET price = ?, size = ?, type_id = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM project_z.house WHERE id = ?";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO project_z.house (price, size, type_id) VALUES(?,?,?)";
    }

    @Override
    public House create() throws PersistException {
        House house = new House();
        return persist(house);
    }

    @Override
    protected List<House> parseResultSet(ResultSet rs) throws PersistException {
        List<House> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistHouse house = new PersistHouse();
                house.setId(rs.getInt("id"));
                house.setPrice(rs.getInt("price"));
                house.setSize(rs.getInt("size"));
                house.setType((Type) getDependence(Type.class, rs.getInt("type_id")));
                result.add(house);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, House object) throws PersistException {
        try {
            Integer typeId = (object.getType() == null || object.getType().getId() == null) ? null
                    : object.getType().getId();
            statement.setInt(1, object.getPrice());
            statement.setInt(2, object.getSize());
            statement.setObject(3, typeId);
            statement.setInt(4, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, House object) throws PersistException {
        try {
            Integer typeId = (object.getType() == null || object.getType().getId() == null) ? null
                    : object.getType().getId();
            statement.setInt(1, object.getPrice());
            statement.setInt(2, object.getSize());
            statement.setObject(3, typeId);
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
