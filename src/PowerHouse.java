import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;

public class PowerHouse extends JComponent implements KeyListener {
	private Ellipse2D.Double apple;
	
	private Rectangle[] body;
	private int score;
	private int x;
	private int y;
	private int dx;
	private int dy;
	private int randX;
	private int randY;
	private int highScore;
	public PowerHouse()
	{
		dx = -20;
		dy = 0;
		x = 400;
		y = 200;
		score = 0;
		randX = 20 * (int)(Math.random() * 25 + 1);
		randY = 20 * (int)(Math.random() * 25 + 1);
		
		apple = new Ellipse2D.Double(randX, randY, 20, 20);
		body = new Rectangle[1000];
		body[score] = new Rectangle(x, y, 20, 20);
		score = 1;
		highScore = score;
	}
	
	public Rectangle[] getBody()
	{
		return body;
	}

	public int getScore() 
	{
		return score;
	}

	public void addSegment()
	{
		if( score == 1 )
			body[score] = new Rectangle((int)body[score - 1].getX() - dx, (int)body[score++ - 1].getY() - dy, 20, 20);
		else
			body[score] = new Rectangle((int)body[score - 1].getX(), (int)body[score++ - 1].getY(), 20, 20);
		
			
	}
	
	public void updateModel()
	{
		
		if( (int)body[0].getX() > 800 )
		{
			body[0].setLocation(-20, (int)body[0].getY());
		}
			
		else if( (int)body[0].getX() < 0)
		{
			body[0].setLocation(820, (int)body[0].getY());
		}
		else if( (int)body[0].getY() < 0)
		{
			body[0].setLocation((int)body[0].getX(), 600);
		}
		else if( (int)body[0].getY() > 600)
		{ 
			body[0].setLocation((int)body[0].getX(), -20);
		}
	
		body[0].translate(dx, dy);
		
		for( int i = score - 1; i > 0; i-- )
		{
			
			body[i].setLocation((int)body[i - 1].getX(), (int)body[i - 1].getY());
			if( i == 1 )
			{
				body[i].setLocation((int)body[i - 1].getX() - dx, (int)body[i - 1].getY() - dy);
			}
		
		}
		
		if( (int)body[0].getX() == (int)apple.getX() && (int)body[0].getY() == (int)apple.getY())
		{
			addSegment();
			changeApple();
		}
		chechCollision();
	}
	
	public void chechCollision()
	{
		for( int i = 1; i < score; i++ )
		{
			if(body[0].getX() == body[i].getX() && body[0].getY() == body[i].getY())
			{
				body[0].setLocation(200, 200);
				score = 1;
			}
		}
	}
	
	public void changeApple() 
	{
		randX = 20 * (int)(Math.random() * 25 + 1);
		randY = 20 * (int)(Math.random() * 25 + 1);
		for( int i = 0; i < score; i++ )
		{
			if( randX == (int)body[i].getX() && randY == (int)body[i].getY() )
			{
				changeApple();
			}
				
			
		}
		apple = new Ellipse2D.Double(randX, randY, 20, 20);
	}
	

	
	public void keyPressed(KeyEvent event) 
	{
		int key = event.getKeyCode();
		int speed = 20;
		if( dy < 0 || dx != 0 )
		{
			if( key == KeyEvent.VK_UP )
			{
				dy = -speed;
				dx = 0;
			}	
		}
		if( dy > 0 || dx != 0 )
		{
			if( key == KeyEvent.VK_DOWN )
			{
				dy = speed;
				dx = 0;
			}
			
		}

		
		if( dx < 0 || dy != 0)
		{
			if( key == KeyEvent.VK_LEFT )
			{
				dx = -speed;
				dy = 0;
			}
		}
	
		if( dx > 0 || dy != 0)
		{
			if( key == KeyEvent.VK_RIGHT )
			{
				dx = speed;
				dy = 0;
			}
		}
		
		
		
		
		
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.RED);
		g2d.fill(apple);
		g2d.draw(body[0]);
		g2d.setColor(Color.WHITE);
		if( score >= highScore )
		{
			highScore = score;
		}
		g2d.drawString("Score: " + score, 0, 20);
		g2d.drawString("HighScore: " + highScore, 0, 40);
		for(int i = 1; i < score; i++)
		{
			g2d.fill(body[i]);
			
		}	
		
	}


	
	//Useless For Now
	public void keyReleased(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub
	}
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
	}

}
