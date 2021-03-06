package jgap.ag;

import org.jgap.*;
import java.lang.String;
import java.util.Random;


public abstract class Generobocode extends BaseGene implements Gene {
	protected ContenuGene gene;
	public Generobocode(Configuration a_conf, ContenuGene s) throws InvalidConfigurationException {
		super(a_conf);
		gene = s;
	}

//	@Override
//	protected Gene newGeneInternal() {
//		try {
//			return new Generobocode(getConfiguration(), gene);
//		} catch (InvalidConfigurationException ex) {
//			throw new IllegalStateException(ex.getMessage());
//		}
//
//	}

	@Override
	public int compareTo(Object othergene) {
		if (othergene instanceof Generobocode) {
			Generobocode other = (Generobocode) othergene;
			return gene.compareTo(other.getAllele());
		} else {
			throw new RuntimeException("Pas bien !");

		}
	}

	@Override
	public void setAllele(Object a_newValue) {
		gene = (ContenuGene) a_newValue;

	}

	public ContenuGene getAllele() {
		return gene;
	}

	@Override
	public String getPersistentRepresentation() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValueFromPersistentRepresentation(String a_representation)
			throws UnsupportedOperationException, UnsupportedRepresentationException {
		// TODO Auto-generated method stub

	}

//	@Override
//	public void setToRandomValue(RandomGenerator a_numberGenerator) {
//		final TableauContenuGene tab = TableauContenuGene.getInstance();
//		int rand = a_numberGenerator.nextInt(tab.size());
//		gene.setCode(tab.getListCode(rand));
//		gene.setIndex(tab.getReverseIndex(rand));
//		
//		 
//
//	}

	@Override
	public void applyMutation(int index, double a_percentage) {
		int reverse = gene.getIndex();
		if (this instanceof GeneInitialisation)
		{
			final TableauGeneInit tab = TableauGeneInit.getInstance();
			gene.setCode(tab.getListCode(reverse));
		}
		if (this instanceof GeneOnHitRobot)
		{
			final TableauGeneOnhitrobot tab = TableauGeneOnhitrobot.getInstance();
			gene.setCode(tab.getListCode(reverse));
		}
		if (this instanceof GeneOnHitWall)
		{
			final TableauGeneOnhitwall tab = TableauGeneOnhitwall.getInstance();
			gene.setCode(tab.getListCode(reverse));
		}
		if (this instanceof GeneOnScan)
		{
			final TableauGeneOnscan tab = TableauGeneOnscan.getInstance();
			gene.setCode(tab.getListCode(reverse));
		}
		if (this instanceof GeneRun)
		{
			final TableauGeneRun tab = TableauGeneRun.getInstance();
			gene.setCode(tab.getListCode(reverse));
		}
		

	}

	@Override
	protected Object getInternalValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
