package com.djulia.levelRegistry;

import com.djulia.tileRegistry.TilePair;
import com.djulia.tileRegistry.tileMap.Tile;

import java.util.HashMap;
import java.util.Map;

public class Chunk {

    public static final int SIZE = 16;

    private final int chunkX;
    private final int chunkY;

    private final Tile[][] ground;
    private final Map<Long, Tile> objects;

    public Chunk(int chunkX, int chunkY) {
        this.chunkX = chunkX;
        this.chunkY = chunkY;

        this.ground = new Tile[SIZE][SIZE];
        this.objects = new HashMap<>();

        createGround();
    }

    private void createGround() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                ground[y][x] = new Tile("Ground");
            }
        }
    }

    public TilePair getTiles(int localX, int localY) {
        return new TilePair(
                getGround(localX, localY),
                getObject(localX, localY)
        );
    }

    public Tile getGround(int localX, int localY) {
        if (!isInside(localX, localY)) {
            return new Tile("VOID");
        }

        return ground[localY][localX];
    }

    public void setGround(int localX, int localY, Tile tile) {
        if (!isInside(localX, localY)) {
            throw new IllegalArgumentException(
                    "Invalid tile position"
            );
        }

        ground[localY][localX] = tile;
    }

    public Tile getObject(int localX, int localY) {
        if (!isInside(localX, localY)) {
            return  new Tile("VOID");
        }

        return objects.get(objectKey(localX, localY));
    }

    public void setObject(int localX, int localY, Tile tile) {
        if (!isInside(localX, localY)) {
            throw new IllegalArgumentException("Invalid tile position");
        }

        long key = objectKey(localX, localY);

        if (tile == null) {
            objects.remove(key);
        } else {
            objects.put(key, tile);
        }
    }

    private static long objectKey(int x, int y) {
        return ((long) x << 32) ^ (y & 0xffffffffL);
    }

    private static  boolean isInside(int x, int y) {
        return x >= 0 && x < SIZE &&
                y >= 0 && y < SIZE;
    }

    public int getChunkX() {
        return chunkX;
    }

    public int getChunkY() {
        return chunkY;
    }
}