package dcore.entity;

import dcore.Art;
import dcore.Game;
import dcore.Inventory;
import dcore.Log;
import dcore.Main;

public class Player extends Creature {

	// Second Stats:
	private int					rage;
	private int					blood;

	public final static byte	STATE_MENU	= 0;
	public final static byte	STATE_WALK	= 1;
	public final static byte	STATE_FIGHT	= 2;
	public final static byte	STATE_DEAD	= 3;

	public Enemy				target;

	public Inventory			inventory	= new Inventory();

	public Player() {
		setImage(Art.playerSet.getSprite(0, 0).getScaledCopy(4));
		setX(0);
		setY(0);
		Log.print(Log.load, "Player loaded");

		// Stats set
		maxHealth = 100;
		setHealth(maxHealth);
		blood = 20;
		setDamage(25);
		attackSpeed = 1.0;
	}

	@Override
	public void setState(byte state) {
		if (state == STATE_MENU) {
			cleanse();
		}
		inventory.calcItemPos();
		this.state = state;
	}

	@Override
	public void update() {
		if (state == STATE_WALK) {
			setX(getX() + 2);
			checkFight();
		}

		if (state == STATE_FIGHT) {
			counterFight++;
			if (counterFight >= attackSpeed * Main.FPS) {
				counterFight %= attackSpeed * Main.FPS;

				target.damage(getDamage());
			}

			if (target.getState() == Enemy.STATE_DEAD) {
				setState(STATE_WALK);
				target = null;
				addBlood(Game.rnd.nextInt(10) + 10);
			}
		}

		if (state == STATE_DEAD) {
			// TODO сделать иначе
			abort();
		}

		if (state != STATE_MENU) {
			if (getX() > Main.GAME_WIDTH) {
				Main.game.setState(Game.STATE_MENU);
				addBlood(5);
				setState(STATE_MENU);
			}

			inventory.update();

			if (Game.getState() == Game.STATE_MENU) {
				abort();
			}
		}
	}

	public void goBattle() {
		setX(0);
		setY(410);
		setState(STATE_WALK);
	}

	/** Использовать для вынужденного выхода в меню */
	private void abort() {
		Main.game.setState(Game.STATE_MENU);
		setState(STATE_MENU);
	}

	private void checkFight() {
		for (int i = 0; i < Game.enemies.size(); i++) {
			Enemy curEnemy = Game.enemies.get(i);
			if (curEnemy.getState() == Enemy.STATE_WAIT && (getXDraw() + getWidth() / 2) >= curEnemy.getXDraw()) {
				setState(STATE_FIGHT);
				target = curEnemy;
				curEnemy.setState(Enemy.STATE_FIGHT);
			}
		}
	}

	public void addBlood(int x) {
		if (x > 0)
			Log.print(Log.game, "You got " + x + " blood");
		this.blood += x;
	}

	public int getBlood() {
		return blood;
	}

	@Override
	public void draw() {
		if (state != STATE_MENU) {
			getImage().draw(getXDraw(), getYDraw());
			inventory.draw();
		}

	}

	private void checkHealth() {
		if (getHealth() <= 0) {
			setState(STATE_DEAD);
		}
	}

	@Override
	public void damage(int damage) {
		addHealth(-damage);
		Log.print(Log.battle, "Enemy do " + damage + " damage to Player");
		checkHealth();
	}

	private void cleanse() {
		target = null;
		setHealth(maxHealth);
	}

	@Override
	public void onMouseClick() {
	}
}
