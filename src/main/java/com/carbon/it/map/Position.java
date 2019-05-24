/*
 * (C) Copyright 2019 JEAN Kévin
 */
package com.carbon.it.map;

public class Position {

    private Integer x;
    private Integer y;

    public Position(Integer x, Integer y){
        this.x = x;
        this.y = y;
    }
    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public String getInformations() {
        return this.y + " - " + this.x;
    }
}
