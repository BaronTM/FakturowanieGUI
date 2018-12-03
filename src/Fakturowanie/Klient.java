package Fakturowanie;

public class Klient {

	private String imie;
	private String nazwisko;
	private String nazwaFirmy;
	private int nip; // 10 cyfr
	private String adres;

	public Klient(String imie, String nazwisko, String nazwaFirmy, int nip, String adres) {
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.nazwaFirmy = nazwaFirmy;
		this.nip = nip;
		this.adres = adres;
	}
	
	public Klient(String imie, String nazwisko, String nazwaFirmy, int nip) {
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.nazwaFirmy = nazwaFirmy;
		this.nip = nip;
	}

	public Klient(String imie, String nazwisko, int nip) {
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.nip = nip;
	}
	
	public Klient(String nazwaFirmy, int nip) {
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
	
	public void setNip(int nip) {
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
	
	public int getNip() {
		return nip;
	}
	
	public String getAdres() {
		return adres;
	}
}
