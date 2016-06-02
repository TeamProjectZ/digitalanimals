package database.dao.entity;

import database.dao.Identified;

/**
 * Created by Паша on 01.06.2016.
 */
public class Type implements Identified<Integer> {
    private Integer id = null;
    private String type;

    @Override
    public Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
