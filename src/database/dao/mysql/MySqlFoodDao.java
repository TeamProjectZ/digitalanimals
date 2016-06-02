package database.dao.mysql;

import database.dao.AbstractJDBCDao;
import database.dao.DaoFactory;
import database.dao.PersistException;
import database.dao.entity.Food;
import database.dao.entity.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Паша on 02.06.2016.
 */
public class MySqlFoodDao extends AbstractJDBCDao<Food, Integer> {
    private class PersistFood extends Food {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlFoodDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
        addRelation(Food.class, "type");
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM project_z.food";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE project_z.food SET name = ?, energy = ?, price = ?, size = ?, type_id = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM project_z.food WHERE id = ?";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO project_z.food (name, energy, price, size, type_id) VALUES(?,?,?,?,?)";
    }

    @Override
    public Food create() throws PersistException {
        Food food = new Food();
        return persist(food);
    }

    @Override
    protected List<Food> parseResultSet(ResultSet rs) throws PersistException {
        List<Food> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistFood food = new PersistFood();
                food.setId(rs.getInt("id"));
                food.setName(rs.getString("name"));
                food.setPrice(rs.getInt("price"));
                food.setEnergy(rs.getInt("energy"));
                food.setSize(rs.getInt("size"));
                food.setType((Type) getDependence(Type.class, rs.getInt("type_id")));
                result.add(food);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Food object) throws PersistException {
        try {
            Integer typeId = (object.getType() == null || object.getType().getId() == null) ? null
                    : object.getType().getId();
            statement.setString(1, object.getName());
            statement.setInt(2, object.getEnergy());
            statement.setInt(3, object.getPrice());
            statement.setInt(4, object.getSize());
            statement.setObject(5, typeId);
            statement.setInt(6, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Food object) throws PersistException {
        try {
            Integer typeId = (object.getType() == null || object.getType().getId() == null) ? null
                    : object.getType().getId();
            statement.setString(1, object.getName());
            statement.setInt(2, object.getEnergy());
            statement.setInt(3, object.getPrice());
            statement.setInt(4, object.getSize());
            statement.setObject(5, typeId);
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
