package Fakturowanie;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabelaKlientow extends JTable {

	private static String[] nazwyKolumn = { "L.P.", "Klient", "Łącznie Netto", "Łącznie Brutto", "Waluta",
			"Faktury", "Zamknięte"};

	private void setParameters() {
		//this.setBounds(0, 0, 672, 300);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.getColumnModel().getColumn(0).setPreferredWidth(27); // L.P.
		this.getColumnModel().getColumn(1).setPreferredWidth(250); // Klient
		this.getColumnModel().getColumn(2).setPreferredWidth(95); // Łącznie brutto
		this.getColumnModel().getColumn(3).setPreferredWidth(95); // Łącznie netto
		this.getColumnModel().getColumn(4).setPreferredWidth(60); // Waluta
		this.getColumnModel().getColumn(5).setPreferredWidth(60); // Faktury
		this.getColumnModel().getColumn(6).setPreferredWidth(75); // Faktury zamkniete
	}

	public TabelaKlientow() {
		super();
		this.setParameters();
	}

	public TabelaKlientow(Object[][] data, String[] nazwy) {
		super(data, nazwy);
		this.setParameters();
	}

	public TabelaKlientow(Object[][] data) {
		super(data, nazwyKolumn);
		this.setParameters();
	}
	
	public TabelaKlientow(DefaultTableModel deufaltTableModel) {
		super(deufaltTableModel);
		this.setParameters();
	}

	public static String[] getNazwyKolumn() {
		return nazwyKolumn;
	}

}
