package WeaponTypes;

import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

import Game.Bullet;

public interface WeaponType {
	public String getName();
	public int getPower();
	public void increasePower();
	public void decreasePower();
	public void fire(Sound sound, int bulletDirection);
}
