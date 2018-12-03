package Fakturowanie;

import java.util.Date;

public class Ustawienia {
	
	
	// dodac wszystko do konstruktora
	private static float czynnikWaluty = 1;
	private static String waluta = "PLN";
	private static float vat = 0.23f;
	private static int nrNastepnejFaktury = 1;
	private static int rok = 2018;
	private static Wystawca domyslnyWystawca;
	private static Ustawienia ustawienia = new Ustawienia();
	private static String[] walutyDoWyboru = {"PLN", "USD", "EUR", "GBP"};
	//------- Limity
	private static String[] rodzajeLimitu = {"NETTO", "BRUTTO"};
	private static float limit = 10000.00f;
	private static String limitRodzaj = "BRUTTO";
	private static boolean limitOn = false;
	private static int trybLimitu = 1;
	private static int sprawdzajSumujac = 1;
	private static Date dataLimitu = new Date(2018, 11, 7);
	

	
	private Ustawienia() {}
	
	public static Ustawienia getUstawienia() {
		return ustawienia;
	}
	
	public static float getCzynnikWaluty() {
		return czynnikWaluty;
	}
	
	public static String getWaluta() {
		return waluta;
	}
	
	public static float getVat() {
		return vat;
	}
	
	public static int getNrNastepnejFaktury() {
		return nrNastepnejFaktury;
	}
	
	public static int getRok() {
		return rok;
	}
	
	public static Wystawca getDomyslnyWystawca() {
		return domyslnyWystawca;
	}
	
	public static String[] getWalutyDoWyboru() {
		return walutyDoWyboru;
	}
	
	public static float getLimit() {
		return limit;
	}
	
	public static String[] getRodzajeLimitu() {
		return rodzajeLimitu;
	}
	
	public static String getLimitRodzaj() {
		return limitRodzaj;
	}
	
	public void setCzynnikWaluty(float czynnikWaluty) {
		this.czynnikWaluty = czynnikWaluty;
	}
	
	public void setWaluta(String waluta) {
		this.waluta = waluta;
	}
	
	public void setVat(float vat) {
		this.vat = vat;
	}
	
	public void setNrNastepnejFaktury(int nrNastepnejFaktury) {
		this.nrNastepnejFaktury = nrNastepnejFaktury;
	}
	
	public void setRok(int rok) {
		this.rok = rok;
	}
	
	public void setDomyslnyWystawca(Wystawca domyslnyWystawca) {
		this.domyslnyWystawca = domyslnyWystawca;
	}
	
	public void setLimit(float limit) {
		this.limit = limit;
	}
	
	public void setLimitRodzaj(String limitRodzaj) {
		this.limitRodzaj = limitRodzaj;
	}
	
	public static void zapiszUstawienia() {
		//-----------------------------------------------------------
	}
	
	public static void wczytajUstawienia() {
		//-----------------------------------------------------------
	}
	
	public static String getIndexFaktury() {
		String nrFaktury = nrNastepnejFaktury + "/" + rok;
		nrNastepnejFaktury++;
		return nrFaktury;
	}
	
	

}