package Fakturowanie;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class Ustawienia implements Serializable{
	
	// dodac wszystko do konstruktora
	private float czynnikWaluty;
	private String waluta;
	private float vat;
	private int nrNastepnejFaktury;
	private int rok;
	private Wystawca domyslnyWystawca;
	private String[] walutyDoWyboru = {"PLN", "USD", "EUR", "GBP"};
	//------- Limity
	private String[] rodzajeLimitu = {"NETTO", "BRUTTO"};
	private float limit;
	private String limitRodzaj;
	private boolean limitOn;
	private int trybLimitu;
	private int sprawdzajSumujac;
	private Date dataLimitu;
    private static final long serialVersionUID = 1755895988989489489L;
	
	public Ustawienia() {
		czynnikWaluty = 1;
		waluta = "PLN";
		vat = 0.23f;
		nrNastepnejFaktury = 1;
		rok = 2018;
		limit = 10000.00f;
		limitRodzaj = "BRUTTO";
		limitOn = false;
		trybLimitu = 1;
		sprawdzajSumujac = 1;
		dataLimitu = new Date(2018, 11, 7);
	}
	
	public float getCzynnikWaluty() {
		return czynnikWaluty;
	}
	
	public String getWaluta() {
		return waluta;
	}
	
	public float getVat() {
		return vat;
	}
	
	public int getNrNastepnejFaktury() {
		return nrNastepnejFaktury;
	}
	
	public int getRok() {
		return rok;
	}
	
	public Wystawca getDomyslnyWystawca() {
		return domyslnyWystawca;
	}
	
	public String[] getWalutyDoWyboru() {
		return walutyDoWyboru;
	}
	
	public String[] getRodzajeLimitu() {
		return rodzajeLimitu;
	}
	
	public float getLimit() {
		return limit;
	}
	
	public String getLimitRodzaj() {
		return limitRodzaj;
	}
	
	public boolean isLimitOn() {
		return limitOn;
	}
	
	public int getTrybLimitu() {
		return trybLimitu;
	}
	
	public int getSprawdzajSumujac() {
		return sprawdzajSumujac;
	}
	
	public Date getDataLimitu() {
		return dataLimitu;
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
	
	public void setLimitOn(boolean limitOn) {
		this.limitOn = limitOn;
	}
	
	public void setTrybLimitu(int trybLimitu) {
		this.trybLimitu = trybLimitu;
	}
	
	public void setSprawdzajSumujac(int sprawdzajSumujac) {
		this.sprawdzajSumujac = sprawdzajSumujac;
	}
	
	public void setDataLimitu(Date dataLimitu) {
		this.dataLimitu = dataLimitu;
	}
	
	public void zapiszUstawienia() {
		try {
			FileOutputStream fos = new FileOutputStream("ustawienia.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public String getIndexFaktury() {
		String nrFaktury = nrNastepnejFaktury + "/" + rok;
		nrNastepnejFaktury++;
		return nrFaktury;
	}	
}
