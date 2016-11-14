package uk.ac.cam.yd278.oop.tick2;


/**
 * Created by Anchor on 2016/11/14.
 */
public class PackedWorld extends World {
    private long mWorld = 0;

    public PackedWorld(String serial) throws PatternFormatException {
        super(serial);
        if ((getWidth() > 8) || (getHeight() > 8)) throw new PatternFormatException(
                getHeight()+"-by-"+getWidth()+" is too big for a packed long");
        getPattern().initialise(this);
    }

    @Override
    public void nextGenerationImpl() {
        long next = 0;
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                next = set(next, i * 8 + j, computeCell(i, j));
            }
        }
        mWorld = next;
    }

    private boolean get(long world, int position) {
        long check = (world >>> position) & 1L;

        return (check == 1);
    }

    private long set(long world, int position, boolean value) {
        if (value) {
            world = world | (1L << position);
        } else {
            world = world & ~(1L << position);
        }
        return world;
    }

    @Override
    public boolean getCell(int col, int row) {
        if ((col < 0) || (col > getWidth())) return false;
        if ((row < 0) || (row > getHeight())) return false;
        return get(mWorld, row * 8 + col);
    }

    @Override
    public void setCell(int col, int row, boolean val) {
        if ((col < 0) || (col > getWidth())) return;
        if ((row < 0) || (row > getHeight())) return;
        mWorld = set(mWorld, row * 8 + col, val);

    }
}
