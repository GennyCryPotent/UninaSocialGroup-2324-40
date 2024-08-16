package UninaSocialGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main{ 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n;
		List<Likes> Res_Likes = new ArrayList<Likes>();
		Likes_DAO GD = new Likes_DAO("system", "Database@03");
		
	
		GD.InsLike(2, "Genny03cry");
		
		//GD.UpCommento("Gabbo", 1, "Sono modificato");
		
		
		
		//System.out.println("Numero like: " + Res_Likes.size());	
		
		//Res_Likes = GD.SelNotificheContenutiUtente("errore31");
		
		n = GD.SelNumLike(2);
		System.out.println("Numero like: " + n);
		
//		for(int i=0; i<Res_Likes.size(); i++) {
//			Likes R;
//			R=Res_Likes.get(i);
//			System.out.println("Testo: "+ R.getTesto());			
//		}
		
		 GD.Close_Connection();
	}
} 
