package Blokus;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
		
public class BoardGUI extends JPanel
{
	private static final long serialVersionUID = -7887556954455476971L;
	private Tile[][] board;
	public Game game;

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
				add(tile);
				board[x][y] = tile;
			}
		}
	}

	public void refresh() 
	{
		for (int y = 0; y < Game.size; y++)
		{
			for (int x = 0; x < Game.size; x++)
			{
				switch (game.getStateAt(x, y))
				{
					case Game.PLAYER1: board[x][y].setBackground(Game.P1COLOR); break;
					case Game.PLAYER2: board[x][y].setBackground(Game.P2COLOR); break;
					case Game.PLAYER3: board[x][y].setBackground(Game.P3COLOR); break;
					case Game.PLAYER4: board[x][y].setBackground(Game.P4COLOR); break;
					default: board[x][y].setBackground(Game.NOCOLOR);
				}
			}
		}
	}
}