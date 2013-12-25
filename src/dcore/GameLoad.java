package dcore;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Color;

import dcore.entity.Button;
import dcore.entity.Enemy;
import dcore.entity.Item;

import static dcore.Game.*;

public class GameLoad {

	public static HashMap<Item.Name, Integer> shopItems = new HashMap<Item.Name, Integer>();
	
	public static void loadMenu() {
		Game.objects.add(new Button(400, 215, "Button", Art.button) {
			@Override
			public void onMouseClick() {
				Log.print(Log.system, "Button from MENU!");
			}
		});

		Game.objects.add(new Button(400, 315, "Shop", Art.button) {
			@Override
			public void onMouseClick() {
				Main.game.setState(STATE_SHOP);
			}
		});

		Game.objects.add(new Button(400, 400, "To Game", Art.button) {
			@Override
			public void onMouseClick() {
				Main.game.setState(STATE_GAME);
			}
		});
	}

	public static void loadGame() {
		Game.objects.add(new Button(Main.GAME_WIDTH - Art.button.getWidth() / 2 - UI.INDENT, Art.button.getHeight() / 2 + UI.INDENT, "To Menu", Art.button) {
			@Override
			public void onMouseClick() {
				Main.game.setState(STATE_MENU);
			}
		});

		Game.objects.add(new Button(Main.GAME_WIDTH - Art.button.getWidth() / 2 - UI.INDENT, Art.button.getHeight() * 3 / 2 + UI.INDENT, "Restart", Art.button) {
			@Override
			public void onMouseClick() {
				Main.game.setState(STATE_GAME);
				Log.print(Log.game, "Restart action");
			}
		});

		Game.objects.add(new Button(Main.GAME_WIDTH - Art.button.getWidth() / 2 - UI.INDENT, Art.button.getHeight() * 5 / 2 + UI.INDENT, "Button 2", Art.button) {
			@Override
			public void onMouseClick() {
				Log.print(Log.game, "Button 2 action");
			}
		});

		for (int i = 1; i <= rnd.nextInt(4); i++)
			enemies.add(new Enemy(rnd.nextInt(200) + 400));

		player.goBattle();
	}

	public static void loadShop() {
		Game.objects.add(new Button(Main.GAME_WIDTH - Art.button.getWidth() / 2, Art.button.getHeight() / 2, "To Menu", Art.button) {
			@Override
			public void onMouseClick() {
				Main.game.setState(STATE_MENU);
			}
		});

		Game.objects.add(new Button(Main.GAME_WIDTH - Art.button.getWidth() * 3 / 2, Art.button.getHeight() / 2, "Button", Art.button) {
			@Override
			public void onMouseClick() {
				Log.print(Log.game, "Another button");
			}
		});

		Game.objects.add(new Button(Main.GAME_WIDTH - Art.button.getWidth() * 5 / 2, Art.button.getHeight() / 2, "Button2", Art.button) {
			@Override
			public void onMouseClick() {
				Log.print(Log.game, "Another button");
			}
		});

		loadItemsShop();
	}

	private static void loadItemsShop() {
		// Какие вещи и сколько продаем
		shopItems.put(Item.Name.LightHealthPotion, 10);
		shopItems.put(Item.Name.MediumHealthPotion, 3);

		
		// Добавление их в игровые объекты и переопределение их для продажи
		for (Item.Name name : Item.Name.values()) {
			if (shopItems.containsKey(name)) {
				Game.itemList.add(new Item(name, shopItems.get(name)) {
					@Override
					public void onMouseClick() {
						buyItem();
					}
					@Override
					public void draw() {
						super.draw();
						Font.honda.drawString(getX()+64, getYDraw(), "Cost: " + getCost(), Color.red);
					}
				});
			}
		}
		
		// Считаем их координаты в списке:
		calcShopitemPos();
	
	}
	public static void calcShopitemPos() {
		for (int i = 0; i < itemList.size(); i++) {
			Game.itemList.get(i).setY(UI.yItemAreaShop + itemList.get(i).getHeight()/2 + 48*i);
			itemList.get(i).setX(UI.INDENT + itemList.get(i).getWidth()/2);
		}

	}
}
