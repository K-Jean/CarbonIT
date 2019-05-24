/*
 * (C) Copyright 2019 JEAN Kévin
 */
package com.carbon.it.enums;

import java.util.ArrayList;
import java.util.List;

public enum Action {
    G, //Left
    D, //Right
    A, //Forward
    R; //backward

    public static List<Action> parsingString(String actionText){
        List<Action> actions = new ArrayList<>();
        for(int i=0; i < actionText.length(); i++){
            actions.add(Action.valueOf(String.valueOf(actionText.charAt(i))));
        }
        return actions;
    }
}
