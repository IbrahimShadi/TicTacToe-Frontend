package model;

/**
 * Helper class for representing a pair of byte values.
 * Represents the coordinates in the grif of our game
 */
public class Pair {
    /**
     * First byte as coordinate
     */
    public byte fst;
    /**
     * Second byte as coordinate
     */
    public byte snd;

    /**
     * Constructs a new Pair with the specified byte values.
     *
     * @param fst first byte as coordinate
     * @param snd second byte as coordinate
     */
    public Pair(byte fst, byte snd) {
        this.fst = fst;
        this.snd = snd;
    }
}
