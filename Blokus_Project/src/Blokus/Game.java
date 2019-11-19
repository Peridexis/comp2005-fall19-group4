package Blokus;
import java.awt.Color;

public class Game {
	public static final int PLAYER1 = 1;
	public static final int PLAYER2 = 2;
	public static final int PLAYER3 = 3;
	public static final int PLAYER4 = 4;
	public static final int NOPLAYER = 5;
	public static final int INVALID = 6;

	public static final Color P1COLOR = Color.BLUE;
	public static final Color P2COLOR = Color.YELLOW;
	public static final Color P3COLOR = Color.RED;
	public static final Color P4COLOR = Color.GREEN;
	public static final Color NOCOLOR = Color.GRAY;

	public static final int size = 20;

	private int[][] board;
	private boolean[][] hovering;
	private Polyomino selected = Polyomino.O0;
	private int cwRotations = 0;
	private boolean flipped = false;
	private int active = PLAYER1;
	private RefreshListner refreshListner;
	private NextTurnListener nextTurnListener;

	public Game()
	{
		hovering = new boolean[size][size];
		board = new int[size][size];
		for (int x = 0; x < size; x++)
		{
			for (int y = 0; y < size; y++)
			{
				board[x][y] = NOPLAYER;
			}
		}
	}

	public static Color getColorFor(int player) 
	{
		switch (player)
		{
			case PLAYER1: return P1COLOR;
			case PLAYER2: return P2COLOR;
			case PLAYER3: return P3COLOR;
			case PLAYER4: return P4COLOR;
			default: return NOCOLOR;
		}
	}

	public static int getNextPlayer(int player)
	{
		switch (player)
		{
			case PLAYER1: return PLAYER2;
			case PLAYER2: return PLAYER3;
			case PLAYER3: return PLAYER4;
			case PLAYER4: return PLAYER1;
			default: return NOPLAYER;
		}
	}

	public Color getActiveColor()
	{
		return getColorFor(active);
	}

	public int getStateAt(int x, int y)
	{
		if (x < 0 || y < 0 || x >= size || y >= size)
		{
			return INVALID;
		}

		return board[x][y];
	}

	public boolean isSelected(Polyomino poly)
	{
		if (poly == Polyomino.O0) { return false; }
		return poly == selected;
	}

	public boolean isSelected()
	{
		return selected != Polyomino.O0;
	}

	public void deselect()
	{
		selected = Polyomino.O0;
	}

	public void nextTurn()
	{
		active = getNextPlayer(active);
		selected = Polyomino.O0;
		cwRotations = 0;
		flipped = false;
	}

	public int[][] getTranslatedSelected()
	{
		return selected.rotatedFlipped(cwRotations, flipped);
	}

	public boolean isHoveringOver(int x, int y)
	{
		return hovering[x][y];
	}

	public void hoverSelectedAt(int atX, int atY)
	{
		int polX, polY;
		int[][] target = getTranslatedSelected();
		for (int x = 0; x < size; x++)
		{
			for (int y = 0; y < size; y++)
			{
				hovering[x][y] = false;
			}
		}

		for (int x = 0; x < 5; x++)
		{
			for (int y = 0; y < 5; y++)
			{
				polX = atX - 2 + x;
				polY = atY - 2 + y;
				if (target[x][y] == 1
				&&  polX < size && polX >= 0 
				&&  polY < size && polY >= 0)
				{
					hovering[polX][polY] = true;
				}
			}
		}
	}

	public void place(int xLocation, int yLocation)
	{
		if (!isLegal(xLocation, yLocation)) { return; }
		
		for (int x = 0; x < size; x++)
		{
			for (int y = 0; y < size; y++)
			{
				if (hovering[x][y])
				{
					hovering[x][y] = false;
					board[x][y] = active;
				}
			}
		}

		nextTurnListener.nextTurn();
	}

	public boolean isLegal(int xLocation, int yLocation)
	{
		int[][] target = getTranslatedSelected();
		int[][] othoDirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
		int[][] diagDirs = {{1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
		int x, y, xDir, yDir;
		boolean foundCorner = false;
		for (int xOffset = 0; xOffset < 5; xOffset++)
		{
			for (int yOffset = 0; yOffset < 5; yOffset++)
			{
				// If it's not an occupied place on the 5X5, don't bother checking it
				if (target[xOffset][yOffset] == 0) { continue; }

				x = xLocation + xOffset - 2;
				y = yLocation + yOffset - 2;

				// Check if it's OOB
				if (x > size || y > size) { return false; }

				// Check if it's occupied
				if (board[x][y] != NOPLAYER) { return false; }

				// Check if any edges match in color
				for (int[] dir : othoDirs)
				{
					xDir = x + dir[0];
					yDir = y + dir[1];

					// Any edges next to the board's edge are valid
					if (xDir < 0 || xDir >= size
					||  yDir < 0 || yDir >= size)
					{
						continue;
					}

					if (board[xDir][yDir] == active)
					{
						return false;
					}
				}

				// Check for any valid corners
				for (int[] dir : diagDirs)
				{
					xDir = x + dir[0];
					yDir = y + dir[1];

					// Any corners next to the board's edge don't count
					if (xDir < 0 || xDir >= size
					||  yDir < 0 || yDir >= size)
					{
						continue;
					}

					if (board[xDir][yDir] == active)
					{
						foundCorner = true;
					}
				}

				// Check for valid starting corners too
				if ((x == 0 || x == size - 1)
				&&  (y == 0 || y == size - 1))
				{
					foundCorner = true; 
					// TODO this should allow each player one starting corner
				}
			}
		}

		return foundCorner;
	}

	public void rotate(int times)
	{
		cwRotations += times;
		cwRotations %= 4;
		refreshListner.refresh();
	}

	public void flip()
	{
		flipped = !flipped;
		refreshListner.refresh();
	}

	public void addRefreshListner(RefreshListner lstn)
	{
		refreshListner = lstn;
	}

	public void addNextTurnListener(NextTurnListener lstn)
	{
		nextTurnListener = lstn;
	}

	public void setSelected(Polyomino poly) { selected = poly; }
	public Polyomino getSelected() { return selected; }
	public void setActive(int poly) { active = poly; }
	public int getActive() { return active; }
}
