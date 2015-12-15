package jgap.ag;

import org.jgap.Configuration;
import org.jgap.InvalidConfigurationException;
import org.jgap.RandomGenerator;

public class GeneOnHitWall extends Generobocode {

	public GeneOnHitWall(Configuration a_conf, ContenuGene s) throws InvalidConfigurationException {
		super(a_conf, s);
		// TODO Auto-generated constructor stub
	}
	
	public void setToRandomValue(RandomGenerator a_numberGenerator) {
		final TableauGeneOnhitwall tab = TableauGeneOnhitwall.getInstance();
		 int rand = a_numberGenerator.nextInt(tab.size());
		 this.getAllele().setCode(tab.getListCode(rand));
		 this.getAllele().setIndex(tab.getReverseIndex(rand));
	}
	
	@Override
	protected GeneOnHitWall newGeneInternal() {
		try {
			return new GeneOnHitWall(getConfiguration(), gene);
		} catch (InvalidConfigurationException ex) {
			throw new IllegalStateException(ex.getMessage());
		}

	}

}
