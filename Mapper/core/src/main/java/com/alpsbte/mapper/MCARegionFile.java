package com.alpsbte.mapper;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

/**
 * Region File Anvil: r.x.z.mca
 * {@see <a href="https://minecraft.fandom.com/wiki/Anvil_file_format"></a>}
 */
public class MCARegionFile extends AbstractRegionFile {

    private static final int GZIP_TYPE = 1;
    private static final int DEFLATE_TYPE = 2;

    private static final int REGION_SIZE = 32;
    private static final int HEADER_SIZE = 4096;
    private static final int OFFSET_COUNT = HEADER_SIZE / 4;

    protected MCARegionFile(File regionFile) {
        super(regionFile);
    }

    @Override
    protected void readRegion(File regionFile) {
        // To read the NBT data, we need to access it by using the RandomAccessFile class
        try (RandomAccessFile region = new RandomAccessFile(regionFile, "r")) {
            offsets = new int[OFFSET_COUNT];
            sectors = new byte[OFFSET_COUNT];

            region.seek(0);
            for (int i = 0; i < offsets.length; i++) {
                int offset = (region.read()) << 16;
                offset |= (region.read() & 0xFF) << 8;
                offsets[i] = offset | region.read() & 0xFF;
                sectors[i] = region.readByte();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public DataInputStream readChunkData(int chunkX, int chunkZ, File regionFile) {
        try {
            RandomAccessFile region = new RandomAccessFile(regionFile, "r"); // mode r = read
            if (outOfBounds(chunkX, chunkZ)) return null; // if chunk is outside the region, return null

            // Read chunk offset
            int offset = getChunkOffset(chunkX, chunkZ);
            if (offset == 0) return null; // If offset is 0, chunk is not generated

            region.seek((long) offset << 12); // Multiply offset by 4096
            region.skipBytes(4); // Skip data length
            int compressionType = region.readByte(); // Read data compression type

            // Check for compression type
            if (compressionType == GZIP_TYPE) {
                return new DataInputStream(new GZIPInputStream(new FileInputStream(region.getFD())));
            } else if (compressionType == DEFLATE_TYPE) {
                return new DataInputStream(new InflaterInputStream(new FileInputStream(region.getFD())));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    @Override
    protected boolean outOfBounds(int chunkX, int chunkZ) {
        return chunkX < 0 || chunkZ < 0 || chunkX >= REGION_SIZE || chunkZ >= REGION_SIZE;
    }
}
