package jgap.ag;



import org.jgap.*;
import java.util.Random;

public class GeneInitialisation extends Generobocode {

	public GeneInitialisation(Configuration a_conf, ContenuGene s) throws InvalidConfigurationException {
		super(a_conf, s);
		// TODO Auto-generated constructor stub
	}

	public void setToRandomValue(RandomGenerator a_numberGenerator) {
		final TableauContenuGene tab = TableauContenuGene.getInstance();
		 int rand = a_numberGenerator.nextInt((7 - 0));
		 this.getAllele().setCode(tab.getListCode(rand));
		 this.getAllele().setIndex(tab.getReverseIndex(rand));
	}
	
	

}
