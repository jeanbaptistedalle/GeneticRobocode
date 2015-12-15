package jgap.ag;



import org.jgap.*;
import java.util.Random;

public class GeneInitialisation extends Generobocode {

	public GeneInitialisation(Configuration a_conf, ContenuGene s) throws InvalidConfigurationException {
		super(a_conf, s);
		// TODO Auto-generated constructor stub
	}

	public void setToRandomValue(RandomGenerator a_numberGenerator) {
		final TableauGeneInit tab = TableauGeneInit.getInstance();
		 int rand = a_numberGenerator.nextInt(tab.size());
		 this.getAllele().setCode(tab.getListCode(rand));
		 this.getAllele().setIndex(tab.getReverseIndex(rand));
	}
	
	@Override
	protected GeneInitialisation newGeneInternal() {
		try {
			return new GeneInitialisation(getConfiguration(), gene);
		} catch (InvalidConfigurationException ex) {
			throw new IllegalStateException(ex.getMessage());
		}

	}
	
	

}
