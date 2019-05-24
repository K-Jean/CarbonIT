/*
 * (C) Copyright 2019 JEAN Kévin
 */
package com.carbon.it;

import com.carbon.it.exception.InvalidLineException;
import com.carbon.it.map.MapExporter;
import com.carbon.it.map.Map;
import com.carbon.it.map.MapImporter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchTreasure {

    private static final Logger LOGGER = Logger.getLogger("SearchTreasure");

    public static void main(String[] args) {
        if (args.length == 2) {
            MapImporter mapImporter = new MapImporter();
            Map map = null;
            try {
                mapImporter.importTreasureFile(args[0]);
                map = mapImporter.buildMap();
            } catch (IOException | InvalidLineException e) {
                LOGGER.log(Level.WARNING, "Error during the parsing of the inputfile", e);
                System.exit(1);
            }

            Manager manager = new Manager(map, mapImporter.getAdventurers());
            map = manager.startAdventure();
            try {
                MapExporter.createFileResult(map, args[1]);
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "Error during writting of the outputfile", e);
                System.exit(1);
            }

            System.exit(0);
        } else {
            LOGGER.warning("You need to pass to this application two arguments <inputFile> <outputFile>");
            System.exit(1);
        }
    }
}
