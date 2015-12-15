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
		final TableauGeneOnscan tab = TableauGeneOnscan.getInstance();
		 int rand = a_numberGenerator.nextInt(tab.size());
		 this.getAllele().setCode(tab.getListCode(rand));
		 this.getAllele().setIndex(tab.getReverseIndex(rand));
	}

	@Override
	protected GeneOnScan newGeneInternal() {
		try {
			return new GeneOnScan(getConfiguration(), gene);
		} catch (InvalidConfigurationException ex) {
			throw new IllegalStateException(ex.getMessage());
		}

	}
	
}
