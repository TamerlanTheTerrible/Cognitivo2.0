package com.example.timur.mainmenu.model;

/**
 * Created by Timur on 2/26/2016.
 */
public class GameTable {
    public GameTable(int columns, int rows){
        super();
        this.columns = columns;
        this.rows = rows;
    }

    private  int columns;
    private int rows;

    public  int getColumns(){return  columns;}
    public void setColumns(int columns){this.columns = columns;}
    public int getRows(){return rows;}
    public void setRows(int rows){this.rows = rows;}
    public String toString(){
        return columns + " x " +rows;
    }
}
