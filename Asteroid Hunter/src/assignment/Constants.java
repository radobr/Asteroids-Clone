package assignment;

import utilities.JEasyFrameFull;

import java.awt.*;

/**
 * Created by radobr on 20/01/2017.
 */
public final class Constants {
    public static final int FRAME_HEIGHT = JEasyFrameFull.HEIGHT;
    public static final int FRAME_WIDTH = JEasyFrameFull.WIDTH;
    public static final int DELAY = 20;  // in milliseconds
    public static final double DT = DELAY / 1000.0;  // in seconds
    public static final Dimension FRAME_SIZE = new Dimension(
            Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
}
