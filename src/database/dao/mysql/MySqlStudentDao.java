package database.dao.mysql;

import database.dao.AbstractJDBCDao;
import database.dao.DaoFactory;
import database.dao.PersistException;
import database.dao.entity.Group;
import database.dao.entity.Student;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class MySqlStudentDao extends AbstractJDBCDao<Student, Integer> {

    private class PersistStudent extends Student {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public MySqlStudentDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
        addRelation(Student.class, "group");
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM project_z.student";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE project_z.student SET name = ?, surname = ?, enrolment_date = ?, group_id = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM project_z.student WHERE id = ?";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO project_z.student (name, surname, enrolment_date, group_id) VALUES(?,?,?,?)";
    }

    @Override
    public Student create() throws PersistException {
        Student s = new Student();
        return persist(s);
    }

    @Override
    protected List<Student> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Student> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistStudent student = new PersistStudent();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                student.setEnrolmentDate(rs.getDate("enrolment_date"));
                student.setGroup((Group) getDependence(Group.class, rs.getInt("group_id")));
                result.add(student);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Student object) throws PersistException {
        try {
            Date sqlDate = convert(object.getEnrolmentDate());
            int groupId = (object.getGroup() == null || object.getGroup().getId() == null) ? -1
                    : object.getGroup().getId();
            statement.setString(1, object.getName());
            statement.setString(2, object.getSurname());
            statement.setDate(3, sqlDate);
            statement.setInt(4, groupId);
            statement.setInt(5, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Student object) throws PersistException {
        try {
            Date sqlDate = convert(object.getEnrolmentDate());
            Integer groupId = (object.getGroup() == null || object.getGroup().getId() == null) ? null
                    : object.getGroup().getId();
            statement.setString(1, object.getName());
            statement.setString(2, object.getSurname());
            statement.setDate(3, sqlDate);
            statement.setObject(4, groupId);
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