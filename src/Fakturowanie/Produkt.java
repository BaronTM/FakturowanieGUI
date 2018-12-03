package Fakturowanie;

public class Produkt {
	
	private String nazwa;
	private float cenaNetto;
	private String jednostka;
	
	public String getNazwa() {
		return nazwa;
	}
	
	public float getCenaNetto() {
		return cenaNetto;
	}
	
	public String getJednostka() {
		return jednostka;
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

}


