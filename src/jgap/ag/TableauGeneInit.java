package jgap.ag;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.univ.angers.GeneralVariables;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class TableauGeneInit {
	private List<ContenuGene> listGene;

	private static TableauGeneInit INSTANCE;
	
	private TableauGeneInit(final File f) {
		listGene = new ArrayList<ContenuGene>();
		FileReader fis;
		BufferedReader lecteur = null;
//		List<ContenuGene> list = new ArrayList<ContenuGene>();
		try {
			fis = new FileReader(f);
			lecteur = new BufferedReader(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ligne;
		try {
			ligne = lecteur.readLine();
			while (ligne != null) {
				ContenuGene Genetmp = new ContenuGene();

				Genetmp.setCode(ligne);
				ligne = lecteur.readLine();
				if (ligne == null) {
					throw new RuntimeException("Il manque une ligne !!");
				}
				Genetmp.setIndex(Integer.parseInt(ligne));
				ligne = lecteur.readLine();
				listGene.add(Genetmp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getReverseIndex(int n){
		
		return listGene.get(n).getIndex();
	}
	
	public String getListCode(int n){
		return listGene.get(n).getCode();
	}
	
	public int size() {
		return listGene.size();
	}
	
	public static TableauGeneInit getInstance(){
		if(INSTANCE == null){
			INSTANCE = new TableauGeneInit(new File(GeneralVariables.BLOC_GENE_FILE_PATH));
		}
		return INSTANCE;
	}
	
	
	public ContenuGene getContenuGene(int n){
		return listGene.get(n);
	}
	
	

}
