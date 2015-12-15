package jgap.ag;

import org.jgap.Configuration;
import org.jgap.InvalidConfigurationException;
import org.jgap.RandomGenerator;

public class GeneOnHitRobot extends Generobocode {

	public GeneOnHitRobot(Configuration a_conf, ContenuGene s) throws InvalidConfigurationException {
		super(a_conf, s);
		// TODO Auto-generated constructor stub
	}
	
	public void setToRandomValue(RandomGenerator a_numberGenerator) {
		final TableauGeneOnhitrobot tab = TableauGeneOnhitrobot.getInstance();
		 int rand = a_numberGenerator.nextInt(tab.size());
		 this.getAllele().setCode(tab.getListCode(rand));
		 this.getAllele().setIndex(tab.getReverseIndex(rand));
	}
	
	@Override
	protected GeneOnHitRobot newGeneInternal() {
		try {
			return new GeneOnHitRobot(getConfiguration(), gene);
		} catch (InvalidConfigurationException ex) {
			throw new IllegalStateException(ex.getMessage());
		}

	}



}
