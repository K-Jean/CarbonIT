/*
 * (C) Copyright 2019 JEAN Kévin
 */
package com.carbon.it.map;

import com.carbon.it.exception.TileAlreadyUsedException;
import com.carbon.it.map.element.Empty;
import com.carbon.it.map.element.Tile;


public class Map {

    private Tile[][] matrix;
    private Integer width;
    private Integer height;

    public Map(Integer width, Integer height) {
        this.matrix = new Tile[height][width];
        this.width = width;
        this.height = height;

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                this.matrix[h][w] = new Empty(new Position(w, h));
            }
        }
    }

    /**
     * @throws TileAlreadyUsedException - throw if you have an object that is not an Empty tile on position (x,y).
     * @throws ArrayIndexOutOfBoundsException - throw if you are trying to insert an object not inside the matrix.
     */
    public void addElement(Tile element) {
        Position elementPos = element.getPosition();
        if(isInsideMap(elementPos.getX(),elementPos.getY())){
            if (this.matrix[elementPos.getY()][elementPos.getX()] instanceof Empty) {
                this.matrix[elementPos.getY()][elementPos.getX()] = element;
            } else {
                throw new TileAlreadyUsedException(String.format("This tile [%s,%s] is already used",
                        elementPos.getY(), elementPos.getX()));
            }
        } else {
            throw new ArrayIndexOutOfBoundsException("You are trying to insert an object out the map");
        }
    }

    public Tile getElement(Position position){
        if(isInsideMap(position.getX(),position.getY())){
           return this.matrix[position.getY()][position.getX()];
        } else {
            throw new ArrayIndexOutOfBoundsException("You are trying to get an object out the map");
        }
    }

    public Tile removeElement(Position position){
        if(isInsideMap(position.getX(),position.getY())){
            Tile tile = this.matrix[position.getY()][position.getX()];
            this.matrix[position.getY()][position.getX()] = new Empty(position);
            return tile;
        } else {
            throw new ArrayIndexOutOfBoundsException("You are trying to remove an object out the map");
        }
    }

    private boolean isInsideMap(Integer x, Integer y){
        return y >= 0 &&y < this.matrix.length && x >= 0 && x < this.matrix[0].length;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int h = 0; h < this.matrix.length; h++) {
            for (int w = 0; w < this.matrix[h].length; w++) {
                builder.append(this.matrix[h][w]);
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

    public String getInforations() {
        return "C - " + this.width + " - " + this.height;
    }
}
