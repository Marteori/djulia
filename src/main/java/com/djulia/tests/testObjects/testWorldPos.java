package com.djulia.tests.testObjects;

import com.djulia.levelRegistry.worldPos.WorldPos;
import com.djulia.tests.BaseTest;

public class testWorldPos extends BaseTest {
    public testWorldPos() {
        super("WorldPos");
    }

    @Override
    protected void runTest() {
        WorldPos pos = new WorldPos(0, 0);

        check(
                pos.getChunkPos().x() == 0,
                "World X=0 находится в Chunk X=0"
        );

        check(
                pos.getLocalPos().x() == 0,
                "World X=0 имеет Local X=0"
        );

        pos = new WorldPos(15, 15);

        check(
                pos.getChunkPos().x() == 0,
                "World X=15 находится в Chunk X=0"
        );

        check(
                pos.getLocalPos().x() == 15,
                "World X=15 имеет Local X=15"
        );

        pos = new WorldPos(16, 16);

        check(
                pos.getChunkPos().x() == 1,
                "World X=16 находится в Chunk X=1"
        );

        check(
                pos.getLocalPos().x() == 0,
                "World X=16 имеет Local X=0"
        );

        // Самая важная проверка
        pos = new WorldPos(-1, -1);

        check(
                pos.getChunkPos().x() == -1,
                "World X=-1 находится в Chunk X=-1"
        );

        check(
                pos.getLocalPos().x() == 15,
                "World X=-1 имеет Local X=15"
        );

        pos = new WorldPos(-16, -16);

        check(
                pos.getChunkPos().x() == -1,
                "World X=-16 находится в Chunk X=-1"
        );

        check(
                pos.getLocalPos().x() == 0,
                "World X=-16 имеет Local X=0"
        );

        pos = new WorldPos(-17, -17);

        check(
                pos.getChunkPos().x() == -2,
                "World X=-17 находится в Chunk X=-2"
        );

        check(
                pos.getLocalPos().x() == 15,
                "World X=-17 имеет Local X=15"
        );
    }
}
