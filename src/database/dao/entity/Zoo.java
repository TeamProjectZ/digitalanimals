package database.dao.entity;

import database.dao.Identified;

import java.util.Date;

/**
 * Created by Паша on 30.05.2016.
 */
public class Zoo implements Identified<Integer>{
    private Integer id = null;
    private String name ;
    private int cash;
    private Date lasActivity;
    private User user;

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

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public Date getLasActivity() {
        return lasActivity;
    }

    public void setLasActivity(Date lasActivity) {
        this.lasActivity = lasActivity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cash=" + cash +
                ", lasActivity=" + lasActivity +
                ", user=" + user +
                '}';
    }
}
