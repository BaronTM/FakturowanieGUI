package Fakturowanie;

import javax.swing.JTable;

public class TabelaProduktow extends JTable{
	
	private static String[] nazwyKolumn = { "L.P.", "Nazwa", "Cena Netto", "Waluta", "J.m."};

	private void setParameters() {
		//this.setBounds(0, 0, 462, 300);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.getColumnModel().getColumn(0).setPreferredWidth(27); // L.P.
		this.getColumnModel().getColumn(1).setPreferredWidth(250); // Nazwa
		this.getColumnModel().getColumn(2).setPreferredWidth(95); // Cena netto
		this.getColumnModel().getColumn(3).setPreferredWidth(50); // Waluta
		this.getColumnModel().getColumn(4).setPreferredWidth(30); // J.m.
	}

	public TabelaProduktow() {
		super();
		this.setParameters();
	}

	public TabelaProduktow(Object[][] data, String[] nazwy) {
		super(data, nazwy);
		this.setParameters();
	}

	public TabelaProduktow(Object[][] data) {
		super(data, nazwyKolumn);
		this.setParameters();
	}

	public static String[] getNazwyKolumn() {
		return nazwyKolumn;
	}
}
