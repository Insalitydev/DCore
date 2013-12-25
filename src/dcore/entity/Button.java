package dcore.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

import dcore.Font;
import dcore.Log;


public class Button extends Entity {
	
	public Button(int x, int y, String name, Image image) {
		setImage(image);
		setX(x);
		setY(y);
		setName(name);
		Log.print(Log.load, "Button: " + getName());
	}

	@Override
	public void update() {
	}

	@Override
	public void onMouseClick() {
		Log.print(Log.game, "The standart button was pressed");
	}

	@Override
	public void draw() {
		if (!isMouseOver()) {
			getImage().draw(getXDraw(), getYDraw());
		}
		else {
			getImage().draw(getXDraw(), getYDraw(), Color.gray);
		}
		Font.drawCenter(getX(), getYDraw(), Font.honda, getName());
	}
}
