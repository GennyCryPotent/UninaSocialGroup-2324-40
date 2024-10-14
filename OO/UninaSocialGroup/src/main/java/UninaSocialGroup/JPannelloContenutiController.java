package UninaSocialGroup;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class JPannelloContenutiController {

	private GestioneFinestre gestioneFinestre = new GestioneFinestre();
	private JPannelloContenuti jPannelloContenutiView;
	private LikesDAO likeDAO = new LikesDAO();

	public JPannelloContenutiController(JPannelloContenuti jPannelloContenutiView) {
		this.jPannelloContenutiView = jPannelloContenutiView;
	}

	public void ActionViewPost(int idPost, String nomeUtente, String nomeGruppo) {

		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(jPannelloContenutiView); // Recupero il JFrame
		// della classe che
		// invoca il
		// costruttore
		frame.setVisible(false);

		if (frame.getTitle().equals("Home")) { // if per verificare da quale JFrame proviene la richiesta (0 Home_GUI ; 1 Gruppi_GUI)
			gestioneFinestre.InfoPost(idPost, nomeUtente, nomeGruppo, 0);
		} else {
			gestioneFinestre.InfoPost(idPost, nomeUtente, nomeGruppo, 1);
		}

	}
	
	public void ActionInsLike(String nomeUtente, int idPost, JButton likeButton) {
		
		// Controllo per vedere se si deve inserire un like o toglierlo alla pressione
		// del pulasante
		if (likeDAO.SelLikeProfilo(nomeUtente, idPost) == false) {
			likeButton.setForeground(new Color(255, 0, 0));
			likeDAO.InsLike(idPost, nomeUtente);
			jPannelloContenutiView.likeNumDinamico++;
			jPannelloContenutiView.setLikeNum(jPannelloContenutiView.likeNumDinamico);
		} else {
			likeButton.setForeground(new Color(0, 0, 0));
			likeDAO.DelLike(idPost, nomeUtente);
			jPannelloContenutiView.likeNumDinamico--;
			jPannelloContenutiView.setLikeNum(jPannelloContenutiView.likeNumDinamico);
		}
	}
	
	

}
