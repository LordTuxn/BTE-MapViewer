package com.alpsbte.mapper.utils.nbt.tags;

public class LongArrayTag extends Tag {

    private final Object value;

    public LongArrayTag(String name, Object value) {
        super(name, value);
        this.value = value;
    }

    @Override
    public long[] getValue() {
        return (long[]) value;
    }

    @Override
    public TagType getType() {
        return TagType.TAG_LONG_ARRAY;
    }

    @Override
    protected boolean equals(Tag tag) {
        return false;
    }
}
