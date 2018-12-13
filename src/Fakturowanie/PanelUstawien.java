package Fakturowanie;

import java.awt.Color;
import java.awt.Font;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.text.InternationalFormatter;

public class PanelUstawien extends JPanel {

	private JLabel tytul;
	private JLabel walutaLab;
	private JLabel czynnikLab;
	private JLabel vatLab;
	private JLabel rokLab;
	private JLabel iloscLab;
	private Font labelFont;
	private JComboBox<String> walutaCBox;
	private JFormattedTextField czynnikTxt;
	private JSpinner vatSpin;
	private JSpinner rokSpin;
	private JTextField iloscTxt;
	private JButton przywrocUst;
	private JButton zapiszHist;
	private JButton wczytajHist;
	private JButton resetHist;
	private JButton zastosuj;

	public PanelUstawien() {
		super();
		this.setBounds(260, 0, 740, 680);
		this.setLayout(null);
		this.setBackground(Color.CYAN);

		tytul = new JLabel("USTAWIENIA");
		tytul.setFont(new Font("TimesRoman", Font.BOLD, 30));
		tytul.setBounds(120, 20, 500, 40);
		tytul.setHorizontalAlignment(SwingConstants.CENTER);

		labelFont = new Font("TimesRoman", Font.CENTER_BASELINE, 20);

		walutaLab = new JLabel("Waluta");
		walutaLab.setFont(labelFont);
		walutaLab.setBounds(30, 100, 120, 30);
		czynnikLab = new JLabel("Stosunek do PLN");
		czynnikLab.setFont(labelFont);
		czynnikLab.setBounds(30, 150, 250, 30);
		vatLab = new JLabel("Wartość procentowa VAT");
		vatLab.setFont(labelFont);
		vatLab.setBounds(30, 200, 300, 30);
		rokLab = new JLabel("Rok");
		rokLab.setFont(labelFont);
		rokLab.setBounds(30, 250, 70, 30);
		iloscLab = new JLabel("Ilość wystawionych faktur");
		iloscLab.setFont(labelFont);
		iloscLab.setBounds(30, 300, 310, 30);

		walutaCBox = new JComboBox<String>(Statyczne.getUstawienia().getWalutyDoWyboru());
		walutaCBox.setBounds(400, 100, 100, 30);
		czynnikTxt = new JFormattedTextField();
		czynnikTxt.setBounds(400, 150, 100, 30);
		czynnikTxt.setHorizontalAlignment(JTextField.CENTER);
		czynnikTxt.setFormatterFactory(new AbstractFormatterFactory() {
			@Override
			public AbstractFormatter getFormatter(JFormattedTextField tf) {
				NumberFormat format = DecimalFormat.getInstance();
				format.setMinimumFractionDigits(2);
				format.setMaximumFractionDigits(2);
				format.setRoundingMode(RoundingMode.HALF_UP);
				InternationalFormatter formatter = new InternationalFormatter(format);
				formatter.setAllowsInvalid(false);
				formatter.setMinimum(0.0);
				formatter.setMaximum(100.00);
				return formatter;
			}
		});
		czynnikTxt.setText(Float.toString(Statyczne.getUstawienia().getCzynnikWaluty()));
		vatSpin = new JSpinner(new SpinnerNumberModel(Statyczne.getUstawienia().getVat(), 0, 1, 0.01));
		vatSpin.setBounds(400, 200, 100, 30);
		vatSpin.setEditor(new JSpinner.NumberEditor(vatSpin, "### %"));
		((JSpinner.DefaultEditor) vatSpin.getEditor()).getTextField().setEditable(false);
		((JSpinner.DefaultEditor) vatSpin.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		rokSpin = new JSpinner(new SpinnerNumberModel(Statyczne.getUstawienia().getRok(), 2000, 2050, 1));
		rokSpin.setBounds(400, 250, 100, 30);
		rokSpin.setEditor(new JSpinner.NumberEditor(rokSpin, "####"));
		((JSpinner.DefaultEditor) rokSpin.getEditor()).getTextField().setEditable(false);
		((JSpinner.DefaultEditor) rokSpin.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		iloscTxt = new JTextField(Integer.toString(Statyczne.getUstawienia().getNrNastepnejFaktury() - 1));
		iloscTxt.setBounds(400, 300, 100, 30);
		iloscTxt.setHorizontalAlignment(SwingConstants.CENTER);
		iloscTxt.setEditable(false);

		przywrocUst = new JButton("PRZYWRÓĆ USTAWIENIA DOMYŚLNE");
		przywrocUst.setBounds(30, 400, 300, 30);
		zapiszHist = new JButton("ZAPISZ HISTORIĘ DO PLIKU");
		zapiszHist.setBounds(30, 450, 300, 30);
		wczytajHist = new JButton("WCZYTAJ HISTORIĘ Z PLIKU");
		wczytajHist.setBounds(30, 500, 300, 30);
		resetHist = new JButton("RESETUJ HISTORIĘ");
		resetHist.setBounds(30, 550, 300, 30);
		zastosuj = new JButton("ZASTOSUJ");
		zastosuj.setBounds(550, 620, 150, 30);

		// ------- LISTENERY
		zastosuj.addActionListener(l -> {
			zapiszUstawienia();
		});

		this.add(tytul);
		this.add(walutaLab);
		this.add(czynnikLab);
		this.add(vatLab);
		this.add(rokLab);
		this.add(iloscLab);
		this.add(walutaCBox);
		this.add(czynnikTxt);
		this.add(vatSpin);
		this.add(rokSpin);
		this.add(iloscTxt);
		this.add(przywrocUst);
		this.add(zapiszHist);
		this.add(wczytajHist);
		this.add(resetHist);
		this.add(zastosuj);
	}

	public void odswiez() {
		walutaCBox.setSelectedItem(Statyczne.getUstawienia().getWaluta());
		czynnikTxt.setValue(Statyczne.getUstawienia().getCzynnikWaluty());
		vatSpin.setValue(Statyczne.getUstawienia().getVat());
		rokSpin.setValue(Statyczne.getUstawienia().getRok());
		iloscTxt.setText(Integer.toString(Statyczne.getUstawienia().getNrNastepnejFaktury() - 1));
	}

	public void zapiszUstawienia() {
		Statyczne.getUstawienia().setWaluta((String) walutaCBox.getSelectedItem());
		float czynnikStary = Statyczne.getUstawienia().getCzynnikWaluty();
		float czynnikNowy = 0.0f;
		String czynnikStr = "";
		String[] parts = czynnikTxt.getText().split(" ");
		for (String s : parts) {
			czynnikStr += s;
		}
		String[] partsCom = czynnikStr.split(",");
		czynnikNowy = Float.parseFloat(partsCom[0]) + Float.parseFloat(partsCom[1]) / 100;
		if (czynnikStary != czynnikNowy) {
			Statyczne.getUstawienia().setCzynnikWaluty(czynnikNowy);
			for (Produkt p : Statyczne.getHistoria().getProdukty()) {
				p.aktualizujKoszt();
			}
			for (Fakturka f : Statyczne.getHistoria().getFaktury()) {
				f.aktualizujKoszt();
			}
		}
		int nowyRok = (int) rokSpin.getValue();
		if (nowyRok != Statyczne.getUstawienia().getRok()) {
			int nrNastepnej = 1;
			for (Fakturka f : Statyczne.getHistoria().getFaktury()) {
				String[] nrParts = f.getNrFaktury().split("/");
				if (Integer.parseInt(nrParts[1]) == nowyRok) {
					int nrFak = Integer.parseInt(nrParts[0]);
					if (nrNastepnej <= nrFak) {
						nrNastepnej = nrFak + 1;
					}
				}
			}
			Statyczne.getUstawienia().setRok(nowyRok);
			Statyczne.getUstawienia().setNrNastepnejFaktury(nrNastepnej);
		}
		Statyczne.getUstawienia().setVat(Float.parseFloat(vatSpin.getValue().toString()));
		Statyczne.zapiszHistorie();
		Statyczne.zapiszUstawienia();
	}
}
