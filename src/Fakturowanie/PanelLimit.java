package Fakturowanie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.text.DateFormatter;
import javax.swing.text.InternationalFormatter;
import javax.swing.text.MaskFormatter;

public class PanelLimit extends JPanel{
	
	private JLabel tytul;
	private SwitchBox wlacznik;
	private Font labelFont;
	private JLabel wlacznikLab;
	private JLabel trybLab;
	private JLabel autosumLab;
	private JLabel mansumLab;
	private JLabel kwotaLab;
	private JFormattedTextField dataTxt;
	private JFormattedTextField kwotaTxt;
	private JRadioButton trybReczny;
	private JRadioButton trybAutomatyczny;
	private ButtonGroup trybyGrupa;
	private JRadioButton biezacyMiesiac;
	private JRadioButton biezacyRok;
	private ButtonGroup biezacyGrupa;
	private JPanel panelParametrow;
	private JPanel panelReczny;
	private JPanel panelAutomatyczny;
	private JPanel panelPodListeFaktur;
	private JButton zastosuj;
	private TabelaFaktur listaFaktur;
	private JScrollPane listaFakturScroll;
	private JComboBox<String> limitNetBrut;
	
	public PanelLimit () {
		super();
		this.setBounds(260, 0, 740, 680);
		this.setLayout(null);
		this.setBackground(Color.CYAN);
		
		tytul = new JLabel("LIMIT");
		tytul.setFont(new Font("TimesRoman", Font.BOLD, 30));
		tytul.setBounds(120, 20, 500, 40);
		tytul.setHorizontalAlignment(SwingConstants.CENTER);
		
		labelFont = new Font("TimesRoman", Font.CENTER_BASELINE, 20);

		wlacznikLab = new JLabel("Limit Wł/Wył");
		wlacznikLab.setFont(labelFont);
		wlacznikLab.setBounds(30, 100, 200, 30);
		trybLab = new JLabel("Tryb limitu");
		trybLab.setFont(labelFont);
		trybLab.setBounds(30, 150, 150, 30);
		
		wlacznik = new SwitchBox("ON", "OFF");
		wlacznik.setBounds(400, 100, 130, 30);
		
		trybAutomatyczny = new JRadioButton("Tryb automatyczny");
		trybAutomatyczny.setFont(new Font("TimesRoman", Font.CENTER_BASELINE, 15));
		trybAutomatyczny.setBounds(400, 150, 200, 30);
		trybReczny = new JRadioButton("Tryb ręczny");
		trybReczny.setFont(trybAutomatyczny.getFont());
		trybReczny.setBounds(400, 180, 150, 30);
		trybyGrupa = new ButtonGroup();
		trybyGrupa.add(trybReczny);
		trybyGrupa.add(trybAutomatyczny);
		trybAutomatyczny.setSelected(true);		

		zastosuj = new JButton("ZASTOSUJ");
		zastosuj.setBounds(550, 620, 150, 30);
		
		//------- panel trybow limitu
		
		panelParametrow = new JPanel();
		panelParametrow.setLayout(null);
		panelParametrow.setBounds(0, 230, 740, 350);
		panelParametrow.setBackground(Color.MAGENTA);
		panelParametrow.setVisible(true);
		
		kwotaLab = new JLabel("Kwota limitu");
		kwotaLab.setFont(labelFont);
		kwotaLab.setBounds(30, 80, 150, 30);
		
		kwotaTxt = new JFormattedTextField();
		kwotaTxt.setFont(trybAutomatyczny.getFont());
		kwotaTxt.setBounds(400, 80, 120, 30);
		kwotaTxt.setHorizontalAlignment(JTextField.CENTER);
		kwotaTxt.setFormatterFactory(new AbstractFormatterFactory() {
            @Override
            public AbstractFormatter getFormatter(JFormattedTextField tf) {
                NumberFormat format = DecimalFormat.getInstance();
                format.setMinimumFractionDigits(2);
                format.setMaximumFractionDigits(2);
                format.setRoundingMode(RoundingMode.HALF_UP);
                InternationalFormatter formatter = new InternationalFormatter(format);
                formatter.setAllowsInvalid(false);
                formatter.setMinimum(0.0);
                formatter.setMaximum(10000000.00);
                return formatter;
            }});
		kwotaTxt.setText(Float.toString(10000.00f));
		
		limitNetBrut = new JComboBox<String>(Statyczne.getUstawienia().getRodzajeLimitu());
		limitNetBrut.setFont(trybAutomatyczny.getFont());
		limitNetBrut.setBounds(520, 80, 100, 30);
						
		//------- tryb automatyczny
		
		autosumLab = new JLabel("Sprawdzaj limit sumując");
		autosumLab.setFont(labelFont);
		autosumLab.setBounds(20, 0, 300, 30);
		
		biezacyMiesiac = new JRadioButton("Bieżący miesiąc");
		biezacyMiesiac.setFont(trybAutomatyczny.getFont());
		biezacyMiesiac.setBounds(390, 0, 200, 30);
		biezacyRok = new JRadioButton("Bieżący rok");
		biezacyRok.setFont(trybAutomatyczny.getFont());
		biezacyRok.setBounds(390, 30, 150, 30);
		biezacyGrupa = new ButtonGroup();
		biezacyGrupa.add(biezacyMiesiac);
		biezacyGrupa.add(biezacyRok);
		biezacyMiesiac.setSelected(true);
		
		//------- tryb reczny
		
		mansumLab = new JLabel("Sprawdzaj limit od dnia");
		mansumLab.setFont(labelFont);
		mansumLab.setBounds(20, 0, 300, 30);
		
		try {
			dataTxt = new JFormattedTextField(new MaskFormatter("##-##-####"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataTxt.setFont(trybAutomatyczny.getFont());
		dataTxt.setBounds(390, 0, 120, 30);
		dataTxt.setHorizontalAlignment(JTextField.CENTER);
		dataTxt.setText("07-12-2018");
		
		//------- lista
		// ---------------------------- TESTOWE
		Object[][] data2 = {
				{"1", "9146/2018", "24.11.2018", 145505.43, 214012.99, "PLN", true, 
					false, "check"}
		};

		listaFaktur = new TabelaFaktur(data2, TabelaFaktur.getNazwyKolumn());
		listaFakturScroll = new JScrollPane(listaFaktur);
		listaFakturScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		listaFakturScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		panelPodListeFaktur = new JPanel();
		panelPodListeFaktur.setLayout(new BorderLayout());
		panelPodListeFaktur.add(listaFakturScroll, BorderLayout.CENTER);
		panelPodListeFaktur.setBounds(30, 130, 680, 200);
		
		//------- panele trybu
				
		panelAutomatyczny = new JPanel();
		panelAutomatyczny.setLayout(null);
		panelAutomatyczny.setBounds(10, 0, 720, 60);
		panelAutomatyczny.setBackground(Color.GREEN);
		panelAutomatyczny.setVisible(true);
		
		panelAutomatyczny.add(biezacyMiesiac);
		panelAutomatyczny.add(biezacyRok);
		panelAutomatyczny.add(autosumLab);
		
		panelReczny = new JPanel();
		panelReczny.setLayout(null);
		panelReczny.setBounds(10, 0, 720, 60);
		panelReczny.setBackground(Color.LIGHT_GRAY);
		panelReczny.setVisible(false);
		
		panelReczny.add(mansumLab);
		panelReczny.add(dataTxt);		
		
		panelParametrow.add(panelAutomatyczny);
		panelParametrow.add(panelReczny);
		panelParametrow.add(kwotaLab);
		panelParametrow.add(kwotaTxt);
		panelParametrow.add(panelPodListeFaktur);
		panelParametrow.add(limitNetBrut);
		
		//------- listenery
		wlacznik.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!wlacznik.isSelected()) {
					trybLab.setVisible(false);
					trybReczny.setVisible(false);
					trybAutomatyczny.setVisible(false);
					panelParametrow.setVisible(false);
				}
				else {
					trybLab.setVisible(true);
					trybReczny.setVisible(true);
					trybAutomatyczny.setVisible(true);
					panelParametrow.setVisible(true);
				}
			}
		});
		
		trybAutomatyczny.addChangeListener(l -> {
			if (trybAutomatyczny.isSelected()) {
				panelReczny.setVisible(false);
				panelAutomatyczny.setVisible(true);
			} else {
				panelReczny.setVisible(true);
				panelAutomatyczny.setVisible(false);
			}
		});

				
		this.add(tytul);
		this.add(wlacznikLab);
		this.add(trybLab);
		this.add(wlacznik);
		this.add(trybReczny);
		this.add(trybAutomatyczny);
		this.add(panelParametrow);
		this.add(zastosuj);
	}

}
