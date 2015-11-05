package jgap.ag;

import org.jgap.Configuration;
import org.jgap.InvalidConfigurationException;
import org.jgap.RandomGenerator;

public class GeneRun extends Generobocode{

	public GeneRun(Configuration a_conf, ContenuGene s) throws InvalidConfigurationException {
		super(a_conf, s);
		// TODO Auto-generated constructor stub
	}

	public void setToRandomValue(RandomGenerator a_numberGenerator) {
		final TableauContenuGene tab = TableauContenuGene.getInstance();
		 int rand = a_numberGenerator.nextInt((14 - 7)+1) + 7;
		 this.getAllele().setCode(tab.getListCode(rand));
		 this.getAllele().setIndex(tab.getReverseIndex(rand));
	}
	

}
