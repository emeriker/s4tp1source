package jUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import modele.analyseur.Analyseur;
import modele.library.Library;

public class AnalyseurTest {
	
	
	@Test
	public void testGetLibrary() {
		Library test = new Library("livre");
		Analyseur analyseTest= new Analyseur(test);
		assertTrue(test==analyseTest.getLibrary());
		
	}

	@Test
	public void testSetLibrary() {
		Library test = new Library("livre");
		Analyseur analyseTest= new Analyseur(test);
		analyseTest.setLibrary(test);
		assertTrue(test == analyseTest.getLibrary());
	}
	

	@Test
	public void testGetNombreDeMots() {
		Library test = new Library("livre");
		Analyseur analyseTest= new Analyseur(test);
		assertTrue(1172680 == analyseTest.getNombreDeMots());
	}

	@Test
	public void testGetNombreDeMotsString() {
		Library test = new Library("livre");
		Analyseur analyseTest= new Analyseur(test);
		assertTrue(51513 == analyseTest.getNombreDeMots("Chénerol.txt"));
	}
	
	@Test
	public void testGetPlusNombreux() {
		Library test = new Library("livre");
		Analyseur analyseTest= new Analyseur(test);
		assertTrue("de".equals(analyseTest.getPlusNombreux("Chénerol.txt").get(0).getMot()));
	}


	@Test
	public void testGetPlusRareString() {
		Library test = new Library("livre");
		Analyseur analyseTest= new Analyseur(test);
		assertTrue("accessible".equals(analyseTest.getPlusRare("Chénerol.txt").get(0).getMot()));
	}

	@Test
	public void testGetNombreMoyenOccurencesParMotString() {
		Library test = new Library("livre");
		Analyseur analyseTest= new Analyseur(test);
		double testD=analyseTest.getNombreMoyenOccurencesParMot("Chénerol.txt");
		assertTrue(7.333855152130127==testD);
	}

	@Test
	public void testGetMotPlusLong() {
		Library test = new Library("livre");
		Analyseur analyseTest= new Analyseur(test);
		assertTrue("extraordinairement".equals(analyseTest.getMotPlusLong("Chénerol.txt").getMot()));
		
	}

	@Test
	public void testGetMotPlusCourtString() {
		Library test = new Library("livre");
		Analyseur analyseTest= new Analyseur(test);
		assertTrue("1".equals(analyseTest.getMotPlusCourt("Chénerol.txt").getMot()));
	}



}
