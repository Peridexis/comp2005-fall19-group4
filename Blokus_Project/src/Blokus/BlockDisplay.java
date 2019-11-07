package Blokus;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlockDisplay extends JPanel
{
	private static final long serialVersionUID = 1L;
	private static final Color bgColor = Game.NOCOLOR;

	private Polyomino selected = Polyomino.O0;
	private Color color = Game.P1COLOR;
	private Tile[][] grid = new Tile[5][5];

	public BlockDisplay(int size)
	{
		setLayout(new GridLayout(5, 5));
		setSize(size, size);
		setPreferredSize(new Dimension(size, size));

		Tile tile;
		for (int y = 0; y < 5; y++)
		{
			for (int x = 0; x < 5; x++)
			{
				tile = new Tile(bgColor, size, new int[] {1, 1, 1, 1});
				grid[x][y] = tile;
				add(tile);
			}
		}
	}

	public void setPolyomino(Polyomino poly)
	{
		selected = poly;
		refresh();
	}

	public void setColor(Color color)
	{
		this.color = color;
		refresh();
	}

	public void refresh()
	{
		int[] edges = new int[4];
		for (int x = 0; x < 5; x++)
		{
			for (int y = 0; y < 5; y++)
			{
				edges[0] = (y != 0 && grid[x][y] == grid[x][y-1]) ? 0 : 1;
				edges[1] = (x != 0 && grid[x][y] == grid[x-1][y]) ? 0 : 1;
				edges[2] = (y != 4 && grid[x][y] == grid[x][y+1]) ? 0 : 1;
				edges[3] = (x != 4 && grid[x][y] == grid[x+1][y]) ? 0 : 1;

				grid[x][y].setEdges(edges);
				grid[x][y].setColor((selected.shape[x][y] == 1) ? color : bgColor);
			}
		}
	}
}
