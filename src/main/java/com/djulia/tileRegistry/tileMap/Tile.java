package com.djulia.tileRegistry.tileMap;

import com.djulia.api.state.TileState;

public class Tile {

    private final String definitionId;
    private final TileState state;

    public Tile(String definitionId) {
        this.definitionId = definitionId;
        this.state = new TileState();
    }

    public String getDefinitionId() {
        return definitionId;
    }

    public TileState getState() {
        return state;
    }
}