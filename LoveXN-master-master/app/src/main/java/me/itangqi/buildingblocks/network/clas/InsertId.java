package me.itangqi.buildingblocks.network.clas;

/**
 * Created by oreo on 2016/10/17.
 */
public class InsertId {
    private int  insertId;
    private String state;

    public InsertId(int insertId, String state) {
        this.insertId = insertId;
        this.state = state;
    }

    public int getInsertId() {
        return insertId;
    }

    public void setInsertId(int insertId) {
        this.insertId = insertId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
