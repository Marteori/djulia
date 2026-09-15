package com.djulia.tileRegistry.tileDefinition;

import com.djulia.tileRegistry.tileMap.Tile;
import com.djulia.tileRegistry.tileMap.TileType;

public abstract class TileDefinition {

    private final String id;
    private final TileType type;

    protected TileDefinition(String id, TileType type) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Tile id cannot be null or blank");
        }

        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public TileType getType() {
        return type;
    }

    public Tile createTile() {
        return new Tile(id);
    }
}