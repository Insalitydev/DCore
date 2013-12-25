package dcore;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.Color;

public class UI {

	public static final int	INDENT				= 16;
	public static final int	HEIGHT_UI			= 96;

	public static final int	SHOP_WIDTH			= 128 + 92;

	// 192 - ширина окошка левого и правоко, а также ширина кнопки
	public static final int	xLeft				= INDENT;
	public static final int	xMid				= 192 + INDENT * 2;
	public static final int	xRight				= Main.GAME_WIDTH - INDENT - 192;
	public static final int	yItemAreaShop	= INDENT * 2 + 64;

	private static void setColorBack() {
		glColor3f(0.25f, 0.2f, 0.2f);
	}

	private static void setColorFront() {
		glColor3f(0.2f, 0.25f, 0.3f);
	}

	private static void drawBackUI() {
		// внешний прямоугольник
		setColorBack();
		glBegin(GL_QUADS);
		{
			glVertex2f(0, 0);
			glVertex2f(Main.GAME_WIDTH, 0);
			glVertex2f(Main.GAME_WIDTH, HEIGHT_UI + INDENT * 2);
			glVertex2f(0, HEIGHT_UI + INDENT * 2);
		}
		glEnd();

		// внутренний прямоугольник
		setColorFront();
		glBegin(GL_QUADS);
		{
			glVertex2f(INDENT, INDENT);
			glVertex2f(Main.GAME_WIDTH - INDENT, INDENT);
			glVertex2f(Main.GAME_WIDTH - INDENT, HEIGHT_UI + INDENT);
			glVertex2f(INDENT, HEIGHT_UI + INDENT);
		}
		glEnd();

		// перегородки
		setColorBack();
		// левая
		glBegin(GL_QUADS);
		{
			glVertex2f(xMid - INDENT, INDENT);
			glVertex2f(xMid, INDENT);
			glVertex2f(xMid, HEIGHT_UI + INDENT * 2);
			glVertex2f(xMid - INDENT, HEIGHT_UI + INDENT * 2);
		}
		glEnd();

		// Правая
		glBegin(GL_QUADS);
		{
			glVertex2f(xRight - INDENT, INDENT);
			glVertex2f(xRight, INDENT);
			glVertex2f(xRight, HEIGHT_UI + INDENT * 2);
			glVertex2f(xRight - INDENT, HEIGHT_UI + INDENT * 2);
		}
		glEnd();
	}

	public static void drawGame() {
		glDisable(GL_TEXTURE_2D);
		drawBackUI();

		Font.honda.drawString(INDENT, INDENT, "Blood: " + Game.player.getBlood(), Color.red);
		Font.honda.drawString(INDENT, INDENT + 32, "Health: " + Game.player.getHealth(), Color.red);

		if (Game.player.target != null) {
			Font.honda.drawString(INDENT, INDENT + 64, "Enemy HP: " + Game.player.target.getHealth(), Color.red);
		}
	}

	private static void drawBackShop() {
		// Подложка:
		setColorBack();
		glBegin(GL_QUADS);
		{
			glVertex2f(0, 0);
			glVertex2f(SHOP_WIDTH, 0);
			glVertex2f(SHOP_WIDTH, Main.GAME_HEIGHT);
			glVertex2f(0, Main.GAME_HEIGHT);
		}
		glEnd();

		// внутренний прямоугольник:
		setColorFront();
		glBegin(GL_QUADS);
		{
			glVertex2f(INDENT, INDENT);
			glVertex2f(SHOP_WIDTH - INDENT, INDENT);
			glVertex2f(SHOP_WIDTH - INDENT, Main.GAME_HEIGHT - INDENT);
			glVertex2f(INDENT, Main.GAME_HEIGHT - INDENT);
		}
		glEnd();

		// Перегородки:
		setColorBack();
		glBegin(GL_QUADS);
		{
			glVertex2f(INDENT, yItemAreaShop - INDENT);
			glVertex2f(SHOP_WIDTH - INDENT, yItemAreaShop - INDENT);
			glVertex2f(SHOP_WIDTH - INDENT, yItemAreaShop);
			glVertex2f(INDENT, yItemAreaShop);
		}
		glEnd();
	}

	public static void drawShop() {
		glDisable(GL_TEXTURE_2D);
		drawBackShop();
		
		Font.drawCenter(SHOP_WIDTH/2, INDENT*2, Font.rubino, "SHOP");
		Font.honda.drawString(INDENT, Main.GAME_HEIGHT-INDENT-Font.honda.getHeight(), "Blood: " + Game.player.getBlood(), Color.red);
	}
}
