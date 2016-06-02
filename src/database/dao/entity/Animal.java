package database.dao.entity;

import database.dao.Identified;

/**
 * Created by Паша on 01.06.2016.
 */
public class Animal implements Identified<Integer> {

    private Integer id = null;
    private String name;
    private int price;
    private int health;
    private Type type;

    @Override
    public Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", health=" + health +
                ", type=" + type +
                '}';
    }
}
