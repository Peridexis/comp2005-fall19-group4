package Blokus;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlockDisplay extends JPanel
{
	private static final long serialVersionUID = 1L;
	private static final Color bgColor = Game.NOCOLOR;

	private Tile[][] grid = new Tile[5][5];
	private Game game;

	public BlockDisplay(int size, Game game)
	{
		this.game = game;
		setLayout(new GridLayout(5, 5));
		setSize(size, size);
		setPreferredSize(new Dimension(size, size));

		Tile tile;
		for (int y = 0; y < 5; y++)
		{
			for (int x = 0; x < 5; x++)
			{
				tile = new Tile(size, bgColor);
				grid[x][y] = tile;
				add(tile);
			}
		}
	}

	public void refresh()
	{
		int[][] target = game.getTranslatedSelected();
		int[] edges = new int[4];
		Tile tile;
		Color color = Game.getColorFor(game.getActive());
		for (int x = 0; x < 5; x++)
		{
			for (int y = 0; y < 5; y++)
			{
				tile = grid[x][y];
				tile.color = (target[x][y] == 1) ? color : bgColor;
				tile.refresh();
			}
		}
	}

	public void setGame(Game game) { this.game = game; }
}
