package dcore;

import org.newdawn.slick.TrueTypeFont;


public class Font {

	public static TrueTypeFont honda = new TrueTypeFont(new java.awt.Font("HondaC", java.awt.Font.BOLD, 24),true);
	public static TrueTypeFont hondaSmall = new TrueTypeFont(new java.awt.Font("HondaC", java.awt.Font.BOLD, 14),true);
	public static TrueTypeFont rubino = new TrueTypeFont(new java.awt.Font("1 Rubino", java.awt.Font.BOLD, 48),false);
	
	/** Рисует шрифт с центровкой по x координате. y - верхняя граница шрифта.*/
	public static void drawCenter(int x, int y, TrueTypeFont font, String msg) {
		font.drawString(x - font.getWidth(msg)/2, y, msg);
	}
}
