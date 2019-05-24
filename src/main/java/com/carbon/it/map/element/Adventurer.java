/*
 * (C) Copyright 2019 JEAN Kévin
 */
package com.carbon.it.map.element;

import com.carbon.it.enums.Action;
import com.carbon.it.enums.Direction;
import com.carbon.it.map.Position;

import java.util.List;
import java.util.stream.Collectors;

public class Adventurer implements Tile {

    private Position position;
    private String name;
    private Direction direction;
    private List<Action> actions;
    private Integer treasure;

    public Adventurer(Position position, String name, Direction direction, List<Action> actions, Integer treasure){
        this.position = position;
        this.name = name;
        this.direction = direction;
        this.actions = actions;
        this.treasure = treasure;
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
        return this.treasure;
    }

    public String getName() {
        return this.name;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public List<Action> getActions() {
        return this.actions.stream().collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "A(" + this.name + ")";
    }

    @Override
    public String getInformations() {
        return "A - " + this.name + " - " + this.position.getInformations() + " - " + this.direction + " - " + this.treasure;
    }
}
