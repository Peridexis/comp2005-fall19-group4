package Blokus;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMenu extends JPanel implements RefreshListener, NextTurnListener
{
	private static final long serialVersionUID = -7887556954455476971L;
	private Game game = new Game();
	private BoardGUI boardPanel;
	private BlockTray mainTray, leftTray, topTray, rightTray;
	private BlockDisplay display;
	private Scoreboard scoreboard;
	private Tile[][] board;
	private int boardSize = 400;

	public GameMenu()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints c;

		// Begin setting up board
		boardPanel = new BoardGUI(boardSize, game);
		boardPanel.addRefreshListener(this);
		c = new GridBagConstraints();
		c.gridx = c.gridy = 1;
		c.weightx = c.weighty = 0.2;
		add(boardPanel, c);
		// Finished setting up board

		// Set up active player block pool display
		mainTray = new BlockTray(new BlockInventory(Game.P1COLOR), boardSize, true);
		mainTray.addRefreshListener(this);
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = c.weighty = 0.2;
		add(mainTray, c);
		
		// Begin setting up opponent block pool displays
		leftTray = new BlockTray(new BlockInventory(Game.P2COLOR), (int) (boardSize*0.7), 3);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = c.weighty = 0.2;
		add(leftTray, c);

		topTray = new BlockTray(new BlockInventory(Game.P3COLOR), (int) (boardSize*0.7), 2);
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = c.weighty = 0.2;
		add(topTray, c);

		rightTray = new BlockTray(new BlockInventory(Game.P4COLOR), (int) (boardSize*0.7), 1);
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = c.weighty = 0.2;
		add(rightTray, c);
		// Finished setting up opponent block pool displays

		// Add button to go to next turn
		JButton nextTurnButton = new JButton("Next Turn");
		nextTurnButton.addActionListener(new NextTurnButtonListener());
		// cycleButton.setSize()
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		add(nextTurnButton, c);

		// Create a sub-panel to hold the block display and manipulation buttons
		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		add(displayPanel, c);

		// Add display for selected block
		display = new BlockDisplay((int) (boardSize*0.5) / 2, game);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		displayPanel.add(display, c);

		// Add button to rotate cw
		JButton cwButton = new JButton("CW Rotate");
		cwButton.addActionListener(new RotationButtonListner(true));
		c.gridx = 0;
		c.gridy = 1;
		displayPanel.add(cwButton, c);

		// Add button to rotate ccw
		JButton ccwButton = new JButton("CCW Rotate");
		ccwButton.addActionListener(new RotationButtonListner(false));
		c.gridx = 0;
		c.gridy = 2;
		displayPanel.add(ccwButton, c);

		// Add button to flip
		JButton flipButton = new JButton("Flip");
		flipButton.addActionListener(new FlipButtonListner());
		c.gridx = 0;
		c.gridy = 3;
		displayPanel.add(flipButton, c);
		// Finished making display for selected block and associated buttons

		// Set up the scoreboard
		scoreboard = new Scoreboard(game, boardSize/2);
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		add(scoreboard, c);
	}

	public void newGame()
	{
		setGame(new Game());
	}

	public void setGame(Game game)
	{
		this.game = game;
		game.addRefreshListener(this);
		game.addNextTurnListener(this);
		boardPanel.game = game;
		mainTray.getInventory().game = game;
		rightTray.getInventory().game = game;
		topTray.getInventory().game = game;
		leftTray.getInventory().game = game;
		display.setGame(game);
		scoreboard.setGame(game);
	}

	public void nextTurn()
	{
		BlockInventory activeTray = mainTray.getInventory();
		mainTray.setInventory(leftTray.getInventory());
		leftTray.setInventory(topTray.getInventory());
		topTray.setInventory(rightTray.getInventory());
		rightTray.setInventory(activeTray);

		activeTray.makeUnavailable(game.getSelected());

		game.nextTurn();
	}

	public void refresh()
	{
		boardPanel.refresh();
		mainTray.refresh();
		rightTray.refresh();
		topTray.refresh();
		leftTray.refresh();
		display.refresh();
		scoreboard.refresh();
	}

	private class NextTurnButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent evnt) 
		{
			nextTurn();
		}
	}

	private class RotationButtonListner implements ActionListener
	{
		private boolean rotateCW = true;
		public RotationButtonListner(boolean cw) { super(); rotateCW = cw; }
		public void actionPerformed(ActionEvent evnt)
		{
			game.rotate(rotateCW ? 1 : 3);
		}
	}

	private class FlipButtonListner implements ActionListener
	{
		public void actionPerformed(ActionEvent evnt)
		{
			game.flip();
		}
	}
}