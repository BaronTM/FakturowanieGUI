package Fakturowanie;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabelaWystawcowFakturka extends JTable {
	private static String[] nazwyKolumn = { "L.P.", "Nazwa Firmy", "Imie", "Nazwisko", "NIP", "Adres" };

	private void setParameters() {
		// this.setBounds(0, 0, 672, 300); 662
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.getColumnModel().getColumn(0).setPreferredWidth(27); // L.P.
		this.getColumnModel().getColumn(1).setPreferredWidth(100); // Nazwa Firmy
		this.getColumnModel().getColumn(2).setPreferredWidth(100); // Imie
		this.getColumnModel().getColumn(3).setPreferredWidth(100); // Nazwisko
		this.getColumnModel().getColumn(4).setPreferredWidth(100); // NIP
		this.getColumnModel().getColumn(5).setPreferredWidth(235); // Adres
	}

	public TabelaWystawcowFakturka() {
		super();
		this.setParameters();
	}

	public TabelaWystawcowFakturka(Object[][] data, String[] nazwy) {
		super(data, nazwy);
		this.setParameters();
	}

	public TabelaWystawcowFakturka(Object[][] data) {
		super(data, nazwyKolumn);
		this.setParameters();
	}

	public TabelaWystawcowFakturka(DefaultTableModel deufaltTableModel) {
		super(deufaltTableModel);
		this.setParameters();
	}

	public static String[] getNazwyKolumn() {
		return nazwyKolumn;
	}
}
