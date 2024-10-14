package UninaSocialGroup;

public class NotificheController {

	private GestioneFinestre GF = new GestioneFinestre();
	private NotificheGUI notificheView;
	private JPannelloRichieste richiesteView;
	private NotificheRichiesteDAO Esitato = new NotificheRichiesteDAO();
	
	//Costruttore Notifiche
	public NotificheController(NotificheGUI notificheView) {
		super();
		this.notificheView = notificheView;
	}
	
	//COstruttore JPanRichieste
	public NotificheController(JPannelloRichieste richiesteView) {
		super();
		this.richiesteView = richiesteView;
	}
	
	public void ActionInditero(String NU) {
		notificheView.setVisible(false);
		GF.AccessoHome(NU);
	}
	
	public void ActionAccetta(String NG, Notifiche N) {
		
		Esitato.Accetta_Richiesta(NG, N.getRicevente()); //Nome utente che manda la richiesta
		richiesteView.setVisible(false);
		
	}
	
	public void ActionRifiuta(String NG, Notifiche N) {
		
		Esitato.Rifiuta_Richiesta(NG, N.getRicevente()); //
		richiesteView.setVisible(false);
	}
	
}
