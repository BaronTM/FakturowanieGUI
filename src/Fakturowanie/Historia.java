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

	private static ArrayList<Fakturka> faktury;
	private static ArrayList<Klient> klienci;
	private static ArrayList<Wystawca> wystawcy;
	private static ArrayList<Produkt> produkty;
	private static Historia historia = new Historia();
    private static final long serialVersionUID = 1755895988989489999L;
	
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
		try {
			FileOutputStream fos = new FileOutputStream("historia.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(historia);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void wczytajHistorie() {
		try {
			FileInputStream fis = new FileInputStream("historia.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			historia = (Historia) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
