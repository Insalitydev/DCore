package dcore;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import dcore.entity.Enemy;
import dcore.entity.Entity;
import dcore.entity.Item;
import dcore.entity.Player;

import static dcore.GameLoad.*;

public class Game {

	public static Random			rnd			= new Random();
	public static ArrayList<Entity>	objects		= new ArrayList<Entity>();
	public static ArrayList<Item>	itemList	= new ArrayList<Item>();
	public static ArrayList<Enemy>	enemies		= new ArrayList<Enemy>();
	public static Player			player;

	/** Game States: */
	public final static byte		STATE_INTRO	= 0;
	public final static byte		STATE_MENU	= 1;
	public final static byte		STATE_GAME	= 2;
	public final static byte		STATE_SHOP	= 3;

	private static byte				state;
	private static int				mouseX;
	private static int				mouseY;

	public Game() {
		Log.print(Log.system, "Art init...");
		Art.init();
		setState(STATE_MENU);
		player = new Player();
	}

	public void setState(byte state) {
		Game.state = state;
		loadState(state);
	}

	private void clearLists() {
		// TODO Try to fix me
		objects = new ArrayList<Entity>();
		enemies.clear();
		itemList.clear();
	}

	private void loadState(byte state) {
		clearLists();

		switch (state) {
			case STATE_MENU:
				GameLoad.loadMenu();
				Log.print(Log.system, "State was changed to STATE_MENU");
				break;

			case STATE_GAME:
				loadGame();
				Log.print(Log.system, "State was changed to STATE_GAME");
				break;

			case STATE_SHOP:
				loadShop();
				Log.print(Log.system, "State was changed to STATE_SHOP");
				break;

			default:
				break;
		}
	}

	private void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		switch (state) {
			case STATE_MENU:
				Art.menuBG.draw();
				Font.drawCenter(400, 16, Font.rubino, "Demonic  Core");
				break;

			case STATE_GAME:
				Art.levelBG.draw();
				UI.drawGame();

				for (Enemy enemy : enemies) {
					enemy.draw();
				}
				
				Log.draw();
				break;

			case STATE_SHOP:
				Art.shopBG.draw();
				UI.drawShop();

				for (Item item : itemList) {
					item.draw();
				}
				break;

			default:
				break;
		}

		for (Entity object : objects) {
			object.draw();
		}

		player.draw();
	}

	public void input() {
		while (Mouse.next()) {
			if (Mouse.getEventButton() == 0 && Mouse.getEventButtonState() == true) {
				for (Entity object : objects) {
					if (object.isMouseOver())
						object.onMouseClick();
				}

				for (Entity item : Inventory.items) {
					if (item.isMouseOver())
						item.onMouseClick();
				}

				for (Item item : itemList) {
					if (item.isMouseOver()) {
						item.onMouseClick();
					}
				}
			}
		}
	}

	/** Основной игровой цикл */
	public void gameLoop() {
		while (Main.running) {
			render();
			updateSysInfo();

			for (Entity object : objects) {
				object.update();
			}

			for (Enemy enemy : enemies) {
				enemy.update();
			}

			for (Item item : itemList) {
				item.update();
			}

			player.update();

			input();

			Display.sync(Main.FPS);
			Display.update();
		}

		Display.destroy();
	}

	private void updateSysInfo() {
		mouseX = Mouse.getX();
		mouseY = Main.GAME_HEIGHT - Mouse.getY() - 1;
	}

	public static int getMouseX() {
		return mouseX;
	}

	public static int getMouseY() {
		return mouseY;
	}

	public static byte getState() {
		return state;
	}
}
