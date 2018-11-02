import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.nio.CharBuffer;

public class Pong extends Applet implements Runnable, KeyListener {
	final int WIDTH = 700, HEIGHT = 500;
	Thread thread;
	HumanPaddle p1;
	AIPaddle p2;
	Ball b1;
	Score s1;
	boolean gameStarted;
	Graphics gfx;
	Image img;
	int leftScore = 0, rightScore = 0;
	
	public void init() {
		this.resize(WIDTH,HEIGHT);
		gameStarted = false;
		this.addKeyListener(this);
		p1 = new HumanPaddle(1);	
		b1 = new Ball();
		p2 = new AIPaddle(2, b1);
		s1 = new Score();
		img = createImage(WIDTH, HEIGHT);
		gfx = img.getGraphics();
		thread = new Thread(this);
		thread.start();
	}
	// Still Need To Create Score
	public void paint(Graphics g) {
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, WIDTH, HEIGHT);
				
		if (b1.getX() < -10 || b1.getX() > 710) {
			gfx.setColor(Color.RED);
			gfx.drawString("GAVE OVER", 350, 250);
		}
		else {
			p1.draw(gfx);
			b1.draw(gfx);
			p2.draw(gfx);
			//s1.draw(gfx);
			
		}
		
		if(!gameStarted) {
			gfx.setColor(Color.white);
			gfx.drawString("Pong", 340, 100);
			gfx.drawString("Press Enter to Begin", 310, 130);
		}
		
		g.drawImage(img, 0, 0, (this));
		
	}

	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void run() {
		for(;;) {
			if (gameStarted) {
			p1.move();
			p2.move();
			b1.move();
			b1.checkPaddleCollision(p1, p2);			
			}
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
	}


	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			p1.setUpAccel(true);
	
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p1.setDownAccel(true);
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			gameStarted = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			p1.setUpAccel(false);
			
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p1.setDownAccel(false);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}




} // End Class
