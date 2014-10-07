import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.StrokeBorder;




public class BrickWallEC
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
        {
           public void run()
           {
              JFrame frame = new BrickFrame();
              frame.setTitle("Bricks are Fun!");
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              frame.setVisible(true);
           }
        });

	}
}


class BrickFrame extends JFrame
{
	public BrickFrame()
	{
		add(new BrickPanel());
		pack();
	}
}


class BrickPanel extends JPanel
{
	
	
	private final double BRICK_WIDTH = 33;
	private final double BRICK_HEIGHT = 16;
	int numOfRows = 1;
	int bricksPerRow = 1;
	int BRICKSPERROW;
	int NUMOFROWS;
	
	public BrickPanel()
	{
		Timer displayTimer = new Timer(450, listener);
		displayTimer.start();

	}
	
	@Override
	public Dimension getPreferredSize() 
	{
		return new Dimension(300,400);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;
		int screenWidth = this.getWidth();
		int screenHeight = this.getHeight();
		BRICKSPERROW = (int) ((int) screenWidth / BRICK_WIDTH);
		NUMOFROWS = (int) ((int) screenHeight / BRICK_HEIGHT);
		System.out.println("" + BRICKSPERROW + " " + NUMOFROWS);
		System.out.println("Painting" + bricksPerRow + " " +numOfRows);
		//System.out.println("Brick width: " + brickWidth + " Brick height: " + brickHeight);
		double offset = BRICK_WIDTH/2.0;
		g2D.setStroke(new BasicStroke(2.0f));
		
		
		for(int row = numOfRows-1; row < numOfRows; row++)
		{
			if(row % 2 == 0)
			{
				for(int col = bricksPerRow-1; col < bricksPerRow; col++)
				{
					Brick brick = new Brick(col * BRICK_WIDTH, row * BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT);
					
					g2D.setColor(brick.getBrickColor());
					g2D.fill(brick);
					g2D.setColor(brick.getBorderColor());
					g2D.draw(brick);
				}
			}
			else
			{
				for(int col = bricksPerRow-1; col < bricksPerRow; col++)
				{
					if(bricksPerRow < BRICKSPERROW)
					{
					Brick brick = new Brick(col * BRICK_WIDTH + offset, row * BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT);
					g2D.setColor(brick.getBrickColor());
					g2D.fill(brick);
					g2D.setColor(brick.getBorderColor());
					g2D.draw(brick);
					}
					
					
				}
			}
		
		}
		
		g2D.setColor(Color.BLACK);
		g2D.setFont(new Font("Arial",Font.BOLD,28));
		g2D.drawString("Number of bricks: " + Brick.getBrickCount(), 35, 35);
		Brick.setBrickCount(0);
		
		
	}
	
	ActionListener listener = new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			System.out.println("Hello");
			if(bricksPerRow<BRICKSPERROW)
				bricksPerRow++;
			else
			{
				bricksPerRow = 1;
				if(numOfRows<=NUMOFROWS)
					numOfRows++;
			}
			repaint();
		}
	};
		
}


class Brick extends Rectangle2D.Double
{
	
	private Color brickColor;
	private float borderWidth;
	private Color borderColor;
	private static int brickCount = 0;
	
	public Brick()
	{
		super();
		brickColor = Color.RED;
		setBorderColor(Color.GRAY);
		brickCount++;
	}
	
	public Brick(double x, double y, double w, double h)
	{
		
		super(x, y, w, h);
		Random r = new Random();
		brickColor = new Color(r.nextInt(256), r.nextInt(256),r.nextInt(256));
		setBorderColor(new Color(r.nextInt(256), r.nextInt(256),r.nextInt(256)));
		brickCount++;
	}
	
	public static int getBrickCount()
	{
		return brickCount;
	}

	public static void setBrickCount(int brickCount)
	{
		Brick.brickCount = brickCount;
	}

	public float getBorderWidth()
	{
		return borderWidth;
	}

	public void setBorderWidth(float borderWidth)
	{
		this.borderWidth = borderWidth;
	}

	public Color getBrickColor()
	{
		return brickColor;
	}

	public void setBrickColor(Color brickColor)
	{
		this.brickColor = brickColor;
	}

	Color getBorderColor()
	{
		return borderColor;
	}

	public void setBorderColor(Color borderColor)
	{
		this.borderColor = borderColor;
	}
}
