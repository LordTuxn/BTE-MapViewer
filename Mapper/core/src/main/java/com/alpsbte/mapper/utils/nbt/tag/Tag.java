package com.alpsbte.mapper.utils.nbt.tag;

/**
 * TODO
 */
public abstract class Tag {

    private final int id;

    /**
     * TODO
     * @param type - TODO
     */
    protected Tag(TagType type) {
        this.id = type.ordinal();
    }

    /**
     * TODO
     * @param b - TODO
     */
    protected Tag(byte b) {
        this.id = TagType.values()[b].ordinal();
    }

    /**
     * TODO
     * @return - TODO
     */
    public int getId() {
        return id;
    }

    /**
     * TODO
     * @return - TODO
     */
    public abstract Object getValue();

    /**
     * TODO
     * @return - TODO
     */
    public abstract String getName();

    /**
     *  TODO
     * @return - TODO
     */
    public TagType getType() {
        return TagType.values()[id];
    }

    /**
     * TODO
     * @return - TODO
     */
    protected abstract boolean equals(Tag tag);

    /**
     * TODO
     * @return - TODO
     */
    @Override
    public String toString() {
        return "(" + getType().name() + ") " + getName() + ": " + getValue();
    }



    /**
     * TODO
     * @param id - TODO
     * @return - TODO
     */
   public static Tag getTagById(byte id) {
       switch (id) {
           case 0:

           case 1:

           case 2:

           case 3:

           case 4:

           case 5:

           case 6:

           case 7:

           case 8:
               return new StringTag(id);
           case 9:

           case 10:

       }
       return null;
   }
}
