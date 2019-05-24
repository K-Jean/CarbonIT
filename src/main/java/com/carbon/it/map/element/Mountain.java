/*
 * (C) Copyright 2019 JEAN Kévin
 */
package com.carbon.it.map.element;

import com.carbon.it.map.Position;

public class Mountain implements Tile {

    private Position position;

    public Mountain(Position position){
        this.position = position;
    }

    @Override
    public boolean isTraversable() {
        return false;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Integer getLoot() {
        return null;
    }

    @Override
    public String toString() {
        return "M";
    }

    @Override
    public String getInformations() {
        return "M - " + this.position.getInformations();
    }
}
