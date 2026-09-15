package com.djulia.tests.testObjects;

import com.djulia.levelRegistry.Chunk;
import com.djulia.tests.BaseTest;
import com.djulia.tileRegistry.tileMap.Tile;

public class testChunk extends BaseTest {

    public testChunk() {
        super("Chunk");
    }

    @Override
    protected void runTest() {
        Chunk chunk = new Chunk(5, -3);

        check(
                chunk.getChunkX() == 5,
                "Chunk X корректный"
        );

        check(
                chunk.getChunkY() == -3,
                "Chunk Y корректный"
        );

        Tile grass = new Tile("grass");
        Tile stone = new Tile("stone");

        // Обычная установка
        chunk.setGround(0, 0, grass);

        check(
                chunk.getGround(0, 0) == grass,
                "Ground устанавливается и возвращается"
        );

        // Край чанка
        chunk.setGround(15, 15, stone);

        check(
                chunk.getGround(15, 15) == stone,
                "Ground работает на границе Chunk"
        );

        // Object
        Tile chest = new Tile("chest");

        chunk.setObject(7, 8, chest);

        check(
                chunk.getObject(7, 8) == chest,
                "Object устанавливается и возвращается"
        );

        // Удаление объекта
        chunk.setObject(7, 8, null);

        check(
                chunk.getObject(7, 8) == null,
                "Object можно удалить"
        );

        // Разные позиции не конфликтуют
        Tile door = new Tile("door");

        chunk.setObject(1, 1, chest);
        chunk.setObject(2, 1, door);

        check(
                chunk.getObject(1, 1) == chest,
                "Object X=1 сохранён"
        );

        check(
                chunk.getObject(2, 1) == door,
                "Object X=2 сохранён"
        );

        // Выход за границу
        check(
                chunk.getGround(-1, 0).getDefinitionId().equals("VOID"),
                "Ground за границей возвращает VOID"
        );

        check(
                chunk.getGround(16, 0).getDefinitionId().equals("VOID"),
                "Ground за правой границей возвращает VOID"
        );

        check(
                chunk.getObject(-1, 0).getDefinitionId().equals("VOID"),
                "Object за границей возвращает VOID"
        );
    }
}
