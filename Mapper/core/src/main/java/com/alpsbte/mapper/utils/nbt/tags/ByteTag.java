package com.alpsbte.mapper.utils.nbt.tags;

public class ByteTag extends Tag {

    private final Object value;

    public ByteTag(String name, Object value) {
        super(name, value);
        this.value = value;
    }

    @Override
    public Byte getValue() {
        return (byte) value;
    }

    @Override
    public TagType getType() {
        return TagType.TAG_BYTE;
    }

    @Override
    protected boolean equals(Tag tag) {
        return false;
    }
}
