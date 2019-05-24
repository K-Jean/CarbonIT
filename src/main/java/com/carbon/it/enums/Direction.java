/*
 * (C) Copyright 2019 JEAN Kévin
 */
package com.carbon.it.enums;

public enum Direction {
    N, //North
    S, //South
    E, //East
    O; //West

    public static Direction toTheLeft(Direction direction){
        switch (direction){
            case N: return O;
            case O: return S;
            case S: return E;
            case E: return N;
            default:
                throw new IllegalArgumentException("Illegal direction");
        }
    }

    public static Direction toTheRight(Direction direction){
        switch (direction){
            case N: return E;
            case E: return S;
            case S: return O;
            case O: return N;
            default:
                throw new IllegalArgumentException("Illegal direction");
        }
    }


}
