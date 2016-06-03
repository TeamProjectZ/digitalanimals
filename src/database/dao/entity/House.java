package database.dao.entity;

import database.dao.Identified;

/**
 * Created by Паша on 03.06.2016.
 */
public class House implements Identified<Integer> {
    private Integer id = null;
    private int price;
    private int size;
    private Type type;


    public Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
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
        return "House{" +
                "id=" + id +
                ", price=" + price +
                ", size=" + size +
                ", type=" + type +
                '}';
    }
}
