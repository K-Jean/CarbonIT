/*
 * (C) Copyright 2019 JEAN Kévin
 */
package com.carbon.it.map.element;

import com.carbon.it.map.Position;

public class Empty implements Tile {

    private Position position;

    public Empty(Position position){
        this.position = position;
    }

    @Override
    public boolean isTraversable() {
        return true;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Integer getLoot() {
        return 0;
    }

    @Override
    public String toString() {
        return ".";
    }

    @Override
    public String getInformations() {
        return "";
    }
}
