package drugodina;

// Clanovi grupe: Danis Surovic 25/122 FIST, Anan Mulic 25/078 FIST
// Klasa Player sastoji se od igraca sa imenom, pozicijom, dimenzijama i koliko ima hp-a

public class PraviPlayer {
	private String ime;
	private int x, y;
	private int sirina, visina;
	private int zdravlje;

	public PraviPlayer(String ime, int x, int y, int sirina, int visina, int zdravlje) {
		setIme(ime);
		setX(x);
		setY(y);
		setSirina(sirina);
		setVisina(visina);
		setZdravlje(zdravlje);
	}

	public String getIme() {
		return ime;
	}

	// Ime ne smije biti prazno, brisemo visak razmaka
	// i svaku rijec pocinjemo velikim slovom
	public void setIme(String ime) {
		String[] rijeci = ime.split(" ");
		String novoIme = "";

		for (int i = 0; i < rijeci.length; i++) {
			String r = rijeci[i];
			if (r.length() > 0) {
				if (novoIme.length() > 0) {
					novoIme = novoIme + " ";
				}
				novoIme = novoIme + r.substring(0, 1).toUpperCase() + r.substring(1).toLowerCase();
			}
		}

		if (novoIme.length() == 0) {
			novoIme = "Igrac";
		}
		this.ime = novoIme;
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

	public int getZdravlje() {
		return zdravlje;
	}

	// Health poeni moraju biti izmedju 0 i 100, inace je nerealisticno
	public void setZdravlje(int zdravlje) {
		if (zdravlje < 0) {
			this.zdravlje = 0;
		} else if (zdravlje > 100) {
			this.zdravlje = 100;
		} else {
			this.zdravlje = zdravlje;
		}
	}

	public String toString() {
		return "Player[" + ime + "] @ (" + x + "," + y + ") " + sirina + "x" + visina + " hp=" + zdravlje;
	}
}
