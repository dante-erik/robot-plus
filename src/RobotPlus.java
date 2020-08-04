import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import math.FastMath;

public class RobotPlus extends Robot {
	
	/**
	 * Maximum duration for mouseMove
	 */
    private final static int MAXIMUM_TIME_MOVE = 64;

    /**
     * Map<Character, Integer> connecting the Characters
     * to their KeyEvent code (which is an integer)
     */
    private final static HashMap<Character, Integer> keyLookup = new HashMap<>(37);

    /*
      Initialize values in the map.

      This runs as the program first scans this file and never again.
      The benefit is that the memory is allocated once and not de-allocated until
      the program terminates. This means that there is no unnecessary time wasted
      on writing things into memory.

      You can't write a Javadoc for static, so this is just a multiline comment.
     */
    static {
        keyLookup.put(' ', KeyEvent.VK_SPACE);
        keyLookup.put('a', KeyEvent.VK_A);
        keyLookup.put('b', KeyEvent.VK_B);
        keyLookup.put('c', KeyEvent.VK_C);
        keyLookup.put('d', KeyEvent.VK_D);
        keyLookup.put('e', KeyEvent.VK_E);
        keyLookup.put('f', KeyEvent.VK_F);
        keyLookup.put('g', KeyEvent.VK_G);
        keyLookup.put('h', KeyEvent.VK_H);
        keyLookup.put('i', KeyEvent.VK_I);
        keyLookup.put('j', KeyEvent.VK_J);
        keyLookup.put('k', KeyEvent.VK_K);
        keyLookup.put('l', KeyEvent.VK_L);
        keyLookup.put('m', KeyEvent.VK_M);
        keyLookup.put('n', KeyEvent.VK_N);
        keyLookup.put('o', KeyEvent.VK_O);
        keyLookup.put('p', KeyEvent.VK_P);
        keyLookup.put('q', KeyEvent.VK_Q);
        keyLookup.put('r', KeyEvent.VK_R);
        keyLookup.put('s', KeyEvent.VK_S);
        keyLookup.put('t', KeyEvent.VK_T);
        keyLookup.put('u', KeyEvent.VK_U);
        keyLookup.put('v', KeyEvent.VK_V);
        keyLookup.put('w', KeyEvent.VK_W);
        keyLookup.put('x', KeyEvent.VK_X);
        keyLookup.put('y', KeyEvent.VK_Y);
        keyLookup.put('z', KeyEvent.VK_Z);
        keyLookup.put('0', KeyEvent.VK_0);
        keyLookup.put('1', KeyEvent.VK_1);
        keyLookup.put('2', KeyEvent.VK_2);
        keyLookup.put('3', KeyEvent.VK_3);
        keyLookup.put('4', KeyEvent.VK_4);
        keyLookup.put('5', KeyEvent.VK_5);
        keyLookup.put('6', KeyEvent.VK_6);
        keyLookup.put('7', KeyEvent.VK_7);
        keyLookup.put('8', KeyEvent.VK_8);
        keyLookup.put('9', KeyEvent.VK_9);
    }

    /**
     * Milliseconds waited after standard method calls.
     * <p>
     * Some methods will not include this wait time.
     */
    private int autoDelay;

    /**
     * Creates a default RobotPlus with 0 autoDelay
     *
     * @throws AWTException If the construction of the parent Robot throws.
     * @author Erik Barbieri <Dohinkus>
     * @see Robot
     */
    public RobotPlus() throws AWTException
    {
		super();
		//use my delay, robot's calls too many times per method
		autoDelay = 0;
    }

    /**
     * Creates a default RobotPlus with a given autoDelay
     *
     * @param milliseconds Number of milliseconds to set the initial autoDelay
     * @throws AWTException If the construction of the parent Robot throws.
     * @author Erik Barbieri <Dohinkus>
     * @see Robot
     */
    public RobotPlus(final int milliseconds) throws AWTException
    {
		super();
		//use my delay, robot's calls too many times per method
		autoDelay = milliseconds;
    }

    /**
     * Moves the cursor linearly from its current position to
     * the given coordinates with a calculated duration.
     *
     * @param xEnd Final x-coordinate for cursor after move
     * @param yEnd Final y-coordinate for cursor after move
     * @author Erik Barbieri <Dohinkus>
     */
    @Override
    public void mouseMove(final int xEnd, final int yEnd)
    {
        final var xStart = MouseInfo.getPointerInfo().getLocation().getX();
        final var yStart = MouseInfo.getPointerInfo().getLocation().getY();
        //an approximation of the distance formula for the distance between the cursor and the inputted x and y
        final var milliseconds = Math.min(FastMath.fastDistance((int) xStart, (int) yStart, xEnd, yEnd), MAXIMUM_TIME_MOVE);
      
        mouseMove(xEnd, yEnd, (int) Math.round(milliseconds));
    }

    /**
     * Moves the cursor linearly from its current position to
     * the given coordinates in the given duration.
     *
     * @param xEnd Final x-coordinate for cursor after move
     * @param yEnd Final y-coordinate for cursor after move
     * @param milliseconds Time (in ms) to move to given position
     * @author Erik Barbieri <Dohinkus>
     */
    public void mouseMove(final int xEnd, final int yEnd, final int milliseconds)
    {
        final var xStart = MouseInfo.getPointerInfo().getLocation().getX();
        final var yStart = MouseInfo.getPointerInfo().getLocation().getY();
        final var xDiff = xEnd - xStart;
        final var yDiff = yEnd - yStart;

        //currentMillisecond starting at 0 would move the cursor to its current location (pointless action), so currentMillisecond starts at 1
        for (var currentMillisecond = 1; currentMillisecond <= milliseconds; ++currentMillisecond)
		{
            //move cursor to new x y based on currentMillisecond
            super.mouseMove((int) ((((double)currentMillisecond / milliseconds) * xDiff) + xStart),
                    (int) ((((double)currentMillisecond / milliseconds) * yDiff) + yStart));
			//each super.mouseMove call should take about 1 ms, so delay(1);
			delay(1);
		}

        if (autoDelay > 0)
            delay(autoDelay);
    }

    /**
     * Clicks either left or right click and holds for 25 ms.
     *
     * 1 - Left Click
     * 2 - Right Click
     * default - No Click
     *
     * @param button Which of the two buttons to click
     * @throws IllegalArgumentException Calling mousePress (and mouseRelease) can throw this
     * @see Robot
     * @author Erik Barbieri <Dohinkus>
     */
    public void mouseClick(final int button)
    {
        mouseClick(button, 25);
    }

    /**
     * Clicks either left or right click and holds for the given duration.
     *
     * 1 - Left Click
     * 2 - Right Click
     * default - No Click
     *
     * @param button Which of the two buttons to click
     * @param milliseconds How long to hold the button down
     * @see Robot
     * @author Erik Barbieri <Dohinkus>
     */
    public void mouseClick(final int button, final int milliseconds)
    {
		try
		{
			//left click
			if (button == 1)
			{
				mousePress(InputEvent.BUTTON1_DOWN_MASK);
				delay(milliseconds);
				mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			}
			//right click
			if (button == 2)
			{
				mousePress(InputEvent.BUTTON3_DOWN_MASK);
				delay(milliseconds);
				mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
			}

			if (autoDelay > 0)
				delay(autoDelay);
		}
		catch(final IllegalArgumentException ex)
		{
			System.out.println("IllegalArgumentException in mouseClick(int button, int milliseconds)");
		}
    }

    /**
     * Clicks every key in a given String (in order) with each keypress lasting 50 ms.
     *
     * @param str String of keys to press (must be lowercase)
     * @see Robot
     * @author Erik Barbieri <Dohinkus>
     */
    public void keyClick(final String str)
    {
        keyClick(str, 50);
    }

    /**
     * Clicks every key in a given String (in order) with
     * each keypress lasting the given number of milliseconds.
     *
     * @param str String of keys to press (must be lowercase)
     * @param milliseconds Time to hold down each key during keypress
     * @see Robot
     * @author Erik Barbieri <Dohinkus>
     */
    public void keyClick(final String str, final int milliseconds)
    {
        //[2][36] space, a-z, 0-9
        //[0][i] for .equals check
        //[1][i] for robot
        for (int i = 0; i < str.length(); i++)
        {
            final var keyCode = keyLookup.get(str.charAt(i));
            keyPress(keyCode);
            delay(milliseconds);
            keyRelease(keyCode);
        }

        if (autoDelay > 0)
            delay(autoDelay);
    }

    /**
     * Converts a String into an ArrayList of KeyEvents in O(n) time,
     * where n is the length of the string.
     * <p>
     * This is accomplished by using a HashMap to store links between
     * characters and their KeyEvent codes.
     *
     * @param str String to convert to KeyEvents
     * @return ArrayList<Integer> An ArrayList containing the KeyEvent codes for each character in the String in order
     * @author Dante Barbieri <pulchroxloom>
     * @see ArrayList
     * @see HashMap
     * @see KeyEvent
     * @deprecated
     */
    @Deprecated
    protected ArrayList<Integer> stringToKeypress(final String str)
    {
        final var keyPresses = new ArrayList<Integer>(str.length());
        for (var i = 0; i < str.length(); ++i)
            keyPresses.add(keyLookup.get(str.charAt(i)));
        return keyPresses;
    }

    /**
     * Clicks a key associated to the given key code for 50 ms.
     *
     * @param key KeyEvent code of key to press
     * @see Robot
     * @author Erik Barbieri <Dohinkus>
     */
    public void keyClick(final int key)
    {
        keyClick(key, 50);
    }

    /**
     * Clicks a key associated to the given key code for
     * the given amount of time.
     *
     * @param key KeyEvent code of key to press
     * @param milliseconds Time to hold the key down
     * @see Robot
     * @author Erik Barbieri <Dohinkus>
     */
    public void keyClick(final int key, final int milliseconds)
    {
        keyPress(key);
        delay(milliseconds);
        keyRelease(key);

        if (autoDelay > 0)
            delay(autoDelay);
    }
	
	/**
	 * Sets local autoDelay int
	 * 
	 * @param milliseconds value
	 * @author Erik Barbieri <Dohinkus>
	 */
	@Override
	public void setAutoDelay(final int milliseconds)
	{
		autoDelay = milliseconds;
	}
	
	/**
	 * Returns autoDelay
	 *
	 * @author Erik Barbieri <Dohinkus>
	 */
	@Override
	public int getAutoDelay()
	{
		return autoDelay;
	}
}
