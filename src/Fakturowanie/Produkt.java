package Fakturowanie;

import java.io.Serializable;

public class Produkt implements Serializable, Cloneable{
	
	private String nazwa;
	private float cenaNetto;
	private String jednostka;
	private static String[] listaJednostek = {"SZT", "KG", "L", "M", "M2", "M3"}; 
    private static final long serialVersionUID = 17558941369716569L;

	
	public Produkt(String nazwa, float cenaNetto, String jednostka) {
		this.nazwa = nazwa;
		this.cenaNetto = cenaNetto;
		this.jednostka = jednostka;
	}
    
	public Produkt clone() throws CloneNotSupportedException {
		Produkt klon = (Produkt) super.clone();
    	return klon;
    }
	
	public String getNazwa() {
		return nazwa;
	}
	
	public float getCenaNetto() {
		return cenaNetto;
	}
	
	public String getJednostka() {
		return jednostka;
	}
	
	public static String[] getListaJednostek() {
		return listaJednostek;
	}
		
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	public void setCenaNetto(float cena) {
		this.cenaNetto = cena;
	}
	
	public void setJednostka(String jednostka) {
		this.jednostka = jednostka;
	}
	
	public boolean equals(Object otherObject) {
		if (this == otherObject) {
			return true;
		} else if (otherObject == null) {
			return false;
		} else if (getClass() != otherObject.getClass()) {
			return false;
		} else {
			Produkt other = (Produkt) otherObject;
			return (nazwa.equals(other.nazwa) && (Float.compare(cenaNetto, other.cenaNetto) == 0) 
					&& jednostka.equals(other.jednostka));
		}
	}

}


