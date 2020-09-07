package me.pr3.TimeTables;

public class TextPoint {
	
	int x = 0;
	int y = 0;
	String text = "";

	public TextPoint(int x, int y, String text) {
		
		
		this.x = x;
		this.y = y;
		this.text = text;
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
