package Fakturowanie;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Statyczne {
	
	private static Historia historia;
	private static Statyczne statyczne = new Statyczne();
	
	private Statyczne() {
		historia = new Historia();
		wczytajHistorie();
	}
	
	public static Historia getHistoria() {
		return historia;
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
	
	public static void zapiszHistorie() {
		historia.zapiszHistorie();
	}
	

}
