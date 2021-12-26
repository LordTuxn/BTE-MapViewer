package com.alpsbte.mapper.utils.nbt.tags;

/**
 * TODO
 */
public abstract class Tag {

    private final String name;
    private final Object value;

    /**
     * TODO
     * @param name - TODO
     * @param value - TODO
     */
    public Tag(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    /**
     * TODO
     * @return - TODO
     */
    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    /**
     *  TODO
     * @return - TODO
     */
    public abstract TagType getType();

    /**
     * TODO
     * @return - TODO
     */
    protected abstract boolean equals(Tag tag);

    /**
     * TODO
     * @param o - TODO
     * @return - TODO
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Tag) this.equals((Tag) o);
        return false;
    }
}
