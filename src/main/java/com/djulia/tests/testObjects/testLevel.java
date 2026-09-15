package com.djulia.tests.testObjects;

import com.djulia.levelRegistry.Chunk;
import com.djulia.levelRegistry.Level;
import com.djulia.levelRegistry.worldPos.WorldPos;
import com.djulia.tests.BaseTest;
import com.djulia.tileRegistry.tileMap.Tile;

public class testLevel extends BaseTest {
    public testLevel() {
        super("Level");
    }

    @Override
    protected void runTest() {
        Level level = new Level("test", "Underworld");

        // World (0, 0)
        WorldPos pos = new WorldPos(0, 0);
        Tile grass = new Tile("grass");

        level.setGround(pos, grass);

        check(
                level.getGround(pos) == grass,
                "Tile сохраняется в WorldPos(0, 0)"
        );

        // World (15, 15) — последний тайл первого Chunk
        pos = new WorldPos(15, 15);

        Tile stone = new Tile("stone");
        level.setGround(pos, stone);

        check(
                level.getGround(pos) == stone,
                "Tile сохраняется в WorldPos(15, 15)"
        );

        // World (16, 16) — первый тайл соседнего Chunk
        pos = new WorldPos(16, 16);

        Tile dirt = new Tile("dirt");
        level.setGround(pos, dirt);

        check(
                level.getGround(pos) == dirt,
                "Tile сохраняется в WorldPos(16, 16)"
        );

        // Проверяем, что это действительно другой Chunk
        Chunk chunk = level.getChunk(
                pos.getChunkPos()
        );

        check(
                chunk != null,
                "Chunk автоматически существует после setGround"
        );

        check(
                chunk.getChunkX() == 1 &&
                        chunk.getChunkY() == 1,
                "WorldPos(16, 16) находится в Chunk(1, 1)"
        );

        // Отрицательные координаты
        pos = new WorldPos(-1, -1);

        Tile lava = new Tile("lava");
        level.setGround(pos, lava);

        check(
                level.getGround(pos) == lava,
                "Tile сохраняется в отрицательных координатах"
        );

        chunk = level.getChunk(
                pos.getChunkPos()
        );

        check(
                chunk.getChunkX() == -1 &&
                        chunk.getChunkY() == -1,
                "WorldPos(-1, -1) находится в Chunk(-1, -1)"
        );

        // Object layer
        pos = new WorldPos(100, 50);

        Tile chest = new Tile("chest");

        level.setObject(pos, chest);

        check(
                level.getObject(pos) == chest,
                "Object сохраняется через Level"
        );
    }
}
