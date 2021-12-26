package com.alpsbte.mapper.utils.nbt.tags;

public class IntArrayTag extends Tag {

    private final Object value;

    public IntArrayTag(String name, Object value) {
        super(name, value);
        this.value = value;
    }

    @Override
    public int[] getValue() {
        return (int[]) value;
    }

    @Override
    public TagType getType() {
        return TagType.TAG_INT_ARRAY;
    }

    @Override
    protected boolean equals(Tag tag) {
        return false;
    }
}
