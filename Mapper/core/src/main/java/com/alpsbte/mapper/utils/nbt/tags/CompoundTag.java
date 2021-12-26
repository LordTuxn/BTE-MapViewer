package com.alpsbte.mapper.utils.nbt.tags;

public class CompoundTag extends Tag {

    private final Object value;

    public CompoundTag(String name, Object value) {
        super(name, value);
        this.value = value;
    }

    @Override
    public Tag[] getValue() {
        return (Tag[]) value;
    }

    @Override
    public TagType getType() {
        return TagType.TAG_COMPOUND;
    }

    @Override
    protected boolean equals(Tag tag) {
        return false;
    }
}
