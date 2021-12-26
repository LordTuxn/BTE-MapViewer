package com.alpsbte.mapper.utils.nbt.tags;

public class ByteArrayTag extends Tag {

    private final Object value;

    public ByteArrayTag(String name, Object value) {
        super(name, value);
        this.value = value;
    }

    @Override
    public byte[] getValue() {
        return (byte[]) value;
    }

    @Override
    public TagType getType() {
        return TagType.TAG_BYTE_ARRAY;
    }

    @Override
    protected boolean equals(Tag tag) {
        return false;
    }
}
