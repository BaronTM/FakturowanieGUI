package Fakturowanie;

public class Klient {

	private String imie;
	private String nazwisko;
	private String nazwaFirmy;
	private long nip; // 10 cyfr
	private String adres;

	public Klient(String imie, String nazwisko, String nazwaFirmy, long nip, String adres) {
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.nazwaFirmy = nazwaFirmy;
		this.nip = nip;
		this.adres = adres;
	}
	
	public Klient(String imie, String nazwisko, String nazwaFirmy, long nip) {
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.nazwaFirmy = nazwaFirmy;
		this.nip = nip;
	}

	public Klient(String imie, String nazwisko, long nip) {
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.nip = nip;
	}
	
	public Klient(String nazwaFirmy, long nip) {
		this.nazwaFirmy = nazwaFirmy;
		this.nip = nip;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public void setNazwaFirmy(String nazwaFirmy) {
		this.nazwaFirmy = nazwaFirmy;
	}
	
	public void setNip(long nip) {
		this.nip = nip;
	}
	
	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getImie() {
		return imie;
	}
	
	public String getNazwisko() {
		return nazwisko;
	}
	
	public String getNazwaFirmy() {
		return nazwaFirmy;
	}
	
	public long getNip() {
		return nip;
	}
	
	public String getAdres() {
		return adres;
	}
	
	public String nipToString() {
		if ((nip > 999999999l) && (nip < 10000000000l)) {
			String tekst = "";
			char[] znaki = Long.toString(nip).toCharArray();
			for (int i = 0; i < znaki.length; i++) {
				tekst = tekst + znaki[i];
				if (i == 2 || i == 5 || i == 7) {
					tekst = tekst + "-";
				}
			}
			return tekst;
		} else {
			return "";
		}
	}
	
	public String toString() {
		String tekst = "";
		if (nazwaFirmy != "" && nazwaFirmy != null) {
			tekst = tekst + nazwaFirmy + "\n";
		}
		if (imie != "" && imie != null && nazwisko != "" && nazwisko != null) {
			tekst = tekst + imie + " " + nazwisko + "\n";
		}
		if (adres != "" && adres != null) {
			String[] parts = adres.split(", ");
			for (String s : parts) {
				tekst = tekst + s + "\n";
			}
		}
		if ((nip > 999999999l) && (nip < 10000000000l)) {
			tekst = tekst + "NIP " + this.nipToString();		
		}
		
		return tekst;
	}
}
