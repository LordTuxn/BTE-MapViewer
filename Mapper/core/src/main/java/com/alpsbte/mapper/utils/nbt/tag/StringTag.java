package com.alpsbte.mapper.utils.nbt.tag;

import com.alpsbte.mapper.utils.nbt.tag.Tag;

public class StringTag extends Tag {

    public StringTag(byte type) {
        super(type);
    }

    @Override
    public String getValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getName() {
        return "TAG_String";
    }

    @Override
    public boolean equals(Tag tag) {
        return tag.getName().equals(getName()) && tag.getType().equals(getType()) && tag.getValue().equals(getValue());
    }
}
