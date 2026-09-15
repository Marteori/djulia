package com.djulia.tests.testObjects;

import com.djulia.tests.BaseTest;
import com.djulia.tileRegistry.tileDefinition.GroundTileDefinition;
import com.djulia.tileRegistry.tileDefinition.TileDefinition;
import com.djulia.tileRegistry.tileMap.Tile;

public class testTileDefinition extends BaseTest {
    public testTileDefinition() {
        super("TileDefinition");
    }

    @Override
    protected void runTest() {
        TileDefinition definition = new GroundTileDefinition("grass");

        Tile tile1 = definition.createTile();
        Tile tile2 = definition.createTile();

        check(
                tile1.getDefinitionId().equals("grass"),
                "Tile получил корректный definitionId"
        );

        check(
                tile2.getDefinitionId().equals("grass"),
                "Второй Tile получил тот же definitionId"
        );

        tile1.getState().set("test", true);

        check(
                tile1.getState().get("test").equals(true),
                "Состояние первого Tile сохраняется"
        );

        check(
                !tile2.getState().has("test"),
                "Состояние первого Tile не распространяется на второй"
        );

        check(
                tile1 != tile2,
                "Definition создаёт разные экземпляры Tile"
        );
    }
}
