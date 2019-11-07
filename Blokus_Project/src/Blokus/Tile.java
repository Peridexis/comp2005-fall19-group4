package Blokus;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tile extends JPanel
{
	private static final long serialVersionUID = 1L;
	private int u = 1, l = 1, d = 1, r = 1;
	private Color color = Game.NOCOLOR;

	public Tile(Color color, int size, int[] edges)
	{
		setSize(size, size);
		setPreferredSize(new Dimension(size, size));

		setEdges(edges);
		setColor(color);
	}

	public void setColor(Color newColor)
	{
		color = newColor;
		setBackground(color);
		setBorder(BorderFactory.createMatteBorder(u, l, d, r, color.darker()));
	}

	public void setEdges(int[] edges)
	{
		u = edges[0];
		l = edges[1];
		d = edges[2];
		r = edges[3];
		setBorder(BorderFactory.createMatteBorder(u, l, d, r, color.darker()));
	}
}
