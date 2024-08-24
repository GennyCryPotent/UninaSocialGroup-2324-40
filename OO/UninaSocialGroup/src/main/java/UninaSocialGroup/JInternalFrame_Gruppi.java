package UninaSocialGroup;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;



public class JInternalFrame_Gruppi extends JInternalFrame {
    
    private List<Commenti> Res_Commenti = new ArrayList<Commenti>();
    private Commenti_DAO CO = new Commenti_DAO();
    private Contenuti_DAO C = new Contenuti_DAO();
    private Contenuti Res_Contenuto;
     

    public JInternalFrame_Gruppi(int Id_Contenuto, String NU, String NG) {
        
    	//Nuovo JInternalFrame
        setTitle("Post");
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setClosable(true);
        setBounds(37, 0, 626, 434);

      
        Res_Contenuto = C.SelSigContenuto(Id_Contenuto);

        //COMPONENTI
        JTextArea textCommenti = new JTextArea(); 
        textCommenti.setEditable(false);
        createTextAreaCommenti(Id_Contenuto, textCommenti);
        
        JButton btnNewButton_1 = new JButton("Indietro");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        JLabel LabelCommenti = new JLabel();
        LabelCommenti.setText("Commenti");
        LabelCommenti.setForeground(new Color(0, 128, 255));
        LabelCommenti.setFont(new Font("Tahoma", Font.BOLD, 18));

        JTextArea textAreaPost = new JTextArea(Res_Contenuto.getTesto());
        textAreaPost.setEditable(false);

        JLabel LabelPost = new JLabel();
        LabelPost.setText(Res_Contenuto.getPubblicatore());
        LabelPost.setForeground(new Color(0, 128, 255));
        LabelPost.setFont(new Font("Tahoma", Font.BOLD, 18));

        JTextArea textAddCommento = new JTextArea();

        JLabel lblAggiungiCommento = new JLabel();
        lblAggiungiCommento.setText("Aggiungi commento");
        lblAggiungiCommento.setForeground(new Color(0, 128, 255));
        lblAggiungiCommento.setFont(new Font("Tahoma", Font.BOLD, 18));

        JButton btnNewButtonAddCommento = new JButton("Commenta");
        btnNewButtonAddCommento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!textAddCommento.getText().isEmpty()) {
                    CO.InsCommento(textAddCommento.getText(), 2, NU);
                    textCommenti.append(NU + " :" + textAddCommento.getText() + "\n");
                    textAddCommento.setText("");
                }
            }
        });

        JButton btnNewButtonRimCommento = new JButton("Rimuovi commento");
        btnNewButtonRimCommento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Gestione_Finestre V = new Gestione_Finestre();
                V.Elimina_Commento(NU, NG, 2);
                 
            }
        });
        
        JButton btnNewButtonModCommento = new JButton("Modifica commento");
        btnNewButtonModCommento.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		Gestione_Finestre V = new Gestione_Finestre();
                V.Elimina_Commento(NU, NG, 2);
        		
        	}
        });

        //GroupLayout
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(textAreaPost, GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(LabelPost, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, 359, Short.MAX_VALUE)
        					.addComponent(btnNewButton_1))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        							.addGroup(groupLayout.createSequentialGroup()
        								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        									.addGroup(groupLayout.createSequentialGroup()
        										.addContainerGap()
        										.addComponent(textAddCommento, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE))
        									.addGroup(groupLayout.createSequentialGroup()
        										.addGap(14)
        										.addComponent(lblAggiungiCommento, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        									.addGroup(groupLayout.createSequentialGroup()
        										.addContainerGap()
        										.addComponent(btnNewButtonAddCommento)))
        								.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE))
        							.addGroup(groupLayout.createSequentialGroup()
        								.addContainerGap()
        								.addComponent(btnNewButtonRimCommento)
        								.addPreferredGap(ComponentPlacement.RELATED)))
        						.addGroup(groupLayout.createSequentialGroup()
        							.addContainerGap()
        							.addComponent(btnNewButtonModCommento)
        							.addPreferredGap(ComponentPlacement.RELATED)))
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(LabelCommenti, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
        						.addComponent(textCommenti, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE))))
        			.addContainerGap())
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(LabelPost, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(textAreaPost, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(LabelCommenti, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblAggiungiCommento, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(textCommenti, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(textAddCommento, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
        					.addGap(8)
        					.addComponent(btnNewButtonAddCommento)
        					.addGap(5)
        					.addComponent(btnNewButtonRimCommento)
        					.addGap(4)
        					.addComponent(btnNewButtonModCommento)))
        			.addGap(433))
        );
        getContentPane().setLayout(groupLayout);
    }

    // costruisce e restutuisce una textArea che contine tutti i commenti del post
    private void createTextAreaCommenti(int Id_Contenuto, JTextArea textArea) {
    	
        
        Res_Commenti = CO.SelCommentiPost(Id_Contenuto);
        
        //For speciale
        for (Commenti commento : Res_Commenti) {
        	textArea.append(commento.getPubblicatore() + ": " + commento.getTesto() + "\n");
        }
        
        
    }
}
