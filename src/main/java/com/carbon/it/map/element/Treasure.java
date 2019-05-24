/*
 * (C) Copyright 2019 JEAN Kévin
 */
package com.carbon.it.map.element;

import com.carbon.it.map.Position;

public class Treasure implements Tile {

    private Integer nbTreasure;
    private Position position;

    public Treasure(Position position, Integer nbTreasure) {
        this.position = position;
        this.nbTreasure = nbTreasure;
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
        return this.nbTreasure;
    }

    @Override
    public String toString() {
        return "T(" + this.nbTreasure + ")";
    }

    @Override
    public String getInformations() {
        return "T - " + this.position.getInformations() + " - " + " - " + this.nbTreasure;
    }
}
