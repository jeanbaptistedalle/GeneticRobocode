package jgap.ag;

import org.jgap.Configuration;
import org.jgap.InvalidConfigurationException;
import org.jgap.RandomGenerator;

public class GeneOnScan extends Generobocode {

	public GeneOnScan(Configuration a_conf, ContenuGene s) throws InvalidConfigurationException {
		super(a_conf, s);
		// TODO Auto-generated constructor stub
	}

	public void setToRandomValue(RandomGenerator a_numberGenerator) {
		final TableauContenuGene tab = TableauContenuGene.getInstance();
		 int rand = a_numberGenerator.nextInt((21-15)+1)+15;
		 this.getAllele().setCode(tab.getListCode(rand));
		 this.getAllele().setIndex(tab.getReverseIndex(rand));
	}

	
	
}
