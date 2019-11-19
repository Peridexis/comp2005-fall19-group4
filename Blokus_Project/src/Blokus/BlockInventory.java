package Blokus;
import java.awt.Color;

public class BlockInventory {
	public Color color;
	private boolean[] available;
	public Game game;

	public BlockInventory(Color color)
	{
		this.color = color;
		available = new boolean[Polyomino.values().length];
		for (Polyomino p : Polyomino.values())
		{
			available[p.ordinal()] = true;
		}
		available[Polyomino.O0.ordinal()] = false;
	}

	public BlockInventory(Color color, Game game) { this(color); this.game = game; }

	public boolean isAvailable(Polyomino poly)
	{
		return available[poly.ordinal()];
	}

	public void makeUnavailable(Polyomino poly)
	{
		available[poly.ordinal()] = false;
	}

	public void makeAvailable(Polyomino poly)
	{
		if (poly == Polyomino.O0) { return; }
		available[poly.ordinal()] = true;
	}

	public boolean isSelected(Polyomino poly)
	{
		return game.isSelected(poly);
	}

	public void select(Polyomino poly)
	{
		game.setSelected(poly);
	}

	public void deselect()
	{
		game.deselect();
	}
}
