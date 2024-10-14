package UninaSocialGroup;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CreaGruppoGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField NGruppo;
	private JTextArea Descrizione;
	private HomeController HC = new HomeController(CreaGruppoGUI.this);

	private JPanel SelectedTagsArea;
	
	private JPanel centerPane;
	private JPanel northPane;
	private JPanel southPane;
	
	private JComboBox TagsBox;
	private List<String> SelectedTags;
	
	private String NU;
	private HomeGUI home;

    private Color lightModeColorBG = new Color(255, 255, 255);
    private Color lightModeColorButton = new Color(255, 255, 255);
    private Color lightModeColorFont = new Color(0, 0, 0);
    private Color lightModeColorInternalArea = new Color(244, 244, 244);
    private Color blueColor = new Color(0, 128, 255);
    private Color darkModeColorFont = new Color(255, 255, 255);
    
    private JTextArea SearchText;
    
	/**
	 * Create the frame.
	 */
	public CreaGruppoGUI(String NU,  HomeGUI home) {
		
		this.NU = NU;
		this.home = home;
		SelectedTags = new ArrayList<>();
		
		// Impostazioni della finestra
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 431, 300);
		contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
		setContentPane(contentPane);
		
		// Pannelli principali per organizzare la GUI
		centerPane = new JPanel();
		centerPane.setLayout(new BoxLayout(centerPane ,BoxLayout.Y_AXIS));
		
		southPane = new JPanel();
		southPane.setLayout(new BoxLayout(southPane ,BoxLayout.X_AXIS));
		
		northPane = new JPanel();
		northPane.setLayout(new BoxLayout(northPane ,BoxLayout.Y_AXIS));
		
		// Aggiunta dei pannelli principali al contentPane
		contentPane.add(centerPane, BorderLayout.CENTER);
		contentPane.add(southPane, BorderLayout.SOUTH);
		contentPane.add(northPane, BorderLayout.NORTH);
		
		// Titolo della finestra "Crea gruppo"
		JLabel lblCreaGruppo = new JLabel("Crea gruppo");
		lblCreaGruppo.setForeground(new Color(0, 128, 255));
		lblCreaGruppo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCreaGruppo.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblCreaGruppo.setBackground(Color.WHITE);
		
		// Area per mostrare i tag selezionati
		SelectedTagsArea = new JPanel();
        SelectedTagsArea.setBackground(lightModeColorBG);
        SelectedTagsArea.setLayout(new BoxLayout(SelectedTagsArea, BoxLayout.Y_AXIS));
        
        // Campo di testo per il nome del gruppo
		NGruppo = new JTextField();
		NGruppo.setColumns(10);
		
		// Area di testo per la descrizione del gruppo
		Descrizione = new JTextArea();
		Descrizione.setColumns(10);
		
		// Etichetta per il nome del gruppo
		JLabel NomeGLabel = new JLabel("Nome:");
		NomeGLabel.setForeground(blueColor);
		NomeGLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		NomeGLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		// Etichetta per la descrizione del gruppo
		JLabel lblDescrizione = new JLabel("Descrizione:");
		lblDescrizione.setForeground(blueColor);
		lblDescrizione.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDescrizione.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		// Etichetta per i tag
		JLabel lblTags  = new JLabel("Tags:");
		lblTags.setForeground(blueColor);
		lblTags.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTags.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		// Etichetta per i tag selezionati
		JLabel lblTagsSelected  = new JLabel("Tags Selezionati:");
		lblTagsSelected.setForeground(blueColor);
		lblTagsSelected.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTagsSelected.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		// Bottone per creare il gruppo
		JButton btnCrea = new JButton("Crea");
		btnCrea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HC.ActionCreaGruppo(NGruppo, Descrizione, NU, home, SelectedTags);
			}
		});
		btnCrea.setForeground(new Color(0, 128, 255));
		
		// Bottone per annullare la creazione del gruppo
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreaGruppoGUI.this.setVisible(false);
			}
		});
		
		// Caricamento dei tag da un DAO e creazione del JComboBox per selezionare i tag
		List<String> tags = new ArrayList<String>();
		TagsDAO tags_DAO = new TagsDAO();
		tags.addAll(tags_DAO.SelAllTags_String());
		
		TagsBox = new JComboBox(tags.toArray(new String[0]));
		TagsBox.setSelectedIndex(-1);
		TagsBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedTag = (String) TagsBox.getSelectedItem();
				if (selectedTag != null) {
					SelectedTags.add(selectedTag);
					SelectedTagsArea.add(CreazioneJPanelTag(selectedTag));
					TagsBox.removeItem(selectedTag);
					TagsBox.setSelectedIndex(-1);
					RefreshTagsArea();
				}
			}
		});
		
		// Aggiunta dei componenti ai pannelli
		northPane.add(lblCreaGruppo);
		northPane.add(NomeGLabel);
		northPane.add(NGruppo);
		northPane.add(lblDescrizione);
		northPane.add(Descrizione);
		northPane.add(lblTags);
		northPane.add(TagsBox);
		northPane.add(lblTagsSelected);
		centerPane.add(SelectedTagsArea);
		southPane.add(btnCrea);
		southPane.add(btnAnnulla);
	}
	
	/**
	 * Crea un pannello per visualizzare un tag selezionato con un pulsante per rimuoverlo.
	 * @param nomeTag Nome del tag.
	 * @return JPanel contenente il tag e il pulsante per rimuoverlo.
	 */
	private JPanel CreazioneJPanelTag(String nomeTag) {
		JPanel panelOfTag = new JPanel();
		panelOfTag.setLayout(new BoxLayout(panelOfTag, BoxLayout.X_AXIS));
		panelOfTag.add(new JLabel(nomeTag));
		
		JButton button = new JButton("X");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveAndReInsertOfTags(nomeTag);
			}
		});
		panelOfTag.add(button);
		
		return panelOfTag;
	}
	
	/**
	 * Aggiorna l'area dei tag selezionati.
	 */
	private void RefreshTagsArea() {
		SelectedTagsArea.revalidate();
		SelectedTagsArea.repaint();
	}
    
	/**
	 * Rimuove un tag dall'elenco dei tag selezionati e lo reinserisce nella combo box.
	 * @param nomeTag Nome del tag da rimuovere.
	 */
	private void RemoveAndReInsertOfTags(String nomeTag) {
		SelectedTags.remove(nomeTag);
		SelectedTagsArea.removeAll();
		TagsBox.addItem(nomeTag);
		for (String Tag : SelectedTags) {
			SelectedTagsArea.add(CreazioneJPanelTag(Tag));
		}
		RefreshTagsArea();
	}
	
	/**
	 * Imposta i colori per l'area di testo.
	 * @param textArea Area di testo a cui applicare i colori.
	 * @param ColorInternalArea Colore dello sfondo dell'area di testo.
	 * @param ColorFont Colore del testo.
	 */
	private void textAreaSetColor(JTextArea textArea, Color ColorInternalArea, Color ColorFont) {
		textArea.setBackground(ColorInternalArea);
		textArea.setForeground(ColorFont);
	}
}