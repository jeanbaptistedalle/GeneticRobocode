package jgap.ag;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.univ.angers.GeneralVariables;

public class TableauGeneRun {
	private List<ContenuGene> listGene;

	private static TableauGeneRun INSTANCE;
	
	private TableauGeneRun(final File f) {
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
	
	public static TableauGeneRun getInstance(){
		if(INSTANCE == null){
			INSTANCE = new TableauGeneRun(new File(GeneralVariables.BLOC_GENE_RUN_PATH));
		}
		return INSTANCE;
	}
	
	
	public ContenuGene getContenuGene(int n){
		return listGene.get(n);
	}

}
