package Fakturowanie;

import java.util.ArrayList;
import java.util.Date;

public class Fakturka {

	private Wystawca wystawca;
	private Klient klient;
	private float vatNaFakturze;
	private float cenaKoncowaNetto;
	private float cenaKoncowaBrutto;
	private ArrayList<Pozycja> listaProduktow;
	private Date dataWystawienia;
	private boolean zamknieta;
	private String formaPlatnosci;
	private Date terminPlatnosci;
	private int nrFaktury;
	private boolean uwzgledniona;
	
	public Fakturka() {
		this.wystawca = Ustawienia.getDomyslnyWystawca();
		this.vatNaFakturze = Ustawienia.getVat();
		this.listaProduktow = new ArrayList<>();
		this.zamknieta = false;
		this.uwzgledniona = true;
	}

	class Pozycja {

		private Produkt produkt;
		private int ilosc;
		private float rabat;
		private float kwotaNettoPoz;
		private float kwotaBruttoPoz;
		private int vatPoz;
		
		public Pozycja(Produkt produkt) {
			this.produkt = produkt;
			ilosc = 1;
			rabat = 0;
		}

		public int getIlosc() {
			return ilosc;
		}

		public Produkt getProdukt() {
			return produkt;
		}

		public float getZnizka() {
			return rabat;
		}

		public void setIlosc(int ilosc) {
			this.ilosc = ilosc;
		}

		public void setZnizka(float znizka) {
			this.rabat = znizka;
		}

		public void oblicz() {
			kwotaNettoPoz = (produkt.getCenaNetto() * ilosc) * rabat;
			kwotaBruttoPoz = kwotaNettoPoz * (100 - vatPoz) / 100;
		}
	}
}
