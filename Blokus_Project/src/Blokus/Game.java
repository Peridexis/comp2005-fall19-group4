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

	public int width = 20;
	public int height = 20;
	private int[][] board;

	public Game()
	{
		board = new int[width][height];
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				board[x][y] = NOPLAYER;
			}
		}
	}

	public int getStateAt(int x, int y)
	{
		if (x < 0 || y < 0 || x >= width || y >= height)
		{
			return INVALID;
		}

		return board[x][y];
	}
}
