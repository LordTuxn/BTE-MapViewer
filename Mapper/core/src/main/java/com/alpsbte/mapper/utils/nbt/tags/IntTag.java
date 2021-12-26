package com.alpsbte.mapper.utils.nbt.tags;

public class IntTag extends Tag {

    private final Object value;

    public IntTag(String name, Object value) {
        super(name, value);
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return (int) value;
    }

    @Override
    public TagType getType() {
        return TagType.TAG_INT;
    }

    @Override
    protected boolean equals(Tag tag) {
        return false;
    }
}
