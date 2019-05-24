/*
 * (C) Copyright 2019 JEAN Kévin
 */
package com.carbon.it;

import com.carbon.it.enums.Action;
import com.carbon.it.enums.Direction;
import com.carbon.it.map.Map;
import com.carbon.it.map.Position;
import com.carbon.it.map.element.Adventurer;
import com.carbon.it.map.element.Tile;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class which is going to run all the actions of the differents adventurers.
 */
public class Manager {

    private static final Logger LOGGER = Logger.getLogger("Manager");

    private Map map;
    private List<Adventurer> adventurers;

    public Manager(Map map, List<Adventurer> adventurers) {
        this.map = map;
        this.adventurers = adventurers;
    }

    public Map startAdventure() {
        boolean move = true;
        while (move) {
            move = false;
            for (int i = 0; i < this.adventurers.size(); i++) {
                Adventurer currentAdventurer = this.adventurers.get(i);
                List<Action> actions = currentAdventurer.getActions();
                if (!actions.isEmpty()) {
                    move = true;
                    Action currentAction = actions.remove(0);
                    Position newPos;
                    switch (currentAction) {
                        case A: case R:
                            newPos = this.calculateNewPosition(currentAdventurer.getPosition(),
                                    currentAdventurer.getDirection(), currentAction);
                            this.adventurers.set(i,this.moveAdventurer(currentAdventurer, newPos, actions));
                            break;
                        case D:
                            this.adventurers.set(i, new Adventurer(currentAdventurer.getPosition(),
                                    currentAdventurer.getName(), Direction.toTheRight(currentAdventurer.getDirection()),
                                    actions, currentAdventurer.getLoot()));
                            break;
                        case G:
                            this.adventurers.set(i, new Adventurer(currentAdventurer.getPosition(),
                                    currentAdventurer.getName(), Direction.toTheLeft(currentAdventurer.getDirection()),
                                    actions, currentAdventurer.getLoot()));
                            break;
                    }
                }
            }
        }
        return this.map;
    }

    private Position calculateNewPosition(Position oldPosition, Direction direction, Action action) {
        int avance = 0;
        if (action.equals(Action.A)) {
            avance = 1;
        }
        if (action.equals(Action.R)) {
            avance = -1;
        }
        switch (direction) {
            case O:
                return new Position(oldPosition.getX() - avance, oldPosition.getY());
            case N:
                return new Position(oldPosition.getX(), oldPosition.getY() - avance);
            case E:
                return new Position(oldPosition.getX() + avance, oldPosition.getY());
            case S:
                return new Position(oldPosition.getX(), oldPosition.getY() + avance);
            default:
                return oldPosition;
        }

    }

    private Adventurer moveAdventurer(Adventurer oldAdventurer, Position newPos, List<Action> actions) {
        if (!oldAdventurer.getPosition().equals(newPos)) {
            try {
                Tile tile = this.map.getElement(newPos);
                if (tile.isTraversable()) {
                    this.map.removeElement(oldAdventurer.getPosition());
                    Adventurer newAdventurer = new Adventurer(newPos, oldAdventurer.getName(),
                            oldAdventurer.getDirection(), actions, oldAdventurer.getLoot() + tile.getLoot());
                    this.map.removeElement(newPos);
                    this.map.addElement(newAdventurer);
                    return newAdventurer;
                } else {
                    LOGGER.warning("The adventurer is trying to going throught a solid object");
                    return new Adventurer(oldAdventurer.getPosition(), oldAdventurer.getName(),
                            oldAdventurer.getDirection(), actions, oldAdventurer.getLoot());
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                LOGGER.log(Level.WARNING, "The adventurer is trying to move outside the map", e);
            }
        }
        return new Adventurer(oldAdventurer.getPosition(), oldAdventurer.getName(),
                oldAdventurer.getDirection(), actions, oldAdventurer.getLoot());
    }
}
