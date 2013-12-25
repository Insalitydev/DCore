package dcore.entity;

/** Класс шаблон для всех живых существ в игре*/
public abstract class Creature extends Entity {

	// Main stats
	private int		health;
	protected  int		maxHealth;
	private int		damage;
	protected double	attackSpeed;

	protected byte	state;

	protected int	counterFight	= 0;

	public abstract void damage(int damage);

	public void setState(byte state) {
		this.state = state;
	}

	public byte getState() {
		return state;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void addHealth(int health) {
		this.health += health;
		if (this.health > maxHealth)
			this.health = maxHealth;
	}
	
	public boolean isMaxHP() {
		return (health >= maxHealth);
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public int getDamage() {
		return damage;
	}

}
