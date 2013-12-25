package dcore.entity;

import org.newdawn.slick.Image;

import dcore.Art;
import dcore.Game;
import dcore.Log;
import dcore.Main;

public class Enemy extends Creature {

	public final static byte	STATE_WAIT	= 0;
	public final static byte	STATE_FIGHT	= 1;
	public final static byte	STATE_DEAD	= 2;

	public Enemy(int x) {
		setImage(Art.playerSet.getSprite(0, 2).getScaledCopy(4));
		setX(x);
		setY(410);

		Log.print(Log.load, "Enemy load...");

		maxHealth = 100;
		setHealth(maxHealth);
		setDamage(10);
		attackSpeed = 1.2;
		setState(STATE_WAIT);
	}

	public void damage(int damage) {
		addHealth(-damage);
		Log.print(Log.battle, "Player do " + damage + " damage to enemy");
	}

	@Override
	public void update() {
		if (getState() == STATE_FIGHT) {
			counterFight++;
			if (counterFight >= attackSpeed * Main.FPS) {
				counterFight %= attackSpeed * Main.FPS;

				Game.player.damage(getDamage());
			}
		}

		if (getHealth() <= 0) {
			setState(STATE_DEAD);
		}
	}

	@Override
	public void draw() {
		if (getState() != STATE_DEAD) {
			getImage().draw(getXDraw(), getYDraw());
		}
	}

	@Override
	public void onMouseClick() {
	}

}
