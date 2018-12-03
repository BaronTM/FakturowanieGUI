package Fakturowanie;

import java.util.ArrayList;

public class Historia {

	private static ArrayList<Fakturka> faktury;
	private static ArrayList<Klient> klienci;
	private static ArrayList<Wystawca> wystawcy;
	private static ArrayList<Produkt> produkty;
	private static Historia historia = new Historia();
	
	private Historia() {}
	
	public static Historia getHistoria() {
		return historia;
	}
	
	public static ArrayList<Fakturka> getFaktury() {
		return faktury;
	}
	
	public static ArrayList<Klient> getKlienci() {
		return klienci;
	}
	
	public static ArrayList<Wystawca> getWystrawcy() {
		return wystawcy;
	}
	
	public static ArrayList<Produkt> getProdukty() {
		return produkty;
	}
	
	public static void zapiszHistorie() {
		//-----------------------------------------------------------
	}
	
	public static void wczytajHistorie() {
		//-----------------------------------------------------------
	}
}
