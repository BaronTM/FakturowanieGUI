package Fakturowanie;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Historia implements Serializable{

	private ArrayList<Fakturka> faktury = new ArrayList<>();
	private ArrayList<Klient> klienci = new ArrayList<>();
	private ArrayList<Wystawca> wystawcy = new ArrayList<>();
	private ArrayList<Produkt> produkty = new ArrayList<>();
    private static final long serialVersionUID = 1755895988989489971L;
	
	public Historia() {
		faktury = new ArrayList<>();
		klienci = new ArrayList<>();
		wystawcy = new ArrayList<>();
		produkty = new ArrayList<>();
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
	
	public void zapiszHistorie() {
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
}
