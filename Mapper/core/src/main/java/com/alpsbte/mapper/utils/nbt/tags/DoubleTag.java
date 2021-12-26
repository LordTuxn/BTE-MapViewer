package com.alpsbte.mapper.utils.nbt.tags;

public class DoubleTag extends Tag {

    private final Object value;

    public DoubleTag(String name, Object value) {
        super(name, value);
        this.value = value;
    }

    @Override
    public Double getValue() {
        return (double) value;
    }

    @Override
    public TagType getType() {
        return TagType.TAG_DOUBLE;
    }

    @Override
    protected boolean equals(Tag tag) {
        return false;
    }
}
