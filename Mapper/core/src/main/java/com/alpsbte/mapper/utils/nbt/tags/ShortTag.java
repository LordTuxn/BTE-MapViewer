package com.alpsbte.mapper.utils.nbt.tags;

public class ShortTag extends Tag {

    private final Object value;

    public ShortTag(String name, Object value) {
        super(name, value);
        this.value = value;
    }

    @Override
    public Short getValue() {
        return (short) value;
    }

    @Override
    public TagType getType() {
        return TagType.TAG_SHORT;
    }

    @Override
    protected boolean equals(Tag tag) {
        return false;
    }
}
