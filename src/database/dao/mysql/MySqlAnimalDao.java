package database.dao.mysql;

import database.dao.AbstractJDBCDao;
import database.dao.DaoFactory;
import database.dao.PersistException;
import database.dao.entity.Animal;
import database.dao.entity.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Паша on 01.06.2016.
 */
public class MySqlAnimalDao extends AbstractJDBCDao<Animal, Integer> {
    private class PersistAnimal extends Animal {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlAnimalDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
        addRelation(Animal.class, "type");
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM project_z.animal";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE project_z.animal SET name = ?, price = ?, health = ?, type_id = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM project_z.animal WHERE id = ?";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO project_z.animal (name, price, health, type_id) VALUES(?,?,?,?)";
    }

    @Override
    public Animal create() throws PersistException {
        Animal animal = new Animal();
        return persist(animal);
    }

    @Override
    protected List<Animal> parseResultSet(ResultSet rs) throws PersistException {
        List<Animal> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistAnimal persistAnimal = new PersistAnimal();
                persistAnimal.setId(rs.getInt("id"));
                persistAnimal.setName(rs.getString("name"));
                persistAnimal.setPrice(rs.getInt("price"));
                persistAnimal.setHealth(rs.getInt("health"));
                persistAnimal.setType((Type) getDependence(Type.class, rs.getInt("type_id")));
                result.add(persistAnimal);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Animal object) throws PersistException {
        try {
            Integer typeId = (object.getType() == null || object.getType().getId() == null) ? null
                    : object.getType().getId();
            statement.setString(1, object.getName());
            statement.setInt(2, object.getPrice());
            statement.setInt(3, object.getHealth());
            statement.setObject(4, typeId);
            statement.setInt(5, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Animal object) throws PersistException {
        try {
            Integer typeId = (object.getType() == null || object.getType().getId() == null) ? null
                    : object.getType().getId();
            statement.setString(1, object.getName());
            statement.setInt(2, object.getPrice());
            statement.setInt(3, object.getHealth());
            statement.setObject(4, typeId);
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
