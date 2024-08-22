package UninaSocialGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main{ 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n;
		List<Contenuti> Res_Contenuti = new ArrayList<Contenuti>();
		Contenuti_DAO GD = new Contenuti_DAO();
		
	
//		GD.InsCommento("Fai schifo", 13, "Genny03cry");
		
		//GD.UpCommento("Gabbo", 1, "Sono modificato");
		
		
		
		//System.out.println("Numero like: " + Res_Contenuti.size());	
		
		//Res_Contenuti = GD.SelContenutiContenutiUtente("errore31");
		
//		n = GD.SelNumContenuti(4);
//		 System.out.println("Numero Contenuti: " + n);
		
		Res_Contenuti = GD.SelAllContenutiMeseGruppo("Fantacalcio", 8);
		
		for(int i=0; i<Res_Contenuti.size(); i++) {
			Contenuti R;
			R=Res_Contenuti.get(i);
			System.out.println("Testo: "+ R.getTesto());		
		}
	}
} 
