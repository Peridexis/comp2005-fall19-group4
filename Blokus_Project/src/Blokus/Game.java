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
	private int cwRotations = 0;
	private boolean flipped = false;
	public int active = PLAYER1;
	private RefreshListner refreshListner;

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
}
