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

	private int width = 14;
	private int height = 7;
	private Polyomino[] hidden = new Polyomino[21];
	private Polyomino[][] layout =
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

	public BlockTray(Color playerColor, int longEdgeSize, int quarterTurns)
	{
		for (int i = quarterTurns; i > 0; i--)
		{
			Polyomino[][] result = new Polyomino[height][width];
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					result[y][width-1-x] = layout[x][y];
				}
			}

			layout = result;
			int temp = width;
			width = height;
			height = temp;
		}

		setLayout(new GridBagLayout());
		setBackground(Game.NOCOLOR);

		GridBagConstraints c;
		Color color;
		Color background = Game.NOCOLOR;
		int blockSize = longEdgeSize / (quarterTurns % 2 == 0 ? width : height);

		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				int[] edges = {1, 1, 1, 1};

				if (y != 0        && layout[x][y] == layout[x][y-1]) { edges[0] = 0; }
				if (x != 0        && layout[x][y] == layout[x-1][y]) { edges[1] = 0; }
				if (y != height-1 && layout[x][y] == layout[x][y+1]) { edges[2] = 0; }
				if (x != width-1  && layout[x][y] == layout[x+1][y]) { edges[3] = 0; }

				c = new GridBagConstraints();
				c.gridx = x;
				c.gridy = y;

				color = layout[x][y] != Polyomino.O0 ? playerColor : background;

				add(new Block(color, blockSize, edges), c);
			}
		}
	}

	public BlockTray(Color color, int longEdgeSize) { this(color, longEdgeSize, 0); }
	public BlockTray(int longEdgeSize) { this(Game.NOCOLOR, longEdgeSize, 0); }
	public BlockTray(Color color) { this(color, defaultSize, 0); }
	public BlockTray() { this(Game.NOCOLOR, defaultSize, 0); }

	private class Block extends JPanel
	{
		private static final long serialVersionUID = 1L;
		public Block(Color color, int size, int[] edges)
		{
			setSize(size, size);
			setPreferredSize(new Dimension(size, size));
			setBackground(color);

			int u = edges[0];
			int l = edges[1];
			int d = edges[2];
			int r = edges[3];

			setBorder(BorderFactory.createMatteBorder(u, l, d, r, color.darker()));
		}
	}
}
