package com.alpsbte.mapper;

import java.io.DataInputStream;
import java.io.File;

public abstract class AbstractRegionFile {

    protected int[] offsets;
    protected byte[] sectors;

    private final File file;

    /**
     * Starts reading the region file
     * @param regionFile - path to region file
     */
    protected AbstractRegionFile(File regionFile) {
        this.file = regionFile;
        if (regionFile.exists() && regionFile.canRead()) {
            readRegion(regionFile);
        }
    }

    /**
     * The offset for a chunk (x, z) begins at byte value 4*(x+z*32).
     * Since every region file begins with a 4 KB header that describes where chunks are stored, we have to
     * multiply it by the chunk position. The chunk position is calculated by adding the x and z value and multiply
     * it by 32, since every region files stores a group of 32x32 chunks.
     */
    protected abstract void readRegion(File regionFile);


    /**
     * Reads uncompressed region file and returns chunk data
     * @param chunkX - relative chunk x
     * @param chunkZ - relative chunk z
     * @return chunk data
     */
    public abstract DataInputStream readChunkData(int chunkX, int chunkZ, File regionFile);


    /**
     * Checks if a given chunk is valid
     * @param chunkX - relative chunk x
     * @param chunkZ - relative chunk z
     * @return true if chunk is valid (in region file)
     */
    protected abstract boolean outOfBounds(int chunkX, int chunkZ);


    /**
     * Returns chunk offset in region file
     * {@link #readRegion(File)}}
     *
     * @param chunkX - relative chunk x
     * @param chunkZ - relative chunk z
     * @return chunk offset
     */
    protected int getChunkOffset(int chunkX, int chunkZ) {
        return offsets[chunkX + chunkZ * 32];
    }

    public File getFile() {
        return file;
    }
}
