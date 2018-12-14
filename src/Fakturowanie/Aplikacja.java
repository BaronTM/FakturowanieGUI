package Fakturowanie;

import javax.swing.*;

import java.awt.*;

public class Aplikacja implements CheckerListener {

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
	private static PanelPodgladu panelPodgladu;
	private static JLayeredPane layeredPane;
	private static Aplikacja aplikacja = new Aplikacja();
	private static CheckerInvoker ci;
	private static Thread thread;
	private static int iloscMinut;

	public static void main(String[] args) {
		Aplikacja.run();
	}

	public Aplikacja() {
	}

	public static void run() {
		ramka = new JFrame("Fatury VAT");
		ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ramka.setSize(1000, 700);
		ramka.getContentPane().setLayout(null);

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1000, 700);

		Statyczne.wczytajHistorie();
		Statyczne.wczytajUstawienia();

		panelMenu = new PanelMenu();
		panelFunkcyjny = new PanelFunkcyjny();
		panelNowejFaktury = new PanelNowejFaktury();
		panelUstawien = new PanelUstawien();
		panelPrzegladFaktur = new PanelPrzegladFaktur();
		panelKlientow = new PanelKlientow();
		panelWystawcow = new PanelWystawcow();
		panelProduktow = new PanelProduktow();
		panelLimit = new PanelLimit();
		panelPodgladu = new PanelPodgladu();

		panelMenu.setVisible(true);
		panelFunkcyjny.setVisible(true);
		panelNowejFaktury.setVisible(false);
		panelUstawien.setVisible(false);
		panelPrzegladFaktur.setVisible(false);
		panelKlientow.setVisible(false);
		panelWystawcow.setVisible(false);
		panelProduktow.setVisible(false);
		panelLimit.setVisible(false);
		panelPodgladu.setVisible(false);

		ramka.getContentPane().add(layeredPane);

		layeredPane.add(panelMenu, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(panelFunkcyjny, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(panelNowejFaktury, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(panelUstawien, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(panelPrzegladFaktur, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(panelKlientow, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(panelWystawcow, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(panelProduktow, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(panelLimit, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(panelPodgladu, JLayeredPane.PALETTE_LAYER);

		ci = new CheckerInvoker();
		ci.addCheckerListener(aplikacja);
		thread = new Thread(ci);
		thread.start();

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

	public static PanelPodgladu getPanelPodgladu() {
		return panelPodgladu;
	}

	public static Aplikacja getAplikacja() {
		return aplikacja;
	}

	public static void resetujIloscMinut() {
		iloscMinut = 0;
	}

	public static float zaokraglij(float f) {
		int i = (int) (f * 100);
		f = Math.round(i);
		float wynik = 0.0f;
		wynik = f / 100;
		return wynik;
	}

	@Override
	public void nextMinute(CheckerEvent e) {
		if (Statyczne.getUstawienia().isLimitOn()) {
			iloscMinut++;
			if (Statyczne.getUstawienia().getCzestotliwosc().equals("5 MINUT")) {
				if (iloscMinut >= 5) {
					iloscMinut = 0;
					if (Statyczne.sprawdzLimit()) {
						JOptionPane.showMessageDialog(ramka, "Limit został przekroczony.", "Uwaga",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			} else if (Statyczne.getUstawienia().getCzestotliwosc().equals("10 MINUT")) {
				if (iloscMinut >= 10) {
					iloscMinut = 0;
					if (Statyczne.sprawdzLimit()) {
						JOptionPane.showMessageDialog(ramka, "Limit został przekroczony.", "Uwaga",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			} else if (Statyczne.getUstawienia().getCzestotliwosc().equals("15 MINUT")) {
				if (iloscMinut >= 15) {
					iloscMinut = 0;
					if (Statyczne.sprawdzLimit()) {
						JOptionPane.showMessageDialog(ramka, "Limit został przekroczony.", "Uwaga",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			} else if (Statyczne.getUstawienia().getCzestotliwosc().equals("30 MINUT")) {
				if (iloscMinut >= 30) {
					iloscMinut = 0;
					if (Statyczne.sprawdzLimit()) {
						JOptionPane.showMessageDialog(ramka, "Limit został przekroczony.", "Uwaga",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			} else if (Statyczne.getUstawienia().getCzestotliwosc().equals("GODZINA")) {
				if (iloscMinut >= 60) {
					iloscMinut = 0;
					if (Statyczne.sprawdzLimit()) {
						JOptionPane.showMessageDialog(ramka, "Limit został przekroczony.", "Uwaga",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		}
	}
}
