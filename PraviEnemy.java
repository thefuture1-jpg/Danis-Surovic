package drugodina;

//Clanovi grupe:Danis Surovic 25/122 FIST, Anan Mulic 25/078 FIST
//Klasa Enemy objasnjava neprijatelj sa tipom, pozicijom, dimenzijama i snagom napada.

public class PraviEnemy {
	private String tip;
	private int x, y;
	private int sirina, visina;
	private int damage;

	public PraviEnemy(String tip, int x, int y, int sirina, int visina, int damage) {
		setTip(tip);
		setX(x);
		setY(y);
		setSirina(sirina);
		setVisina(visina);
		setDamage(damage);
	}

	public String getTip() {
		return tip;
	}

	// Tip ne moze biti prazan
	public void setTip(String tip) {
		if (tip == null || tip.trim().isEmpty()) {
			System.out.println("Tip ne moze biti prazan.");
			this.tip = "Nepoznat";
		} else {
			this.tip = tip.trim();
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSirina() {
		return sirina;
	}

	public void setSirina(int sirina) {
		this.sirina = sirina;
	}

	public int getVisina() {
		return visina;
	}

	public void setVisina(int visina) {
		this.visina = visina;
	}

	public int getDamage() {
		return damage;
	}

	// Damage mora biti izmedju 0 i 100
	public void setDamage(int damage) {
		if (damage < 0) {
			this.damage = 0;
		} else if (damage > 100) {
			this.damage = 100;
		} else {
			this.damage = damage;
		}
	}

	public String toString() {
		return "Enemy[" + tip + "] @ (" + x + "," + y + ") " + sirina + "x" + visina + " dmg=" + damage;
	}
}