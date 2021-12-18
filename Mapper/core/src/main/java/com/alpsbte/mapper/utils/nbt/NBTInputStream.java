package com.alpsbte.mapper.utils.nbt;

import com.alpsbte.mapper.utils.nbt.tag.Tag;
import com.alpsbte.mapper.utils.nbt.tag.TagType;

import java.io.DataInputStream;
import java.io.IOException;

public class NBTInputStream implements AutoCloseable {

    private DataInputStream stream;

    public NBTInputStream(DataInputStream inputStream) throws IOException {
        if (inputStream != null) {
            this.stream = new DataInputStream(inputStream);

            //TagType type = Tag.getTagById(inputStream.readByte()).getType();
            // TODO
        }
    }

    public Tag readTag(String name) {
        // TODO
        return null;
    }

    public Object readValue(TagType type) throws IOException {
        switch (type) {
            case TAG_BYTE:
                return stream.readByte();
            case TAG_SHORT:
                return stream.readShort();
            case TAG_INT:
                return stream.readInt();
            case TAG_LONG:
                return stream.readLong();
            case TAG_FLOAT:
                return stream.readFloat();
            case TAG_DOUBLE:
                return stream.readDouble();
            case TAG_STRING:
                return stream.readUTF();
            case TAG_LIST:
                // TODO
            case TAG_BYTE_ARRAY:
                return stream.read(new byte[stream.readInt()]);
            case TAG_COMPOUND:
                // TODO
        }
        return null;
    }

    public DataInputStream getStream() {
        return stream;
    }

    @Override
    public void close() throws Exception {
        stream.close();
    }
}
