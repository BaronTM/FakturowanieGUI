package Fakturowanie;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabelaFaktur extends JTable{
	

	private static String[] nazwyKolumn = {"L.P.", "Nr Faktury", "Data", "Kwota Netto", "Kwota Brutto", "Waluta", "Zamknięta", 
			"Limit"};
	
	private void setParameters() {
		//this.setBounds(0, 0, 672, 500);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.getColumnModel().getColumn(0).setPreferredWidth(27);	// L.P.
		this.getColumnModel().getColumn(1).setPreferredWidth(105);	// Nr Faktury
		this.getColumnModel().getColumn(2).setPreferredWidth(105);	// Data
		this.getColumnModel().getColumn(3).setPreferredWidth(100);	// Kwota netto
		this.getColumnModel().getColumn(4).setPreferredWidth(100);	// Kwota brutto
		this.getColumnModel().getColumn(5).setPreferredWidth(75);	// Waluta
		this.getColumnModel().getColumn(6).setPreferredWidth(90);	// Zamknieta
		this.getColumnModel().getColumn(7).setPreferredWidth(60);	// Limit
	}
	
	public TabelaFaktur() {
		super();
		this.setParameters();
	}
	
	public TabelaFaktur(Object[][] data, String[] nazwy) {
		super(data, nazwy);
		this.setParameters();
	}
	
	public TabelaFaktur(Object[][] data) {
		super(data, nazwyKolumn);
		this.setParameters();
	}
	
	public TabelaFaktur(DefaultTableModel deufaltTableModel) {
		super(deufaltTableModel);
		this.setParameters();
	}
	
	public static String[] getNazwyKolumn() {
		return nazwyKolumn;
	}

}
