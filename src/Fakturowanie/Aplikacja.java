package Fakturowanie;

import javax.swing.*;
import java.awt.*;

public class Aplikacja {
	
	private static JFrame ramka;
	private static PanelMenu panelMenu;
	private static PanelFunkcyjny panelFunkcyjny;
	private static PanelNowejFaktury panelNowejFaktury;
	private static PanelUstawien panelUstawien;
	private static PanelPrzegladFaktur panelPrzegladFaktur;
	private static PanelKlientow panelKlientow;
	private static PanelWystawcow panelWystawcow;
	private static PanelProduktow panelProduktow;
	private static PanelLimit panelLimit;
	private static Aplikacja aplikacja = new Aplikacja();
	
	public static void main(String[] args) {
		Aplikacja.run();
	}
	
	public Aplikacja() {}
	
	public static void run() {
		ramka = new JFrame("Fatury VAT");
		ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ramka.setSize(1000, 700);
		ramka.getContentPane().setLayout(null);
		
		panelMenu = new PanelMenu();
		panelFunkcyjny = new PanelFunkcyjny();
		panelNowejFaktury = new PanelNowejFaktury();
		panelUstawien = new PanelUstawien();
		panelPrzegladFaktur = new PanelPrzegladFaktur();
		panelKlientow = new PanelKlientow();				
		panelWystawcow = new PanelWystawcow();
		panelProduktow = new PanelProduktow();
		panelLimit = new PanelLimit();
		
		panelMenu.setVisible(true);
		panelFunkcyjny.setVisible(true);
		panelNowejFaktury.setVisible(false);
		panelUstawien.setVisible(false);
		panelPrzegladFaktur.setVisible(false);
		panelKlientow.setVisible(false);
		panelWystawcow.setVisible(false);
		panelProduktow.setVisible(false);
		panelLimit.setVisible(false);
		
		ramka.getContentPane().add(panelMenu);
		ramka.getContentPane().add(panelFunkcyjny);
		ramka.getContentPane().add(panelNowejFaktury);	
		ramka.getContentPane().add(panelUstawien);
		ramka.getContentPane().add(panelPrzegladFaktur);
		ramka.getContentPane().add(panelKlientow);
		ramka.getContentPane().add(panelWystawcow);
		ramka.getContentPane().add(panelProduktow);
		ramka.getContentPane().add(panelLimit);
		
		ramka.setResizable(false);
		ramka.setLocationRelativeTo(null);
		ramka.setVisible(true);
	}
	
	public static JFrame getRamka() {
		return ramka;
	}
	
	public static PanelMenu getPanelMenu() {
		return panelMenu;
	}
	
	public static PanelFunkcyjny getPanelFunkcyjny() {
		return panelFunkcyjny;
	}
	
	public static PanelNowejFaktury getPanelNowejFaktury() {
		return panelNowejFaktury;
	}
	
	public static PanelUstawien getPanelUstawien() {
		return panelUstawien;
	}
	
	public static PanelPrzegladFaktur getPanelPrzegladFaktur() {
		return panelPrzegladFaktur;
	}
	
	public static PanelKlientow getPanelKlientow() {
		return panelKlientow;
	}
	
	public static PanelWystawcow getPanelWystawcow() {
		return panelWystawcow;
	}
	
	public static PanelProduktow getPanelProduktow() {
		return panelProduktow;
	}
	
	public static PanelLimit getPanelLimit() {
		return panelLimit;
	}
	
	public static Aplikacja getAplikacja() {
		return aplikacja;
	}

}
