package dcore.entity;

import static dcore.Game.player;

import org.newdawn.slick.Color;

import dcore.Art;
import dcore.Font;
import dcore.Game;
import dcore.Log;

public class Item extends Entity {

	public static enum Name {
		LightHealthPotion, MediumHealthPotion;
	}
	
	public static enum Type{
		consumable, weapon, helm, armor, ring, amulet, wings, other;
	}

	private int	quantity	= 1;
	private Type type;
	private String hint;
	public Name itemName;
	private int cost;
	
	public Item(Name itemName) {
		this(itemName, 1);
	}
	
	public Item(Name itemName, int quantity) {
		if (itemName== Name.LightHealthPotion) {
			setImage(Art.itemSet.getSprite(0, 0).getScaledCopy(2));
			setName("Light Health Potion");
			hint = "Restore 50 health to Hero";
			this.type = Type.consumable;
			this.cost = 5;
		}

		if (itemName == Name.MediumHealthPotion) {
			setImage(Art.itemSet.getSprite(1, 0).getScaledCopy(2));
			setName("Medium Health Potion");
			hint = "Restore 100 health to Hero";
			this.type = Type.consumable;
			this.cost = 15;
		}

		setQuantity(quantity);
		setX(0);
		setY(0);
		this.itemName = itemName;
		Log.print(Log.load, "You get item: " + getName());
	}

	@Override
	public void update() {
	}

	@Override
	public void onMouseClick() {
		if (itemName == Name.LightHealthPotion) {
			if (quantity > 0 && !Game.player.isMaxHP()) {
				Game.player.addHealth(50);
				addQuantity(-1);
				Log.print(Log.game, "The item " + getName() + " was used. Restored 50 HP.");
			}
		}
		
		if (itemName == Name.MediumHealthPotion) {
			if (quantity > 0 && !Game.player.isMaxHP()) {
				Game.player.addHealth(100);
				addQuantity(-1);
				Log.print(Log.game, "The item " + getName() + " was used. Restored 100 HP.");
			}
		}
	}

	@Override
	public void draw() {
		if (!isMouseOver()) {
			getImage().draw(getXDraw(), getYDraw());
		}
		else {
			getImage().draw(getXDraw(), getYDraw(), Color.gray);
		}

		if (quantity > 0)
			Font.hondaSmall.drawString(getX(), getY(), "" + quantity, Color.white);
		else
			Font.hondaSmall.drawString(getX(), getY(), "" + quantity, Color.gray);

		if (isMouseOver()) {
			Font.hondaSmall.drawString(getX(), getY()+getHeight()/2, hint);
		}		
	}
	
	public void buyItem() {
		if (player.getBlood() >= getCost() && getQuantity() > 0) {
			player.addBlood(-getCost());
			player.inventory.addItem(new Item(itemName));
			addQuantity(-1);
		}
	}

	public int getQuantity() {
		return quantity;
	}
	
	public int getCost() {
		return cost;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}
}
