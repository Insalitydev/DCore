package dcore;

import java.util.ArrayList;

import dcore.entity.Item;

public class Inventory {
	public static ArrayList<Item>	items	= new ArrayList<Item>();

	public Inventory() {
		Log.print(Log.load, "Inventory load...");
		addItem(new Item(Item.Name.LightHealthPotion));
		addItem(new Item(Item.Name.LightHealthPotion));
		addItem(new Item(Item.Name.LightHealthPotion));
		addItem(new Item(Item.Name.LightHealthPotion));
		addItem(new Item(Item.Name.MediumHealthPotion));
		calcItemPos();
	}

	public void update() {
		for (Item item : items) {
			item.update();
		}
	}

	public void draw() {
		for (Item item : items) {
			item.draw();
		}
	}
	
	public void calcItemPos() {
		for (int i = 0; i < items.size(); i++) {
			items.get(i).setX(UI.xMid + UI.INDENT + i*32);
			items.get(i).setY(UI.INDENT+16);
		}
	}

	public void addItem(Item item) {
		
		for (Item curItem : items) {
			if (curItem.getName().equals(item.getName())) {
				curItem.addQuantity(1);
				return;
			}
		}

		// Если не нашли, добавляем в список предмет
		items.add(item);
	}
}
