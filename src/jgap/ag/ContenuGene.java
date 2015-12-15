package jgap.ag;

import java.lang.String;

public class ContenuGene {
	private String code;
	private int index_inverse;
	/*
	 * example T[0] = "ahead(10)",1 T[1] = "back(10)",0 T[2] = "fire(10)",2 ,
	 * fire a pas d'inverse donc lui meme
	 */

	public ContenuGene() {

	}

	public ContenuGene(String c, int i) {
		code = c;
		index_inverse = i;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String s) {
		code = s;
	}

	public int getIndex() {
		return index_inverse;
	}

	public void setIndex(int i) {
		index_inverse = i;
	}

	public int compareTo(ContenuGene allele) {
		return code.compareTo(allele.getCode());
	}

	public String toString() {
		return code + " " + index_inverse;
	}

}
