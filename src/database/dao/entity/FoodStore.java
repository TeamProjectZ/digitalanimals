package database.dao.entity;

import database.dao.Identified;

/**
 * Created by Паша on 02.06.2016.
 */
public class FoodStore implements Identified<Integer>{
    private Integer id = null;
    private int value;
    private Food food;

    public Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "FoodStore{" +
                "id=" + id +
                ", value=" + value +
                ", food=" + food +
                '}';
    }
}
