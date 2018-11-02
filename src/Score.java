import java.awt.Color;
import java.awt.Graphics;

public class Score {
	String awayScore = "0", homeScore = "0";
	int away = 0;
	
	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawString("Home", 100, 100);
		g.drawString(homeScore, 112, 115);
		g.setColor(Color.orange);
		g.drawString("Away", 600, 100);
		
	}
	
	public void setAIScore(int s) {
		away += s;
	}
	
	public int getAIScore() {
		return away;
	}







} // End Class
