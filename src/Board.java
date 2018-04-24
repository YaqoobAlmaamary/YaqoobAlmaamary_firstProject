/*
 * 
 * 
 * Version 1.0
 * 
 * This is the main as well as Frame where the game is added
 * 
 */




import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;


public class Board {
	
	public static void main( String[] args ) throws InterruptedException
	{
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		PowerHouse game = new PowerHouse();
		
		panel.setFocusable(true);
		frame.setVisible(true);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.black);
		
		frame.addKeyListener(game);
		panel.add(game);
		
		frame.add(panel);
		
		while(true)
		{
			game.updateModel();
			frame.repaint();
			Thread.sleep(50);
		}
	}

}
