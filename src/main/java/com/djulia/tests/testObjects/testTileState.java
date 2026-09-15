package com.djulia.tests.testObjects;

import com.djulia.tests.BaseTest;
import com.djulia.tileRegistry.tileMap.Tile;

public class testTileState extends BaseTest {
    public testTileState() {
        super("TileState");
    }

    @Override
    protected void runTest() {
        Tile door1 = new Tile("door");
        Tile door2 = new Tile("door");

        door1.getState().set("open", true);

        check(
                (boolean) door1.getState().get("open"),
                "Состояние Tile сохраняется"
        );

        check(
                !door2.getState().has("open"),
                "Состояние одного Tile не распространяется на другой"
        );

        door1.getState().set("uses", 5);

        check(
                (int) door1.getState().get("uses") == 5,
                "TileState хранит дополнительные данные"
        );

        door1.getState().remove("uses");

        check(
                !door1.getState().has("uses"),
                "Состояние можно удалить"
        );

        door1.getState().clear();

        check(
                !door1.getState().has("open"),
                "State.clear() очищает состояние"
        );
    }
}
