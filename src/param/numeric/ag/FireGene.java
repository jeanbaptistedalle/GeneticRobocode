package param.numeric.ag;

import org.jgap.BaseGene;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.InvalidConfigurationException;
import org.jgap.RandomGenerator;
import org.jgap.UnsupportedRepresentationException;
import org.w3c.dom.ranges.Range;

public class FireGene extends BaseGene implements Gene {

	public static final int[] m_alphabet = {1,2,3,4,5,6,7,8,9,10};
	private int m_minLength;
	private int m_maxLength;
	private String m_value;
  
	
	
	
	
	public FireGene(final Configuration a_config, final int a_minLength,
            final int a_maxLength) throws InvalidConfigurationException {
    	super(a_config);
    	m_maxLength = a_maxLength;
    	m_minLength = a_minLength;
    }


	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void applyMutation(int arg0, double arg1) {
			int newValue = Integer.valueOf((String)getAllele()) + 1;
			setAllele(String.valueOf(newValue));
	}

	@Override
	public String getPersistentRepresentation()
			throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return m_value;
	}

	@Override
	public void setAllele(Object newAllele) {
		m_value = (String)newAllele;
		
	}

	@Override
	public Object getAllele() {
		return m_value;
		
	}
	
	
	@Override
	public void setToRandomValue(RandomGenerator a_numberGenerator) {
		
		// check errors in alphabet
		if (m_alphabet == null || m_alphabet.length < 1) {
		      throw new IllegalStateException("The valid alphabet is empty!");
		}
		if (m_maxLength < m_minLength || m_maxLength < 1 || m_minLength == 0 ) {
		      throw new IllegalStateException(
		          "Illegal valid maximum and/or minimum "
		          + "length of alphabet!");
		}
		
		// randomise lenght of string
		int length = 1;
	   /* length = m_maxLength - m_minLength + 1;
		int i = a_numberGenerator.nextInt() % length;
		if (i < 0) {
			i = -i;
		}
		length = m_minLength + i;
		*/
		// randomise allel
		
		int index; 
		String newAllele = "";
	    final int alphabetLength = m_alphabet.length;
	    //for (int j = 0; j < length; j++) {
	    	index = a_numberGenerator.nextInt(alphabetLength);
		    newAllele = String.valueOf(m_alphabet[index]);
		//}
	    
	    // Call setAllele to ensure extended verification.
	    // -----------------------------------------------
	    setAllele(newAllele);
		
	}

	@Override
	public void setValueFromPersistentRepresentation(String arg0)
			throws UnsupportedOperationException,
			UnsupportedRepresentationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Object getInternalValue() {
		return null;
	}

	@Override
	protected Gene newGeneInternal() {
		 try {
		      FireGene result = new FireGene(getConfiguration(), m_minLength, m_maxLength);
		      result.setConstraintChecker(getConstraintChecker());
		      return result;
		    } catch (InvalidConfigurationException iex) {
		      throw new IllegalStateException(iex.getMessage());
		    }
	}

}
