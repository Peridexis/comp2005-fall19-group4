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
	public Polyomino selected = Polyomino.O0;
	public Color color = P1COLOR;
	public int active = PLAYER1;

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
		switch (active)
		{
			case PLAYER1:	active = PLAYER2;
							color = P2COLOR;
							break;
			case PLAYER2:	active = PLAYER3;
							color = P3COLOR;
							break;
			case PLAYER3:	active = PLAYER4;
							color = P4COLOR;
							break;
			case PLAYER4:	active = PLAYER1;
							color = P1COLOR;
							break;
		}
	}

	public void hoverOver(int x, int y)
	{
		hovering[x][y] = true;
	}

	public void stopHovering(int x, int y)
	{
		hovering[x][y] = false;
	}

	public boolean isHoveringOver(int x, int y)
	{
		return hovering[x][y];
	}

	public void hoverSelectedAt(int atX, int atY)
	{
		int polX, polY;
		for (int x = 0; x < size; x++)
		{
			for (int y = 0; y < size; y++)
			{
				if (Math.abs(atX - x) <= 2
				&&  Math.abs(atY - y) <= 2)
				{
					polX = atX + 2 - x;
					polY = atY + 2 - y;
					if (selected.shape[polX][polY] == 1)
					{
						hoverOver(x, y);
					}
					else
					{
						stopHovering(x, y);
					}
				}
				else
				{
					stopHovering(x, y);
				}
			}
		}
	}
}
