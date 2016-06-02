package database.dao.entity;

import database.dao.Identified;

/**
 * Created by Паша on 02.06.2016.
 */
public class Food implements Identified<Integer>{
    private Integer id = null;
    private String name;
    private int energy;
    private int price;
    private int size;
    private Type type;

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

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", energy=" + energy +
                ", price=" + price +
                ", size=" + size +
                ", type=" + type +
                '}';
    }
}
