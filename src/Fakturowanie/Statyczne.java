package Fakturowanie;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;


public class Statyczne {
	
	private static Historia historia;
	private static Ustawienia ustawienia;
	private static Statyczne statyczne = new Statyczne();
	
	private Statyczne() {
		historia = new Historia();
		ustawienia = new Ustawienia();
		wczytajHistorie();
	}
	
	public static Historia getHistoria() {
		return historia;
	}
	
	public static Ustawienia getUstawienia() {
		return ustawienia;
	}
	
	public static void wczytajUstawienia() {
		try {
			FileInputStream fis = new FileInputStream("ustawienia.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ustawienia = (Ustawienia) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
	}
	
	public static void wczytajHistorie() {
		try {
			FileInputStream fis = new FileInputStream("historia.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			historia = (Historia) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
	}
	
	public static void zapiszHistorie() {
		historia.zapiszHistorie();
	}
	
	public void zapiszUstawienia() {
		ustawienia.zapiszUstawienia();
	}
	

}
