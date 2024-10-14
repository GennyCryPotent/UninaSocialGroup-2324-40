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
	private JTextField nomeGruppo;
	private JTextArea descrizione;
	private HomeController homeController = new HomeController(CreaGruppoGUI.this);

	private JPanel selectedTagsArea;
	
	private JPanel centerPane;
	private JPanel northPane;
	private JPanel southPane;
	
	private JComboBox tagsBox;
	private List<String> selectedTags;
	
	private String nomeUtente;
	private HomeGUI home;

	private PaletteColori paletteColori;
	
    private JTextArea SearchText;
    
	/**
	 * Create the frame.
	 */
	public CreaGruppoGUI(String nomeUtente,  HomeGUI home) {
		
		this.nomeUtente = nomeUtente;
		this.home = home;
		selectedTags = new ArrayList<>();
		
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
		lblCreaGruppo.setForeground(paletteColori.blueColor);
		lblCreaGruppo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCreaGruppo.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblCreaGruppo.setBackground(Color.WHITE);
		
		// Area per mostrare i tag selezionati
		selectedTagsArea = new JPanel();
        selectedTagsArea.setBackground(paletteColori.lightModeColorBG);
        selectedTagsArea.setLayout(new BoxLayout(selectedTagsArea, BoxLayout.Y_AXIS));
        
        // Campo di testo per il nome del gruppo
		nomeGruppo = new JTextField();
		nomeGruppo.setColumns(10);
		
		// Area di testo per la descrizione del gruppo
		descrizione = new JTextArea();
		descrizione.setColumns(10);
		
		// Etichetta per il nome del gruppo
		JLabel nomeGLabel = new JLabel("Nome:");
		nomeGLabel.setForeground(paletteColori.blueColor);
		nomeGLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		nomeGLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		// Etichetta per la descrizione del gruppo
		JLabel lblDescrizione = new JLabel("Descrizione:");
		lblDescrizione.setForeground(paletteColori.blueColor);
		lblDescrizione.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDescrizione.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		// Etichetta per i tag
		JLabel lblTags  = new JLabel("Tags:");
		lblTags.setForeground(paletteColori.blueColor);
		lblTags.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTags.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		// Etichetta per i tag selezionati
		JLabel lblTagsSelected  = new JLabel("Tags Selezionati:");
		lblTagsSelected.setForeground(paletteColori.blueColor);
		lblTagsSelected.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTagsSelected.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		// Bottone per creare il gruppo
		JButton btnCrea = new JButton("Crea");
		btnCrea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homeController.ActionCreaGruppo(nomeGruppo, descrizione, nomeUtente, home, selectedTags);
			}
		});
		btnCrea.setForeground(paletteColori.blueColor);
		
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
		
		tagsBox = new JComboBox(tags.toArray(new String[0]));
		tagsBox.setSelectedIndex(-1);
		tagsBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedTag = (String) tagsBox.getSelectedItem();
				if (selectedTag != null) {
					selectedTags.add(selectedTag);
					selectedTagsArea.add(CreazioneJPanelTag(selectedTag));
					tagsBox.removeItem(selectedTag);
					tagsBox.setSelectedIndex(-1);
					RefreshTagsArea();
				}
			}
		});
		
		// Aggiunta dei componenti ai pannelli
		northPane.add(lblCreaGruppo);
		northPane.add(nomeGLabel);
		northPane.add(nomeGruppo);
		northPane.add(lblDescrizione);
		northPane.add(descrizione);
		northPane.add(lblTags);
		northPane.add(tagsBox);
		northPane.add(lblTagsSelected);
		centerPane.add(selectedTagsArea);
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
		selectedTagsArea.revalidate();
		selectedTagsArea.repaint();
	}
    
	/**
	 * Rimuove un tag dall'elenco dei tag selezionati e lo reinserisce nella combo box.
	 * @param nomeTag Nome del tag da rimuovere.
	 */
	private void RemoveAndReInsertOfTags(String nomeTag) {
		selectedTags.remove(nomeTag);
		selectedTagsArea.removeAll();
		tagsBox.addItem(nomeTag);
		for (String Tag : selectedTags) {
			selectedTagsArea.add(CreazioneJPanelTag(Tag));
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