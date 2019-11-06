package Blokus;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

enum Polyomino 
{
	// Names from http://puzzler.sourceforge.net/docs/polyominoes-intro.html
	// The letter roughly refers to the shape, and the number is how many tiles it uses

	// The one free Monomino
	O1,
	// The one free Domino
	I2,
	// The two free Trominos
	I3, V3,
	// The five free Tertominos
	I4, L4, O4, T4, Z4,
	// The twelve free Pentominoes
	F5, I5, L5, N5, P5, T5, U5, V5, W5, X5, Y5, Z5,

	O0 // Empty tile value
}


public class BlockTray extends JPanel
{
	private static final long serialVersionUID = 1L;
	private static final int defaultSize = 500;
	private Polyomino[][] defaultLayout =
	{
		{ Polyomino.L5, Polyomino.L5, Polyomino.L5, Polyomino.L5, Polyomino.V5, Polyomino.V5, Polyomino.V5 },
		{ Polyomino.P5, Polyomino.P5, Polyomino.O0, Polyomino.L5, Polyomino.T4, Polyomino.O0, Polyomino.V5 },
		{ Polyomino.P5, Polyomino.P5, Polyomino.F5, Polyomino.T4, Polyomino.T4, Polyomino.T4, Polyomino.V5 },
		{ Polyomino.P5, Polyomino.F5, Polyomino.F5, Polyomino.F5, Polyomino.O0, Polyomino.O4, Polyomino.O4 },
		{ Polyomino.I2, Polyomino.O0, Polyomino.X5, Polyomino.F5, Polyomino.Y5, Polyomino.O4, Polyomino.O4 },
		{ Polyomino.I2, Polyomino.X5, Polyomino.X5, Polyomino.X5, Polyomino.Y5, Polyomino.Y5, Polyomino.I3 },
		{ Polyomino.L4, Polyomino.L4, Polyomino.X5, Polyomino.W5, Polyomino.Y5, Polyomino.O0, Polyomino.I3 },
		{ Polyomino.L4, Polyomino.O0, Polyomino.W5, Polyomino.W5, Polyomino.Y5, Polyomino.T5, Polyomino.I3 },
		{ Polyomino.L4, Polyomino.W5, Polyomino.W5, Polyomino.T5, Polyomino.T5, Polyomino.T5, Polyomino.I4 },
		{ Polyomino.I5, Polyomino.N5, Polyomino.V3, Polyomino.V3, Polyomino.O1, Polyomino.T5, Polyomino.I4 },
		{ Polyomino.I5, Polyomino.N5, Polyomino.N5, Polyomino.V3, Polyomino.O0, Polyomino.O0, Polyomino.I4 },
		{ Polyomino.I5, Polyomino.O0, Polyomino.N5, Polyomino.Z5, Polyomino.Z5, Polyomino.Z4, Polyomino.I4 },
		{ Polyomino.I5, Polyomino.U5, Polyomino.N5, Polyomino.U5, Polyomino.Z5, Polyomino.Z4, Polyomino.Z4 },
		{ Polyomino.I5, Polyomino.U5, Polyomino.U5, Polyomino.U5, Polyomino.Z5, Polyomino.Z5, Polyomino.Z4 }
	};

	private int width = 14;
	private int height = 7;
	private Block[][] blocks;

	public BlockTray(BlockInventory inventory, int longEdgeSize, int quarterTurns)
	{
		// Create a temp copy of the default layout, so it can be rotated without issue
		Polyomino[][] layout = defaultLayout;
		for (int i = quarterTurns; i > 0; i--)
		{
			// Rotate the layout 90 degrees `quarterTurns` times
			Polyomino[][] result = new Polyomino[height][width];
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					result[y][width-1-x] = layout[x][y];
				}
			}

			// Set the new layout and swap width/height
			layout = result;
			int temp = width;
			width = height;
			height = temp;
		}

		setLayout(new GridBagLayout());

		// Define some variables for the loop to create the Blocks
		GridBagConstraints c;
		Color color = inventory.color, background = Game.NOCOLOR, blockColor;
		Block block;
		int blockSize = longEdgeSize / (quarterTurns % 2 == 0 ? width : height);
		blocks = new Block[width][height];
		int[] edges = new int[4];
		Polyomino poly;

		// Create a Block for each space in the layout, and put it on the tray
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				// Get the type of polyomino at that grid location
				poly = layout[x][y];

				// Check each direction. If it's not OOB, and it shares a polyomino type, 
				// then don't put a border between the two blocks. Otherwise, do.
				edges[0] = (y != 0        && poly == layout[x][y-1]) ? 0 : 1;
				edges[1] = (x != 0        && poly == layout[x-1][y]) ? 0 : 1;
				edges[2] = (y != height-1 && poly == layout[x][y+1]) ? 0 : 1;
				edges[3] = (x != width-1  && poly == layout[x+1][y]) ? 0 : 1;

				// Create the gridbagconstraints
				c = new GridBagConstraints();
				c.gridx = x;
				c.gridy = y;

				// Set the color. Any empty tile will be background colored, but will 
				// still be there (primarily for structure)
				blockColor = poly != Polyomino.O0 ? color : background;

				block = new Block(blockColor, blockSize, edges, poly);
				blocks[x][y] = block;
				add(block, c);
			}
		}
	}

	public BlockTray(BlockInventory inventory, int longEdgeSize) { this(inventory, longEdgeSize, 0); }
	public BlockTray(BlockInventory inventory) { this(inventory, defaultSize, 0); }

	private class Block extends JPanel
	{
		private static final long serialVersionUID = 1L;
		private int u, l, d, r;
		private Polyomino poly;

		public Block(Color color, int size, int[] edges, Polyomino poly)
		{
			setSize(size, size);
			setPreferredSize(new Dimension(size, size));

			u = edges[0];
			l = edges[1];
			d = edges[2];
			r = edges[3];
			this.poly = poly;

			setColor(color);
		}

		public void setColor(Color color)
		{
			setBackground(color);
			setBorder(BorderFactory.createMatteBorder(u, l, d, r, color.darker()));
		}
	}
}
