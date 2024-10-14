package UninaSocialGroup;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class JPannelloContenutiController {

	private GestioneFinestre GF = new GestioneFinestre();
	private JPannelloContenuti JPannelloContenutiView;
	private LikesDAO L = new LikesDAO();

	public JPannelloContenutiController(JPannelloContenuti jPannelloContenutiView) {
		JPannelloContenutiView = jPannelloContenutiView;
	}

	public void ActionViewPost(int Id_Post, String nomeUtente, String nomeGruppo) {

		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(JPannelloContenutiView); // Recupero il JFrame
		// della classe che
		// invoca il
		// costruttore
		frame.setVisible(false);

		if (frame.getTitle().equals("Home")) { // if per verificare da quale JFrame proviene la richiesta (0 Home_GUI ; 1 Gruppi_GUI)
			GF.InfoPost(Id_Post, nomeUtente, nomeGruppo, 0);
		} else {
			GF.InfoPost(Id_Post, nomeUtente, nomeGruppo, 1);
		}

	}
	
	public void ActionInsLike(String nomeUtente, int Id_Post, JButton likeButton) {
		
		// Controllo per vedere se si deve inserire un like o toglierlo alla pressione
		// del pulasante
		if (L.SelLikeProfilo(nomeUtente, Id_Post) == false) {
			likeButton.setForeground(new Color(255, 0, 0));
			L.InsLike(Id_Post, nomeUtente);
			JPannelloContenutiView.likeNumDinamico++;
			JPannelloContenutiView.setLikeNum(JPannelloContenutiView.likeNumDinamico);
		} else {
			likeButton.setForeground(new Color(0, 0, 0));
			L.DelLike(Id_Post, nomeUtente);
			JPannelloContenutiView.likeNumDinamico--;
			JPannelloContenutiView.setLikeNum(JPannelloContenutiView.likeNumDinamico);
		}
	}
	
	

}
