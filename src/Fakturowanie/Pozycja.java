package Fakturowanie;

import java.io.Serializable;

public class Pozycja implements Serializable, Cloneable{
	
	private Produkt produkt;
	private int ilosc;
	private float rabat;
	private float kwotaNettoPoz;
	private float kwotaBruttoPoz;
	private float vatPoz;
	private static final long serialVersionUID = 1755895988459484123L;
		
	//------- KONSTRUKTORY
	
	public Pozycja(Produkt produkt) {
		this.produkt = produkt;
		ilosc = 1;
		rabat = 0;
		vatPoz = Statyczne.getUstawienia().getVat();
	}
	
	public Pozycja clone() throws CloneNotSupportedException {
		Pozycja klon = (Pozycja) super.clone();
    	klon.produkt = this.produkt.clone();
    	return klon;
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
	
	public float getVatPoz() {
		return vatPoz;
	}
	
	//------- SETTERY

	public void setIlosc(int ilosc) {
		this.ilosc = ilosc;
	}

	public void setZnizka(float znizka) {
		this.rabat = znizka;
	}
	
	public void setVatPoz(float vatPoz) {
		this.vatPoz = vatPoz;
	}
	
	//------- INNE METODY

	public void oblicz() {
		kwotaNettoPoz = zaokraglij(produkt.getCenaNetto() * ilosc) * (1 - rabat);
		kwotaBruttoPoz = zaokraglij(kwotaNettoPoz * (1 + vatPoz));
	}
	
	public float zaokraglij(float f) {
		f = f * 100;
		f = Math.round(f);
		f = f / 100;
		return f;
	}
}


