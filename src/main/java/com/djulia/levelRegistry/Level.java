package com.djulia.levelRegistry;

import com.djulia.tileRegistry.TilePair;
import com.djulia.tileRegistry.tileMap.Tile;
import com.djulia.levelRegistry.worldPos.ChunkPos;
import com.djulia.levelRegistry.worldPos.LocalPos;
import com.djulia.levelRegistry.worldPos.WorldPos;

import java.util.HashMap;
import java.util.Map;

public class Level {

    private final String id;
    private final String name;

    private final Map<Long, Chunk> chunks;

    public Level(String id, String name) {
        this.id = id;
        this.name = name;

        this.chunks = new HashMap<>();
    }

    public Chunk getChunk(ChunkPos chunkPos) {
        long key = chunkKey(chunkPos);

        return chunks.get(key);
    }

    public Chunk createChunk(ChunkPos chunkPos) {
        long key = chunkKey(chunkPos);

        return chunks.computeIfAbsent(
                key,
                k -> new Chunk(chunkPos.x(), chunkPos.y())
        );
    }

    public Tile getGround(WorldPos worldPos) {
        Chunk chunk = getChunk(worldPos.getChunkPos());

        if (chunk == null) {
            return new Tile("VOID");
        }

        LocalPos localPos = worldPos.getLocalPos();

        return chunk.getGround(localPos.x(), localPos.y());
    }

    public void setGround(WorldPos worldPos, Tile tile) {
        Chunk chunk = createChunk(worldPos.getChunkPos());

        LocalPos localPos = worldPos.getLocalPos();

        chunk.setGround(localPos.x(), localPos.y(), tile);
    }

    public Tile getObject(WorldPos worldPos) {
        Chunk chunk = getChunk(worldPos.getChunkPos());

        if (chunk == null) {
            return new Tile("VOID");
        }

        LocalPos localPos = worldPos.getLocalPos();

        return chunk.getObject(localPos.x(), localPos.y());
    }

    public void setObject(WorldPos worldPos, Tile tile) {
        Chunk chunk = createChunk(worldPos.getChunkPos());

        LocalPos localPos = worldPos.getLocalPos();

        chunk.setObject(localPos.x(), localPos.y(), tile);
    }

    public TilePair getTiles(WorldPos worldPos) {
        ChunkPos chunkPos = worldPos.getChunkPos();
        LocalPos localPos = worldPos.getLocalPos();

        Chunk chunk = getChunk(chunkPos);

        if (chunk == null) {
            return new TilePair(new Tile("VOID"), null);
        }

        return chunk.getTiles(localPos.x(), localPos.y());
    }

    private static long chunkKey(ChunkPos chunkPos) {
        return ((long) chunkPos.x() << 32) ^ (chunkPos.y() & 0xffffffffL);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}