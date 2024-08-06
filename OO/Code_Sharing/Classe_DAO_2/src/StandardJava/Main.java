package StandardJava;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main{ 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Gruppi> Res_Gruppi = new ArrayList<Gruppi>();
		Gruppi_DAO GD = new Gruppi_DAO("system", "Unina@03");
		
//		Gruppi G = GD.SelSigGruppo("SSC_Napoli_Ultras");
//		System.out.println("Nome del gruppo: "+ G.getNome());
//		Res_Gruppi = GD.SelAllGruppo();
//		
//		for(int i=0; i<Res_Gruppi.size(); i++) {
//			
//			Gruppi R;
//			R=Res_Gruppi.get(i);
//			System.out.println(R.getNome());
//			
//		}
	
		GD.InsGruppo("Ludopatici", "Gruppo bello", "Gabbo");
	}
} 


