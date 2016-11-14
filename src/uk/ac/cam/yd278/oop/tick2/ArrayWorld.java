package uk.ac.cam.yd278.oop.tick2;


/**
 * Created by Anchor on 2016/11/14.
 */
public class ArrayWorld extends World {
    private boolean[][] mWorld;

    public ArrayWorld(String serial) throws PatternFormatException {
        super(serial);
        mWorld = new boolean[getHeight()][getWidth()];
        getPattern().initialise(this);
    }

    @Override
    public void nextGenerationImpl() {
        boolean[][] next = new boolean[getHeight()][getWidth()];
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                next[i][j] = computeCell(i, j);
            }
        }
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                setCell(i,j,next[i][j]);
            }
        }
        mWorld = next;
    }

    @Override
    public boolean getCell(int col, int row) {
        if ((col < 0) || (col >= getWidth())) return false;
        if ((row < 0) || (row >= getHeight())) return false;
        return mWorld[row][col];
    }

    @Override
    public void setCell(int col, int row, boolean val) {
        if ((col < 0) || (col > getWidth())) return;
        if ((row < 0) || (row > getHeight())) return;
        mWorld[row][col] = val;
    }


}
