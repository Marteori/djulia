package com.djulia.levelRegistry.worldPos;

import com.djulia.levelRegistry.Chunk;

public record WorldPos(int x, int y) {

    public ChunkPos getChunkPos() {
        return new ChunkPos(
                Math.floorDiv(x, Chunk.SIZE),
                Math.floorDiv(y, Chunk.SIZE)
        );
    }

    public ChunkPos getChunkPos(int x, int y) {
        return new ChunkPos(
                Math.floorDiv(x, Chunk.SIZE),
                Math.floorDiv(y, Chunk.SIZE)
        );
    }

    public LocalPos getLocalPos() {
        return new LocalPos(
                Math.floorMod(x, Chunk.SIZE),
                Math.floorMod(y, Chunk.SIZE)
        );
    }

    public LocalPos getLocalPos(int x, int y) {
        return new LocalPos(
                Math.floorMod(x, Chunk.SIZE),
                Math.floorMod(y, Chunk.SIZE)
        );
    }
}