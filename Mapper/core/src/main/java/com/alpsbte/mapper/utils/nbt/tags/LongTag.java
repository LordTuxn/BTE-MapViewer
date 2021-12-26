package com.alpsbte.mapper.utils.nbt.tags;

public class LongTag extends Tag {

    private final Object value;

    public LongTag(String name, Object value) {
        super(name, value);
        this.value = value;
    }

    @Override
    public Long getValue() {
        return (long) value;
    }

    @Override
    public TagType getType() {
        return TagType.TAG_LONG;
    }

    @Override
    protected boolean equals(Tag tag) {
        return false;
    }
}
