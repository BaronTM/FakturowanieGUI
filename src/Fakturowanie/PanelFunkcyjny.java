package Fakturowanie;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class PanelFunkcyjny extends JPanel{
	
	private JLabel tytul;
	private JTextArea wizytowka;
	
	public PanelFunkcyjny() {
		super();
		this.setLayout(null);
		this.setBounds(260, 0, 740, 680);
		this.setBackground(Statyczne.getKolor());
		
		tytul = new JLabel("PROGRAM DO FAKTUROWANIA");
		tytul.setFont(new Font("TimesRoman", Font.BOLD, 30));
		tytul.setBounds(95, 20, 550, 40);
		tytul.setHorizontalAlignment(SwingConstants.CENTER);
		
		wizytowka = new JTextArea();
		wizytowka.setText("Autor: Ernest Paprocki\npaprocki.ernest@gmail.com\nJavaEE 2018");
		wizytowka.setFont(new Font("TimesRoman", Font.BOLD, 20));
		wizytowka.setEditable(false);
		wizytowka.setBackground(Statyczne.getKolor());
		wizytowka.setBounds(350, 550, 370, 120);
		
		this.add(tytul);
		this.add(wizytowka);
	}

}
