package database.dao.mysql;

import database.dao.AbstractJDBCDao;
import database.dao.DaoFactory;
import database.dao.PersistException;
import database.dao.entity.Food;
import database.dao.entity.FoodStore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Паша on 02.06.2016.
 */
public class MySqlFoodStoreDao extends AbstractJDBCDao<FoodStore, Integer> {
    private class PersistFoodStore extends FoodStore {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlFoodStoreDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
        addRelation(FoodStore.class, "food");
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM project_z.food_store";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE project_z.food_store SET value = ?, food_id = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM project_z.food_store WHERE id = ?";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO project_z.food_store (value, food_id) VALUES(?,?)";
    }

    @Override
    public FoodStore create() throws PersistException {
        FoodStore food = new FoodStore();
        return persist(food);
    }

    @Override
    protected List<FoodStore> parseResultSet(ResultSet rs) throws PersistException {
        List<FoodStore> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistFoodStore food = new PersistFoodStore();
                food.setId(rs.getInt("id"));
                food.setValue(rs.getInt("value"));
                food.setFood((Food) getDependence(Food.class, rs.getInt("food_id")));
                result.add(food);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, FoodStore object) throws PersistException {
        try {
            Integer foodId = (object.getFood() == null || object.getFood().getId() == null) ? null
                    : object.getFood().getId();
            statement.setInt(1, object.getValue());
            statement.setObject(2, foodId);
            statement.setInt(3, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, FoodStore object) throws PersistException {
        try {
            Integer foodId = (object.getFood() == null || object.getFood().getId() == null) ? null
                    : object.getFood().getId();
            statement.setInt(1, object.getValue());
            statement.setObject(2, foodId);
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
