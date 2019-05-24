/*
 * (C) Copyright 2019 JEAN Kévin
 */
package com.carbon.it.map.element;

import com.carbon.it.map.Position;

public interface Tile {

    public boolean isTraversable();

    public Position getPosition();

    public Integer getLoot();

    public String getInformations();
}
