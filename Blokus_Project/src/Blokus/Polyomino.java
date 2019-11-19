package Blokus;

public enum Polyomino
{
	// Names from http://puzzler.sourceforge.net/docs/polyominoes-intro.html
	// The letter roughly refers to the shape, and the number is how many tiles it uses

	// The one free Monomino
	O1(new int[][] {
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
		{0, 0, 1, 0, 0},
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
	}),

	// The one free Domino
	I2(new int[][] {
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
		{0, 0, 1, 1, 0},
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
	}),

	// The two free Trominos
	I3(new int[][] {
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
		{0, 1, 1, 1, 0},
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
	}),

	V3(new int[][] {
		{0, 0, 0, 0, 0},
		{0, 0, 1, 0, 0},
		{0, 0, 1, 1, 0},
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
	}),

	// The five free Tertominos
	I4(new int[][] {
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
		{0, 1, 1, 1, 1},
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
	}),

	L4(new int[][] {
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
		{0, 1, 1, 1, 0},
		{0, 1, 0, 0, 0},
		{0, 0, 0, 0, 0},
	}),

	O4(new int[][] {
		{0, 0, 0, 0, 0},
		{0, 0, 1, 1, 0},
		{0, 0, 1, 1, 0},
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
	}),

	T4(new int[][] {
		{0, 0, 0, 0, 0},
		{0, 0, 1, 0, 0},
		{0, 1, 1, 0, 0},
		{0, 0, 1, 0, 0},
		{0, 0, 0, 0, 0},
	}),

	Z4(new int[][] {
		{0, 0, 0, 0, 0},
		{0, 1, 1, 0, 0},
		{0, 0, 1, 1, 0},
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
	}),

	// The twelve free Pentominoes
	F5(new int[][] {
		{0, 0, 0, 0, 0},
		{0, 0, 1, 0, 0},
		{0, 1, 1, 0, 0},
		{0, 0, 1, 1, 0},
		{0, 0, 0, 0, 0},
	}),

	I5(new int[][] {
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
		{1, 1, 1, 1, 1},
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
	}),

	L5(new int[][] {
		{0, 0, 1, 0, 0},
		{0, 0, 1, 0, 0},
		{0, 0, 1, 0, 0},
		{0, 0, 1, 1, 0},
		{0, 0, 0, 0, 0},
	}),

	N5(new int[][] {
		{0, 0, 0, 0, 0},
		{0, 1, 1, 0, 0},
		{0, 0, 1, 1, 1},
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
	}),

	P5(new int[][] {
		{0, 0, 0, 0, 0},
		{0, 1, 1, 1, 0},
		{0, 1, 1, 0, 0},
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
	}),

	T5(new int[][] {
		{0, 0, 0, 0, 0},
		{0, 0, 1, 0, 0},
		{0, 0, 1, 0, 0},
		{0, 1, 1, 1, 0},
		{0, 0, 0, 0, 0},
	}),

	U5(new int[][] {
		{0, 0, 0, 0, 0},
		{0, 1, 1, 0, 0},
		{0, 0, 1, 0, 0},
		{0, 1, 1, 0, 0},
		{0, 0, 0, 0, 0},
	}),

	V5(new int[][] {
		{0, 0, 0, 0, 0},
		{0, 1, 0, 0, 0},
		{0, 1, 0, 0, 0},
		{0, 1, 1, 1, 0},
		{0, 0, 0, 0, 0},
	}),

	W5(new int[][] {
		{0, 0, 0, 0, 0},
		{0, 0, 0, 1, 0},
		{0, 0, 1, 1, 0},
		{0, 1, 1, 0, 0},
		{0, 0, 0, 0, 0},
	}),

	X5(new int[][] {
		{0, 0, 0, 0, 0},
		{0, 0, 1, 0, 0},
		{0, 1, 1, 1, 0},
		{0, 0, 1, 0, 0},
		{0, 0, 0, 0, 0},
	}),

	Y5(new int[][] {
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
		{0, 1, 1, 1, 1},
		{0, 0, 1, 0, 0},
		{0, 0, 0, 0, 0},
	}),

	Z5(new int[][] {
		{0, 0, 0, 0, 0},
		{0, 1, 1, 0, 0},
		{0, 0, 1, 1, 1},
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
	}),

	// Empty tile value
	O0(new int[][] {
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
	});

	public int[][] shape;

	private Polyomino(int[][] shape)
	{
		this.shape = shape;
	}

	public int[][] rotatedFlipped(int times, boolean isFlipped)
	{
		// Rotate the requested number of times
		int[][] target = shape;
		for (int i = times; i > 0; i--)
		{
			int[][] rotated = new int[5][5];
			for (int x = 0; x < 5; x++) {
				for (int y = 0; y < 5; y++) {
					rotated[y][4-x] = target[x][y];
				}
			}
			target = rotated;
		}

		// Flip it if needed
		if (isFlipped)
		{
			int[][] flipped = new int[5][5];
			for (int x = 0; x < 5; x++)
			{
				for (int y = 0; y < 5; y++)
				{
					flipped[x][y] = target[4-x][y];
				}
			}
			target = flipped;
		}

		return target;
	}

	public int[][] rotated(int times) { return rotatedFlipped(times, false); }
	public int[][] flipped() { return rotatedFlipped(0, true); }
}
