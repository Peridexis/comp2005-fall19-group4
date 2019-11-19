package Blokus;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
		
public class BoardGUI extends JPanel implements MouseListener
{
	private static final long serialVersionUID = -7887556954455476971L;
	private Tile[][] board;
	public Game game;
	private RefreshListner refreshListner;

	public BoardGUI(int size, Game game)
	{
		this.game = game;
		setLayout(new GridLayout(Game.size, Game.size));
		board = new Tile[Game.size][Game.size];
		Tile tile;

		for (int y = 0; y < Game.size; y++)
		{
			for (int x = 0; x < Game.size; x++)
			{
				tile = new Tile(size / Game.size, Game.NOCOLOR);
				tile.addMouseListener(this);
				tile.setCoords(x, y);
				add(tile);
				board[x][y] = tile;
			}
		}
	}

	public void refresh() 
	{
		int state;
		Color color;
		Color activeColor = game.getActiveColor();
		for (int y = 0; y < Game.size; y++)
		{
			for (int x = 0; x < Game.size; x++)
			{
				state = game.getStateAt(x, y);
				color = Game.getColorFor(state);
				if (game.isHoveringOver(x, y))
				{
					color = addTransparent(color, activeColor);
				}
				board[x][y].setBackground(color);
			}
		}
	}

	private Color addTransparent(Color base, Color top)
	{
		double p = 0.30; // percent opacity on overlay
		// do not make it 50%, as then blue on yellow happens to be the bg color
		int r, g, b, a;

		a = 255;
		r = (int) ((top.getRed()   * p) + (base.getRed()   * (1 - p)));
		g = (int) ((top.getGreen() * p) + (base.getGreen() * (1 - p)));
		b = (int) ((top.getBlue()  * p) + (base.getBlue()  * (1 - p)));

		return new Color(r, g, b, a);
	}

	public void addRefreshListner(RefreshListner lstn)
	{
		refreshListner = lstn;
	}

	// Add MouseListener methods
	public void mouseClicked(MouseEvent evnt) {}
	public void mouseEntered(MouseEvent evnt)
	{
		Tile tile = (Tile) evnt.getComponent();
		int[] coords = tile.getCoords();
		game.hoverSelectedAt(coords[0], coords[1]);

		refreshListner.refresh();
	}
	public void mouseExited(MouseEvent evnt) {}
	public void mousePressed(MouseEvent evnt) {}
	public void mouseReleased(MouseEvent evnt) {}
}