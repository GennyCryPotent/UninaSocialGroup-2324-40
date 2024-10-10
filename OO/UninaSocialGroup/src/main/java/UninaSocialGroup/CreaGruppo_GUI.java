package UninaSocialGroup;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class CreaGruppo_GUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField NGruppo;
	private JTextArea Descrizione;
	private HomeController HC = new HomeController(CreaGruppo_GUI.this);

	private JPanel SelectedTagsArea;
	
	
	private JPanel centerPane;
	private JPanel northPane;
	private JPanel southPane;
	
	
	
	private JComboBox TagsBox;
	
	private List<String> SelectedTags;
	
	private String NU;
	private Home_GUI home;

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
	public CreaGruppo_GUI(String NU,  Home_GUI home) {
		
		this.NU = NU;
		this.home = home;
		SelectedTags = new ArrayList<>();
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 431, 300);
		contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
		
		setContentPane(contentPane);
		
		centerPane = new JPanel();
		centerPane.setLayout(new BoxLayout(centerPane ,BoxLayout.Y_AXIS));
		
		southPane = new JPanel();
		southPane.setLayout(new BoxLayout(southPane ,BoxLayout.X_AXIS));
		
		northPane = new JPanel();
		northPane.setLayout(new BoxLayout(northPane ,BoxLayout.Y_AXIS));
		
		
		contentPane.add(centerPane, BorderLayout.CENTER);
		contentPane.add(southPane, BorderLayout.SOUTH);
		contentPane.add(northPane, BorderLayout.NORTH);
		
		
		JLabel lblCreaGruppo = new JLabel("Crea gruppo");
		lblCreaGruppo.setForeground(new Color(0, 128, 255));
		lblCreaGruppo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCreaGruppo.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblCreaGruppo.setBackground(Color.WHITE);
		



        SelectedTagsArea = new JPanel();
        SelectedTagsArea.setBackground(lightModeColorBG);
        SelectedTagsArea.setLayout(new BoxLayout(SelectedTagsArea, BoxLayout.Y_AXIS));
        

        
		NGruppo = new JTextField();
		NGruppo.setColumns(10);
		
		Descrizione = new JTextArea();
		Descrizione.setColumns(10);
		
		JLabel NomeGLabel = new JLabel("Nome:");
		
		NomeGLabel.setForeground(blueColor);
		NomeGLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		NomeGLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel lblDescrizione = new JLabel("Descrizione:");
		
		lblDescrizione.setForeground(blueColor);
		lblDescrizione.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDescrizione.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel lblTags  = new JLabel("Tags:");
		
		lblTags.setForeground(blueColor);
		lblTags.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTags.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel lblTagsSelected  = new JLabel("Tags Selezionati:");
		
		lblTagsSelected.setForeground(blueColor);
		lblTagsSelected.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTagsSelected.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		
		JButton btnCrea = new JButton("Crea");
		
		
		
		btnCrea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
			   HC.ActionCreaGruppo(NGruppo, Descrizione, NU, home, SelectedTags);
			  //home.setVisible(false);
			}
		});
		btnCrea.setForeground(new Color(0, 128, 255));
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreaGruppo_GUI.this.setVisible(false);
			}
		});

		
        List<String> tags = new ArrayList<String>();
		Tags_DAO tags_DAO = new Tags_DAO();
		tags.addAll(tags_DAO.SelAllTags_String());
		
        TagsBox = new JComboBox(tags.toArray(new String[0]));
        TagsBox.setSelectedIndex(-1);
		TagsBox.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  
				  System.out.println((String) TagsBox.getSelectedItem());
				  
				  
				  SelectedTags.add((String) TagsBox.getSelectedItem());
				  
				  
				  SelectedTagsArea.add(CreazioneJPanelTag((String) TagsBox.getSelectedItem()));
				  
				  
				  
				  
				  TagsBox.removeItem((String) TagsBox.getSelectedItem());
				  TagsBox.setSelectedIndex(-1);
				  
				  RefreshTagsArea();
			  }
			
		});
		
		
		
	    
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
		

	
	private void RefreshTagsArea() {
		SelectedTagsArea.revalidate();
		SelectedTagsArea.repaint();
		
	}
    
	private void RemoveAndReInsertOfTags(String nomeTag) {
		  SelectedTags.remove(nomeTag);
		  SelectedTagsArea.removeAll();
		  
		  TagsBox.addItem(nomeTag);
		  
		  for(String Tag : SelectedTags) {
			  SelectedTagsArea.add(CreazioneJPanelTag(nomeTag));
		  }
		  
		  
		  RefreshTagsArea();
	}
	
	private void textAreaSetColor(JTextArea textArea, Color ColorInternalArea, Color ColorFont ) {
    	textArea.setBackground(ColorInternalArea);
    	textArea.setForeground(ColorFont);
	}
	
}
