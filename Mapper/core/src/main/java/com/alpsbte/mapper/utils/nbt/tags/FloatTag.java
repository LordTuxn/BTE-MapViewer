package com.alpsbte.mapper.utils.nbt.tags;

public class FloatTag extends Tag {

    private final Object value;

    public FloatTag(String name, Object value) {
        super(name, value);
        this.value = value;
    }

    @Override
    public Float getValue() {
        return (float) value;
    }

    @Override
    public TagType getType() {
        return TagType.TAG_FLOAT;
    }

    @Override
    protected boolean equals(Tag tag) {
        return false;
    }
}
