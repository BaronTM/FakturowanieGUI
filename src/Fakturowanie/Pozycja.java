package Fakturowanie;

public class Pozycja {
	
	private Produkt produkt;
	private int ilosc;
	private float znizka;
	private float cenaNettoPoz;
	private float cenaBruttoPoz;
	private int vat;
		
	public Pozycja(Produkt produkt) {
		this.produkt = produkt;
		ilosc = 1;
		znizka = 0;
	}
	
	public int getIlosc() {
		return ilosc;
	}
	
	public Produkt getProdukt() {
		return produkt;
	}
	
	public float getZnizka() {
		return znizka;
	}
	
	public void setIlosc(int ilosc) {
		this.ilosc = ilosc;
	}
	
	public void setZnizka(float znizka) {
		this.znizka = znizka;
	}
	
	public void oblicz() {
		cenaNettoPoz = (produkt.getCenaNetto() * ilosc) * znizka;
		cenaBruttoPoz = cenaNettoPoz * (100 - vat) / 100;
	}	
	
}
