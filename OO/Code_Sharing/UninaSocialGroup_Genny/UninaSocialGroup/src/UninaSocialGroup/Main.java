package UninaSocialGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main{ 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Notifiche_Gruppi> Res_Notifiche_Gruppi = new ArrayList<Notifiche_Gruppi>();
		Notifiche_Gruppi_DAO GD = new Notifiche_Gruppi_DAO("system", "Database@03");
		
		
		
		Res_Notifiche_Gruppi = GD.SelAllGruppo("Gabbo");
		
		for(int i=0; i<Res_Notifiche_Gruppi.size(); i++) {
			Notifiche_Gruppi R;
			R=Res_Notifiche_Gruppi.get(i);
			System.out.println("Testo: "+ R.getTesto());			
		}
	
		//GD.InsNotifica_R("Dungeons N Dragons", "Genny03cry");
		
		//GD.DelAmministratore("DarkNine", "Fantacalcio");
		
		
		
		 GD.Close_Connection();
	}
} 
