/*
 * (C) Copyright 2019 JEAN Kévin
 */
package com.carbon.it.map;

import com.carbon.it.map.element.Empty;
import com.carbon.it.map.element.Tile;

import java.io.*;

public class MapExporter {

    private MapExporter() {
    }

    public static void createFileResult(Map map, String outputFile) throws IOException {
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(map.getInforations());
            writer.write(System.lineSeparator());
            for (int h = 0; h < map.getHeight(); h++) {
                for (int w = 0; w < map.getWidth(); w++) {
                    Tile tile = map.getElement(new Position(w, h));
                    if (!(tile instanceof Empty)) {
                        writer.write(tile.getInformations());
                        writer.write(System.lineSeparator());
                    }
                }
            }
        }
    }
}
