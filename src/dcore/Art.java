package dcore;

import java.io.IOException;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Art {

	public static SpriteSheet test;
	public static SpriteSheet playerSet;
	public static SpriteSheet itemSet;
	public static Image menuBG;
	public static Image button;
	public static Image levelBG;
	public static Image shopBG;
	
	public static void init() {
		try {
			test = new SpriteSheet(Art.class.getClassLoader().getResource("weapon.png"), 32, 32);
			test.setFilter(Image.FILTER_NEAREST);
			Log.print(Log.load, "Loading test SpriteSheet: " + Art.class.getClassLoader().getResource("weapon.png").toString());
			
			playerSet = new SpriteSheet(Art.class.getClassLoader().getResource("game/playerSet.png"), 32, 32);
			playerSet.setFilter(Image.FILTER_NEAREST);
			Log.print(Log.load, "Loading playerSet SpriteSheet: " + Art.class.getClassLoader().getResource("game/playerSet.png").toString());
			
			itemSet = new SpriteSheet(Art.class.getClassLoader().getResource("game/itemSet.png"), 16, 16);
			itemSet.setFilter(Image.FILTER_NEAREST);
			Log.print(Log.load, "Loading itemSet SpriteSheet: " + Art.class.getClassLoader().getResource("game/itemSet.png").toString());
			
			menuBG = new Image("UI/MenuBG.png");
			Log.print(Log.load, "Loading menuBG Image: " + Art.class.getClassLoader().getResource("UI/MenuBG.png").toString());
			
			button = new Image("UI/Button.png");
			Log.print(Log.load, "Loading Button Image: " + Art.class.getClassLoader().getResource("UI/Button.png").toString());
			
			levelBG = new Image("game/level.png");
			Log.print(Log.load, "Loading levelBG Image: " + Art.class.getClassLoader().getResource("game/level.png").toString());
			
			shopBG = new Image("game/shopBG.png");
			Log.print(Log.load, "Loading shopBG Image: " + Art.class.getClassLoader().getResource("game/shopBG.png").toString());
			shopBG.clampTexture();
		}
		catch (SlickException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
