package com.alpsbte.mapper;

import com.alpsbte.mapper.utils.nbt.NBTInputStream;
import com.alpsbte.mapper.utils.nbt.tags.Tag;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class ChunkDataTest {

    private static MCARegionFile regionFile;

    private static int[][] chunkDataSet() {
        return new int[][] { { 0 , 0 }, { 0 , 9 }, { 2 , 15 }, { 8 , 2 } };
    }

    @BeforeAll
    static void setUp() {
        regionFile = new MCARegionFile(new File(Objects.requireNonNull(ChunkDataTest.class.getClassLoader().getResource("r.-1.-8.mca")).getFile()));
    }

    @ParameterizedTest
    @MethodSource(value = "chunkDataSet")
    public void readSingleChunk(int[] chunkXZ) {
        readChunk(chunkXZ[0], chunkXZ[1], regionFile.readChunkData(chunkXZ[0], chunkXZ[1], regionFile.getFile()));
    }

    @Test
    public void readAllChunks() {
        for (int x = 0; x < 32; x++) {
            for (int z = 0; z < 32; z++) {
                readChunk(x, z, regionFile.readChunkData(x, z, regionFile.getFile()));
            }
        }
    }

    private void readChunk(int x, int z, DataInputStream input) {
        try {
            if (input != null) {
                NBTInputStream stream = new NBTInputStream(input);
                System.out.println("--- Chunk Data for x = " + x + " | z = " + z + " ---");

                int dataVersion = (int) stream.readTagInCompound("DataVersion", null).getValue();
                System.out.println("DataVersion: " + dataVersion);

                String status = (String) stream.readTagInCompound("Status", "Level").getValue();
                System.out.println("Status: " + status);

                int[] biomes = (int[]) stream.readTagInCompound("Biomes", "Level").getValue();
                System.out.println("Biomes: " + String.join(", ",
                        Arrays.stream(biomes).mapToObj(String::valueOf).toArray(String[]::new)));

                Tag[] sections = (Tag[]) stream.readTagInCompound("Sections", "Level").getValue();
                System.out.println("Sections: " + sections.length);

                System.out.println();
            }
        } catch (IOException ex) {
            fail("Could not read chunk at x: " + x + ", y: " + z, ex);
        }
    }
}
