package com.alpsbte.mapper.utils.nbt.tags;

public class EndTag extends Tag {

    public EndTag() {
        super(null, null);
    }

    @Override
    public TagType getType() {
        return TagType.TAG_END;
    }

    @Override
    protected boolean equals(Tag tag) {
        return false;
    }
}
