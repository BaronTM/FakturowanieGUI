package Fakturowanie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class PanelPrzegladFaktur extends JPanel{
	
	private JLabel tytul;
	private TabelaFaktur lista;
	private JScrollPane listaScroll;
	private JPanel panelPodListe;
	private JButton podglad;
	private DefaultTableModel modelListyFakur;
	
	public PanelPrzegladFaktur() {
		super();
		this.setBounds(260, 0, 740, 680);
		this.setLayout(null);
		this.setBackground(Color.ORANGE);

		tytul = new JLabel("PRZEGLĄD FAKTUR");
		tytul.setFont(new Font("TimesRoman", Font.BOLD, 30));
		tytul.setBounds(120, 20, 500, 40);
		tytul.setHorizontalAlignment(SwingConstants.CENTER);
		
		modelListyFakur = new DefaultTableModel(TabelaFaktur.getNazwyKolumn(), 0) {
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		lista = new TabelaFaktur(modelListyFakur);
		listaScroll = new JScrollPane(lista);
		listaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		listaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		podglad = new JButton("PODGLĄD");
		podglad.setBounds(30, 590, 150, 30);

		panelPodListe = new JPanel();
		panelPodListe.setLayout(new BorderLayout());
		panelPodListe.add(listaScroll, BorderLayout.CENTER);
		panelPodListe.setBounds(30, 70, 680, 500);
		
		this.add(tytul);
		this.add(panelPodListe);
		this.add(podglad);
	}
	
	public void odswiezListe() {
		for (int k = modelListyFakur.getRowCount(); k > 0; k--) {
			modelListyFakur.removeRow(0);
		}
		int i = 0;
		for (Fakturka p : Statyczne.getHistoria().getFaktury()) {
			Object[] element = new Object[8];
			element[0] = i + 1;
			element[1] = p.getNrFaktury();
			element[2] = SimpleDateFormat.getDateInstance(3).format(p.getDataWystawienia());
			element[3] = Float.toString(p.getCenaKoncowaNetto());
			element[4] = Float.toString(p.getCenaKoncowaBrutto());
			element[5] = Statyczne.getUstawienia().getWaluta();
			element[6] = p.isZamknieta();
			element[7] = p.isUwzgledniona();			
			modelListyFakur.addRow(element);
			i++;
		}
	}
	
	public void setVisible(boolean vis) {
		super.setVisible(vis);
		if (vis) {
			odswiezListe();
		}
	}


}
	