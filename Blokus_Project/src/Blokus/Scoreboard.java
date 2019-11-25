package Blokus;
import javax.swing.*;
import java.awt.*;

public class Scoreboard extends JPanel
{
	private static final long serialVersionUID = 1L;
	private Game game;
	private JLabel[] labels = new JLabel[4];

	public Scoreboard(Game game, int size)
	{
		this.game = game;
		setLayout(new GridLayout(4, 1));
		setPreferredSize(new Dimension(size, size));
		setBackground(Game.NOCOLOR);

		JLabel label;
		for (int i = 0; i < 4; i++)
		{
			label = new JLabel("    Score for player " + (i+1) + ": 0");
			add(label);
			labels[i] = label;
		}
	}

	public void refresh()
	{
		String text = "    Score for player ";
		int score;

		score = game.getScore(Game.PLAYER1);
		labels[0].setText(text + "1: " + score);

		score = game.getScore(Game.PLAYER2);
		labels[1].setText(text + "2: " + score);

		score = game.getScore(Game.PLAYER3);
		labels[2].setText(text + "3: " + score);

		score = game.getScore(Game.PLAYER4);
		labels[3].setText(text + "4: " + score);
	}

	public void setGame(Game game) { this.game = game; }
}