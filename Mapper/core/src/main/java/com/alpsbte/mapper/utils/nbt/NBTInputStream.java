package com.alpsbte.mapper.utils.nbt;

import com.alpsbte.mapper.utils.nbt.tags.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class NBTInputStream {

    private final DataInputStream stream;

    private final Tag rootTag;
    private CompoundTag[] compoundTags = new CompoundTag[1];

    /**
     * TODO
     * @param is - TODO
     * @throws IOException - TODO
     */
    public NBTInputStream(@NotNull DataInputStream is) throws IOException {
        this.stream = is;

        TagType type = TagType.values()[is.readByte()];
        this.rootTag = (type == TagType.TAG_END) ? new EndTag() : readTag(type, is.readUTF());
        stream.close();
    }

    /**
     * TODO
     * @param name - TODO
     * @param compound - TODO
     * @return - TODO
     */
    public Tag readTagInCompound(String name, @Nullable String compound) {
        Tag tag = compound != null ? readCompoundTagByName(compound) : rootTag;
        if (tag == null) return null;

        if (tag.getName().equals(name)) {
            return tag;
        } else if (tag.getType() == TagType.TAG_COMPOUND) {
            Tag[] tags = (Tag[]) tag.getValue();
            if (tags == null) return null;

            for (Tag tagValue : tags) {
                if (tagValue.getName() == null) continue;
                if (tagValue.getName().equals(name)) return tagValue;
            }
        }
        return null;
    }

    /**
     * TODO
     * @param name - TODO
     * @return TODO
     */
    private Tag readCompoundTagByName(String name) {
        for (CompoundTag tag : compoundTags) {
            if (tag.getName().equals(name)) return tag;
        }
        return null;
    }

    /**
     * TODO
     * @param type - TODO
     * @param name - TODO
     * @return - TODO
     */
    private Tag readTag(TagType type, String name) {
        switch (type.ordinal()) {
            case 0:
                return new EndTag();
            case 1:
                return new ByteTag(name, readValue(type));
            case 2:
                return new ShortTag(name, readValue(type));
            case 3:
                return new IntTag(name, readValue(type));
            case 4:
                return new LongTag(name, readValue(type));
            case 5:
                return new FloatTag(name, readValue(type));
            case 6:
                return new DoubleTag(name, readValue(type));
            case 7:
                return new ByteArrayTag(name, readValue(type));
            case 8:
                return new StringTag(name, readValue(type));
            case 9:
                return new ListTag(name, readValue(type));
            case 10:
                CompoundTag tag = new CompoundTag(name, readValue(type));
                compoundTags[compoundTags.length - 1] = tag;
                compoundTags = Arrays.copyOf(compoundTags, compoundTags.length + 1);
                return tag;
            case 11:
                return new IntArrayTag(name, readValue(type));
            case 12:
                return new LongArrayTag(name, readValue(type));
        }
        return null;
    }

    /**
     * TODO
     * @param tag - TODO
     * @return - TODO
     */
    public Object readValue(TagType tag) {
        try {
            switch (tag.ordinal()) {
                case 1:
                    return stream.readByte();
                case 2:
                    return stream.readShort();
                case 3:
                    return stream.readInt();
                case 4:
                    return stream.readLong();
                case 5:
                    return stream.readFloat();
                case 6:
                    return stream.readDouble();
                case 7:
                    byte[] byteArray = new byte[stream.readInt()];
                    for (int i = 0; i < byteArray.length; i++) {
                        byteArray[i] = stream.readByte();
                    }
                    return byteArray;
                case 8:
                    return stream.readUTF();
                case 9:
                    TagType eleType = TagType.values()[stream.readByte()];
                    int eleLen = stream.readInt();
                    Tag[] elements = new Tag[eleLen];
                    for(int i = 0; i < eleLen; i++)
                        elements[i] = readTag(eleType, "");
                    return elements;
                case 10:
                    TagType t = null;
                    Tag[] tags = new Tag[0];
                    do {
                        byte bType = stream.readByte();
                        //if (bType < 0 || bType > 12) continue;
                        t = TagType.values()[bType];
                        String name = null;
                        if(t != TagType.TAG_END)
                            name = stream.readUTF();
                        Tag[] temp = new Tag[tags.length + 1];
                        System.arraycopy(tags, 0, temp, 0, tags.length);
                        temp[tags.length] = readTag(t, name);
                        tags = temp;
                    } while(t != TagType.TAG_END);
                    return tags;
                case 11:
                    int[] intArray = new int[stream.readInt()];
                    for (int i = 0; i < intArray.length; i++) {
                        intArray[i] = stream.readInt();
                    }
                    return intArray;
                case 12:
                    long[] longArray = new long[stream.readInt()];
                    for (int i = 0; i < longArray.length; i++) {
                        longArray[i] = stream.readLong();
                    }
                    return longArray;
            }
        } catch (IOException ex) {
            // TODO: Log error
            ex.printStackTrace();
        }
        return null;
    }
}
