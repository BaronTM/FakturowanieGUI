package Fakturowanie;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;

import javax.swing.JFileChooser;

public class Statyczne {

	private static Historia historia;
	private static Ustawienia ustawienia;
	private static Statyczne statyczne = new Statyczne();
	private static Color kolor = new Color(230, 230, 240);

	private Statyczne() {
		historia = new Historia();
		ustawienia = new Ustawienia();
		wczytajHistorie();
		wczytajUstawienia();
	}

	public static Historia getHistoria() {
		return historia;
	}

	public static Ustawienia getUstawienia() {
		return ustawienia;
	}

	public static Color getKolor() {
		return kolor;
	}

	public static void resetUstawien() {
		ustawienia = new Ustawienia();
	}

	public static void resetHistorii() {
		historia = new Historia();
	}

	public static void zapiszHistorie() {
		historia.zapisz();
	}

	public static void zapiszUstawienia() {
		ustawienia.zapisz();
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

	public static void zapiszHistorieDoPliku() {
		JFileChooser plikDanych = new JFileChooser();
		plikDanych.showSaveDialog(Aplikacja.getRamka());
		historia.zapiszPlikHistorii(plikDanych.getSelectedFile());
	}

	public static void wczytajHistorieZPliku() {
		JFileChooser plikDanych = new JFileChooser();
		plikDanych.showOpenDialog(Aplikacja.getRamka());
		wczytajPlikHistorii(plikDanych.getSelectedFile());
	}

	private static void wczytajPlikHistorii(File plik) {
		try {
			FileInputStream fis = new FileInputStream(plik);
			ObjectInputStream ois = new ObjectInputStream(fis);
			historia = (Historia) ois.readObject();
			ois.close();
		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public static boolean sprawdzLimit() {
		float lacznaKwota = 0.0f;
		Long dataLimitu;
		if (ustawienia.getTrybLimitu() == 1) {
			if (ustawienia.getSprawdzajSumujac() == 1) {
				Date data = new Date();
				int month = data.getMonth();
				int year = data.getYear();
				data = new Date(year, month, 1);
				dataLimitu = data.getTime();
			} else {
				Date data = new Date();
				int year = data.getYear();
				data = new Date(year, 0, 1);
				dataLimitu = data.getTime();
			}
		} else {
			dataLimitu = ustawienia.getDataLimitu().getTime();
		}
		if (ustawienia.getLimitRodzaj().equals("NETTO")) {
			for (Fakturka f : historia.getFaktury()) {
				if ((dataLimitu < f.getDataWystawienia().getTime()) && f.isUwzgledniona()) {
					lacznaKwota += f.getCenaKoncowaNetto();
				}
			}
		} else if (ustawienia.getLimitRodzaj().equals("BRUTTO")) {
			for (Fakturka f : historia.getFaktury()) {
				if ((dataLimitu < f.getDataWystawienia().getTime()) && f.isUwzgledniona()) {
					lacznaKwota += f.getCenaKoncowaBrutto();
				}
			}
		}
		lacznaKwota = Aplikacja.zaokraglij(lacznaKwota);
		
		if (lacznaKwota > ustawienia.getLimit()) {
			return true;
		} else {
			return false;
		}
	}
}
