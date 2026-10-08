package drugodina;

// Projekat 1
// Clanovi grupe su Danis Surovic 25/122 FIST, Anan Mulic 25/078 FIST
// Igrac i neprijatelji su pravougaonici na mapi, ako se igrac i neprijatelj preklapaju, to je sudar i igrac gubi zdravlje


public class Game {
	private PraviPlayer igrac;
	private PraviEnemy[] neprijatelji = new PraviEnemy[10];
	private int brojNeprijatelja = 0;
	private String[] eventLog = new String[100];
	private int brojPoruka = 0;

	public Game(PraviPlayer igrac) {
		this.igrac = igrac;
	}

	// da li se igrac i neprijatelj preklapaju
	public boolean checkCollision(PraviPlayer p, PraviEnemy e) {
		return p.getX() < e.getX() + e.getSirina() && p.getX() + p.getSirina() > e.getX()
				&& p.getY() < e.getY() + e.getVisina() && p.getY() + p.getVisina() > e.getY();
	}

	// smanjuje zdravlje igraca i pise u eventlog-u :D
	public void decreaseHealth(PraviPlayer p, PraviEnemy e) {
		int staroZdravlje = p.getZdravlje();
		int novoZdravlje = staroZdravlje - e.getDamage();

		if (novoZdravlje < 0) {
			novoZdravlje = 0;
		}
		p.setZdravlje(novoZdravlje);

		String poruka = "HIT: " + p.getIme() + " by " + e.getTip() + " for " + e.getDamage();
		poruka = poruka + " -> HP " + staroZdravlje + " -> " + novoZdravlje;

		eventLog[brojPoruka] = poruka; //Event log sam iskreno nasao preko AI, jer sam trazio nacin da mi se sacuva negdje, e komanda
		brojPoruka++;                  //eventLog, ce ga sacuvati u 'dnevniku'
	}

	// dodaje neprijatelja u niz i pise u eventLog
	public void addEnemy(PraviEnemy e) {
		neprijatelji[brojNeprijatelja] = e;
		brojNeprijatelja++;
		eventLog[brojPoruka] = "ADD: " + e;
		brojPoruka++;
	}

	// vraca neprijatelje ciji tip sadrzi tekst
	public PraviEnemy[] findByType(String tekst) {
		PraviEnemy[] rezultat = new PraviEnemy[brojNeprijatelja];
		for (int i = 0; i < brojNeprijatelja; i++) {
			if (neprijatelji[i].getTip().toLowerCase().contains(tekst.toLowerCase())) {
				rezultat[i] = neprijatelji[i];
			}
		}
		return rezultat;
	}

	// daje nam neprijatelje koji se sudaraju sa igracem
	public PraviEnemy[] collidingWithPlayer() {
		PraviEnemy[] rezultat = new PraviEnemy[brojNeprijatelja];
		for (int i = 0; i < brojNeprijatelja; i++) {
			if (checkCollision(igrac, neprijatelji[i])) {
				rezultat[i] = neprijatelji[i];
			}
		}
		return rezultat;
	}

	// svaki enemy u sudaru udara igraca samim time igrac gubi healthh
	public void resolveCollisions() {
		for (int i = 0; i < brojNeprijatelja; i++) {
			if (checkCollision(igrac, neprijatelji[i])) {
				decreaseHealth(igrac, neprijatelji[i]);
			}
		}
	}

	public static void main(String[] args) {
		PraviPlayer p = new PraviPlayer("  luka       lakovic ", 10, 5, 32, 32, 85);
		Game igra = new Game(p);

		// prvi neprijatelj smo napravili rucno ja i Anan glavom i bradom
		igra.addEnemy(new PraviEnemy("Skeleton", 50, 50, 20, 20, 10));

		// drugi neprijatelj iz stringa
		String linija = "Goblin;12,5;16x16;20";
		String[] dijelovi = linija.split(";"); //Goblin se odvaja u sektore, "Goblin", "12", "5", itd...
		String[] pozicija = dijelovi[1].split(","); 
		String[] velicina = dijelovi[2].split("x"); 
		String tip = dijelovi[0];
		int x = Integer.parseInt(pozicija[0]);
		int y = Integer.parseInt(pozicija[1]);
		int sirina = Integer.parseInt(velicina[0]);
		int visina = Integer.parseInt(velicina[1]);
		int damage = Integer.parseInt(dijelovi[3]);

		PraviEnemy goblin = new PraviEnemy(tip, x, y, sirina, visina, damage);
		igra.addEnemy(goblin);

		System.out.println("Svi neprijatelji:");
		for (int i = 0; i < igra.brojNeprijatelja; i++) {
			System.out.println(igra.neprijatelji[i]);
		}

		System.out.println("\nPretraga za gob-om:");
		PraviEnemy[] nadjeni = igra.findByType("gob");
		for (int i = 0; i < nadjeni.length; i++) {
			if (nadjeni[i] != null) {
				System.out.println(nadjeni[i]);
			}
		}

		System.out.println("\nSudari:");
		PraviEnemy[] sudari = igra.collidingWithPlayer();
		for (int i = 0; i < sudari.length; i++) {
			if (sudari[i] != null) {
				System.out.println(sudari[i]);
			}
		}

		System.out.println("\nPrije:   " + p);
		igra.resolveCollisions();
		System.out.println("Poslije: " + p);

		System.out.println("\nEvent log:");
		for (int i = 0; i < igra.brojPoruka; i++) {
			System.out.println(igra.eventLog[i]);
		}
	}
}