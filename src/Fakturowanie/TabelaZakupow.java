package Fakturowanie;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabelaZakupow extends JTable {
	
	private static String[] nazwyKolumn = {"L.P.", "Nazwa", "Cena Netto", "Ilość", "J.m.", "Vat", "Rabat", 
			"Kwota Netto", "Kwota Brutto"};
	
	private void setParameters() {
		//this.setBounds(0, 0, 672, 300);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.getColumnModel().getColumn(0).setPreferredWidth(27);	// L.P.
		this.getColumnModel().getColumn(1).setPreferredWidth(170);	// Nazwa
		this.getColumnModel().getColumn(2).setPreferredWidth(100);	// Cena Netto
		this.getColumnModel().getColumn(3).setPreferredWidth(40);	// Ilosc
		this.getColumnModel().getColumn(4).setPreferredWidth(30);	// Jednostka
		this.getColumnModel().getColumn(5).setPreferredWidth(35);	// Vat
		this.getColumnModel().getColumn(6).setPreferredWidth(60);	// Rabat
		this.getColumnModel().getColumn(7).setPreferredWidth(100);	// Kwota Netto
		this.getColumnModel().getColumn(8).setPreferredWidth(100);	// Kwota Brutto
	}
	
	public TabelaZakupow() {
		super();
		this.setParameters();
	}
	
	public TabelaZakupow(Object[][] data, String[] nazwy) {
		super(data, nazwy);
		this.setParameters();
	}
	
	public TabelaZakupow(Object[][] data) {
		super(data, nazwyKolumn);
		this.setParameters();
	}
	
	public TabelaZakupow(DefaultTableModel deufaltTableModel) {
		super(deufaltTableModel);
		this.setParameters();
	}
	
	public static String[] getNazwyKolumn() {
		return nazwyKolumn;
	}
}
