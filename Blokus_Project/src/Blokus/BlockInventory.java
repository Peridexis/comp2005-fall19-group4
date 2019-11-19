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
			getAvailable()[p.ordinal()] = true;
		}
		getAvailable()[Polyomino.O0.ordinal()] = false;
	}

	public BlockInventory(Color color, Game game) { this(color); this.game = game; }

	public boolean isAvailable(Polyomino poly)
	{
		return getAvailable()[poly.ordinal()];
	}

	public void makeUnavailable(Polyomino poly)
	{
		getAvailable()[poly.ordinal()] = false;
	}

	public void makeAvailable(Polyomino poly)
	{
		if (poly == Polyomino.O0) { return; }
		getAvailable()[poly.ordinal()] = true;
	}

	public void refreshGUI()
	{
		game.refreshGUI();
	}

	public boolean[] getAvailable() {
		return available;
	}
}
