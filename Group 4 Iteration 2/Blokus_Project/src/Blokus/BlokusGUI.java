package Blokus;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import javax.imageio.*;
import java.io.File;


public class BlokusGUI {
	public static final int Num_Player = 4;
	
	public static class Blokus_Frame extends JFrame{
		
		private BlokusBoard board;
	    private BlokusPlayer[] players;
		private JButton instruction;
		
		public Blokus_Frame() {
			
			JFrame window = new JFrame("Blokus");
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		    window.setSize(800,800);
		    window.setLocation(100, 100);
		    JPanel panel1 = new JPanel();
		    JPanel panel2 = new JPanel();
		    window.add(panel1, BorderLayout.NORTH);
		    window.add(panel2, BorderLayout.WEST);
		    JButton button = new JButton("Instructions");
		    panel2.add(button, BorderLayout.NORTH);
//			setVisible(true);
			window.setBackground(Color.gray);
			JLabel l = new JLabel("Welcome to Blokus");
			panel1.add(l);
			button.addActionListener(new ButtonClickListener());
			
			
			window.setVisible(true);
			

			
			
			setResizable(false);//设置窗体是否可以改变大小 
			
		
			
		}
		
	class ButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JOptionPane.showMessageDialog(null, "\"RULES: Each player starts with 21 pieces. Turn order is: Blue, Yellow, Red, Green.\"\n\" A player's piece bin will highlight to indicate current turn.\" \n\" In the first round, players place their opening piece on a corner of the board.\" \n Each new piece played must be placed so that it touches at least one piece of the same color.\"\n\" only corner-to-corner contact allowed, edges cannot touch.\"\n\" A player may pass, if they have no valid moves, play continues.\"\n\" Game ends when no player can place piece.\"\n\" Score calculated by remaining pieces in players hands.\"\n\" The player with the highest score wins!\"");
		}
		 
	}
		
//		public Blokus_Frame()
//	      {
//	         super("Blokus");
//	         board = new BlokusBoard();
//	         players = new BlokusPlayer[Num_Player];
//	         players[0] = new BlokusPlayer(BlokusBoard.BLUE);
//	         players[1] = new BlokusPlayer(BlokusBoard.RED);
//	         players[2] = new BlokusPlayer(BlokusBoard.YELLOW);
//	         players[3] = new BlokusPlayer(BlokusBoard.GREEN);
//	         
//	         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	         initializeGUI();
//	      }
//		
//		private void initializeGUI() {
//			class ClickListener implements MouseListener, MouseMotionListener, MouseWheelListener{
//				public void mouseClicked(MouseEvent e) {
//					
//				}
//
//				@Override
//				public void mouseWheelMoved(MouseWheelEvent arg0) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void mouseDragged(MouseEvent arg0) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void mouseMoved(MouseEvent arg0) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void mouseEntered(MouseEvent arg0) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void mouseExited(MouseEvent arg0) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void mousePressed(MouseEvent arg0) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void mouseReleased(MouseEvent arg0) {
//					// TODO Auto-generated method stub
//					
//				} 
//			}
			
		
		}
		
	
	public static void main(String[] args)
	   {
	      new Blokus_Frame();
	   }
	
	
}

