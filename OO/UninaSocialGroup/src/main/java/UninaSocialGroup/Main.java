package UninaSocialGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main{ 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n;
		Gestione_Finestre.DB.connect();
		List<Notifiche> Res_Notifiche = new ArrayList<Notifiche>();
		Notifiche_Richieste_DAO GD = new Notifiche_Richieste_DAO();
		
	

		
//		GD.InsCommento("Fai schifo", 13, "Genny03cry");
		
		//GD.UpCommento("Gabbo", 1, "Sono modificato");
		
		
		
		//System.out.println("Numero like: " + Res_Notifiche.size());	
		
		//Res_Notifiche = GD.SelNotificheNotificheUtente("errore31");
		
//		n = GD.SelNumNotifiche(4);
//		 System.out.println("Numero Notifiche: " + n);
		
		Res_Notifiche = GD.SelNoitificheRichiesteDiUnCreatore("Genny03cry");
		
		for(int i=0; i<Res_Notifiche.size(); i++) {
			Notifiche R;
			R=Res_Notifiche.get(i);
			System.out.println("Testo: "+ R.getTesto() + " num: " + i);		
		}
	}
} 
