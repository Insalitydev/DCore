package dcore.entity;


import org.newdawn.slick.Image;

import dcore.Game;

/** Класс шаблон для всех игровых объектов.*/
public abstract class Entity {
	/** x, y - центр объекта */
	private int		x, y;
	/** левый верхний угол для отрисовки, обновляются в setX() и setY() */
	private int		xDraw, yDraw;
	private int		width, height;
	private String	name;
	private Image	image;

	public abstract void update();

	public abstract void draw();

	public abstract void onMouseClick();

	public boolean isMouseOver() {
		if (isPointInRectangle(Game.getMouseX(), Game.getMouseY()))
			return true;
		return false;
	}

	public boolean isPointInRectangle(int x, int y) {
		if (x > this.xDraw && x <= this.xDraw + this.width && y > this.yDraw && y <= this.yDraw + this.height)
			return true;
		return false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		setXDraw(x);
	}

	public int getXDraw() {
		return xDraw;
	}

	private void setXDraw(int x) {
		this.xDraw = x - getWidth() / 2;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		setYDraw(y);
	}

	public int getYDraw() {
		return yDraw;
	}

	private void setYDraw(int y) {
		this.yDraw = y - getHeight() / 2;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
		this.width = image.getWidth();
		this.height = image.getHeight();
	}
}
