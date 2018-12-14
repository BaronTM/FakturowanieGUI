package Fakturowanie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Historia implements Serializable{

	private ArrayList<Fakturka> faktury = new ArrayList<Fakturka>();
	private ArrayList<Klient> klienci = new ArrayList<Klient>();
	private ArrayList<Wystawca> wystawcy = new ArrayList<Wystawca>();
	private ArrayList<Produkt> produkty = new ArrayList<Produkt>();
    private static final long serialVersionUID = 1755895988989489971L;
	
	public Historia() {
		faktury = new ArrayList<Fakturka>();
		klienci = new ArrayList<Klient>();
		wystawcy = new ArrayList<Wystawca>();
		produkty = new ArrayList<Produkt>();
	}
		
	public ArrayList<Fakturka> getFaktury() {
		return faktury;
	}
	
	public ArrayList<Klient> getKlienci() {
		return klienci;
	}
	
	public ArrayList<Wystawca> getWystrawcy() {
		return wystawcy;
	}
	
	public ArrayList<Produkt> getProdukty() {
		return produkty;
	}
	
	public void zapisz() {
		try {
			FileOutputStream fos = new FileOutputStream("historia.ser");
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
	
	public void zapiszPlikHistorii(File plik) {
		try {
			FileOutputStream fos = new FileOutputStream(plik);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
