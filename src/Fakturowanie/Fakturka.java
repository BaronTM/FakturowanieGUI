package Fakturowanie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Fakturka implements Serializable{

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
	private String nrFaktury;
	private boolean uwzgledniona;
	private static String[] dostepneFormyPlatnosci = {"GOTÓWKA", "KARTA"};
    private static final long serialVersionUID = 1755895988989484123L;
	
	//------- KONSTRUKTORY
	
	public Fakturka() {
		this.wystawca = Ustawienia.getDomyslnyWystawca();
		this.vatNaFakturze = Ustawienia.getVat();
		this.listaProduktow = new ArrayList<>();
		this.zamknieta = false;
		this.uwzgledniona = true;
	}
	
	//------- GETTERY
	
	public Wystawca getWystawca() {
		return wystawca;
	}
	
	public Klient getKlient() {
		return klient;
	}
	
	public float getVatNaFakturze() {
		return vatNaFakturze;
	}
	
	public float getCenaKoncowaNetto() {
		return cenaKoncowaNetto;
	}
	
	public float getCenaKoncowaBrutto() {
		return cenaKoncowaBrutto;
	}
	
	public Pozycja[] getListaProduktow() {
		return ((Pozycja[]) listaProduktow.toArray());
	}
	
	public Date getDataWystawienia() {
		return dataWystawienia;
	}
	
	public boolean isZamknieta() {
		return zamknieta;
	}
	
	public String getFormaPlatnosci() {
		return formaPlatnosci;
	}
	
	public Date getTerminPlatnosci() {
		return terminPlatnosci;
	}
	
	public String getNrFaktury() {
		return nrFaktury;
	}
	
	public boolean isUwzgledniona() {
		return uwzgledniona;
	}
	
	public static String[] getDostepneFormyPlatnosci() {
		return dostepneFormyPlatnosci;
	}
	
	//------- SETTERY
	
	public void setWystawca(Wystawca wystawca) {
		this.wystawca = wystawca;
	}
	
	public void setKlient(Klient klient) {
		this.klient = klient;
	}
	
	public void setVatNaFakturze(float vatNaFakturze) {
		this.vatNaFakturze = vatNaFakturze;
	}
	
	public void setDataWystawienia(Date dataWystawienia) {
		this.dataWystawienia = dataWystawienia;
	}
	
	public void setZamknieta(boolean zamknieta) {
		this.zamknieta = zamknieta;
	}
	
	public void setFormaPlatnosci(String formaPlatnosci) {
		this.formaPlatnosci = formaPlatnosci;
	}
	
	public void setNrFaktury() {
		this.nrFaktury = Ustawienia.getIndexFaktury();
	}
	
	public void setUwzgledniona(boolean uwzgledniona) {
		this.uwzgledniona = uwzgledniona;
	}
	
	//------- INNE METODY
	
	public void obliczKwotyKoncowej() {
		cenaKoncowaNetto = 0;
		cenaKoncowaBrutto = 0;
		for (Pozycja p : listaProduktow) {
			cenaKoncowaNetto += p.getKwotaNettoPoz();
			cenaKoncowaBrutto += p.getKwotaBruttoPoz();
		}
	}
	

	private class Pozycja {

		private Produkt produkt;
		private int ilosc;
		private float rabat;
		private float kwotaNettoPoz;
		private float kwotaBruttoPoz;
		private float vatPoz;
		
		//------- KONSTRUKTORY
		
		public Pozycja(Produkt produkt) {
			this.produkt = produkt;
			ilosc = 1;
			rabat = 0;
		}

		//------- GETTERY

		public int getIlosc() {
			return ilosc;
		}

		public Produkt getProdukt() {
			return produkt;
		}
		

		public float getZnizka() {
			return rabat;
		}
		
		public float getKwotaNettoPoz() {
			return kwotaNettoPoz;
		}
		
		public float getKwotaBruttoPoz() {
			return kwotaBruttoPoz;
		}
		
		//------- SETTERY

		public void setIlosc(int ilosc) {
			this.ilosc = ilosc;
		}

		public void setZnizka(float znizka) {
			this.rabat = znizka;
		}
		
		//------- INNE METODY

		public void oblicz() {
			kwotaNettoPoz = (produkt.getCenaNetto() * ilosc) * rabat;
			kwotaBruttoPoz = kwotaNettoPoz * (1 - vatPoz) / 100;
			obliczKwotyKoncowej();
		}
	}
}
