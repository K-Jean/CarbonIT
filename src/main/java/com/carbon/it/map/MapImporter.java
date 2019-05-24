/*
 * (C) Copyright 2019 JEAN Kévin
 */
package com.carbon.it.map;

import com.carbon.it.enums.Action;
import com.carbon.it.enums.Direction;
import com.carbon.it.exception.InvalidLineException;
import com.carbon.it.exception.InvalidPositionException;
import com.carbon.it.map.element.Adventurer;
import com.carbon.it.map.element.Mountain;
import com.carbon.it.map.element.Treasure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MapImporter {

    private List<Adventurer> adventurers;
    private List<Mountain> mountains;
    private List<Treasure> treasures;
    private Integer width;
    private Integer heigth;

    public MapImporter() {
        this.adventurers = new ArrayList<>();
        this.mountains = new ArrayList<>();
        this.treasures = new ArrayList<>();
    }

    public void importTreasureFile(String inputFile) throws IOException, InvalidLineException {
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                parsingLine(line);
            }
        }
    }

    private void parsingLine(String line) throws InvalidLineException {
        switch (line.charAt(0)) {
            case '#':
                break;
            case 'C':
                parsingMapData(line);
                break;
            case 'M':
                parsingMountainData(line);
                break;
            case 'A':
                parsingAdventurerData(line);
                break;
            case 'T':
                parsingTreasureData(line);
                break;
            default:
                throw new InvalidLineException("This line can't be parse by our algorithm");
        }
    }

    private void parsingAdventurerData(String line) throws InvalidLineException {
        String[] adventurerParam = line.split("-");
        if (adventurerParam.length != 6) {
            throw new InvalidLineException("Your adventurer line have a bad format");
        } else {
            try {
                adventurers.add(new Adventurer(this.convertStringToPosition(adventurerParam[2].trim(), adventurerParam[3].trim()),
                        adventurerParam[1].trim(), Direction.valueOf(adventurerParam[4].trim()),
                        Action.parsingString(adventurerParam[5].trim()), 0));
            } catch (InvalidPositionException | IllegalArgumentException e) {
                throw new InvalidLineException("Error during the construction of Adventurer", e);
            }
        }
    }

    private Position convertStringToPosition(String x, String y) throws InvalidPositionException {
        try {
            return new Position(Integer.parseInt(x), Integer.parseInt(y));
        } catch (NumberFormatException e) {
            throw new InvalidPositionException("The position is incorrect", e);
        }
    }

    private void parsingMountainData(String line) throws InvalidLineException {
        String[] mountainParams = line.split("-");
        if (mountainParams.length != 3) {
            throw new InvalidLineException("Your mountain line have a bad format");
        } else {
            try {
                this.mountains.add(new Mountain(this.convertStringToPosition(mountainParams[1].trim(), mountainParams[2].trim())));
            } catch (InvalidPositionException | IllegalArgumentException e) {
                throw new InvalidLineException("Error during the construction of Mountain", e);
            }
        }
    }

    private void parsingMapData(String line) throws InvalidLineException {
        String[] mapParams = line.split("-");
        if (mapParams.length != 3) {
            throw new InvalidLineException("Your Map line have a bad format");
        } else {
            try {
                this.width = Integer.parseInt(mapParams[1].trim());
                this.heigth = Integer.parseInt(mapParams[2].trim());
            } catch (NumberFormatException e) {
                throw new InvalidLineException("Error during the construction of map", e);
            }
        }
    }

    private void parsingTreasureData(String line) throws InvalidLineException {
        String[] treasureParams = line.split("-");
        if (treasureParams.length != 4) {
            throw new InvalidLineException("Your treasure line have a bad format");
        } else {
            try {
                this.treasures.add(new Treasure(this.convertStringToPosition(treasureParams[1].trim(), treasureParams[2].trim()),
                        Integer.parseInt(treasureParams[3].trim())));
            } catch (InvalidPositionException | IllegalArgumentException e) {
                throw new InvalidLineException("Error during the construction of treasure", e);
            }
        }
    }

    public List<Adventurer> getAdventurers() {
        return adventurers.stream().collect(Collectors.toList());
    }

    public List<Mountain> getMountains() {
        return mountains.stream().collect(Collectors.toList());
    }

    public List<Treasure> getTreasures() {
        return treasures.stream().collect(Collectors.toList());
    }

    public Map buildMap() {
        Optional.ofNullable(this.width).ifPresent(integer -> {

        });
        if (this.width == null || this.width <= 0 || this.heigth == null || this.heigth <= 0) {
            throw new IllegalArgumentException("the width or the heigth was not set or less than 1");
        }
        Map newMap = new Map(this.width, this.heigth);
        this.mountains.forEach(mountain -> newMap.addElement(mountain));
        this.treasures.forEach(treasure -> newMap.addElement(treasure));
        this.adventurers.forEach(adventurer -> newMap.addElement(adventurer));
        return newMap;
    }
}
