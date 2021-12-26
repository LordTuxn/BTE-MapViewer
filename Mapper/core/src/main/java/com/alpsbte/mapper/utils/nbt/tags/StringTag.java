package com.alpsbte.mapper.utils.nbt.tags;

public class StringTag extends Tag {

    private final Object value;

    public StringTag(String name, Object value) {
        super(name, value);
        this.value = value;
    }

    @Override
    public String getValue() {
        return (String) value;
    }

    @Override
    public TagType getType() {
        return TagType.TAG_STRING;
    }

    @Override
    public boolean equals(Tag tag) {
        return tag.getType().equals(getType()) && tag.getValue().equals(getValue());
    }
}
