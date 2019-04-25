package modele.analyseur;

import java.util.ArrayList;
import java.util.Observable;

public class Analyseur extends Observable {

	public ILibrary library;

	public Analyseur(ILibrary library) {
		setLibrary(library);
	}

	public void setLibrary(ILibrary library) {
		this.library = library;
	}

	public ILibrary getLibrary() {
		this.setChanged();
		notifyObservers();
		return library;
	}

	public int getNombreDeMots() {
		int nbrMot = 0;
		for (String book : getLibrary().keySet()) {
			nbrMot += getNombreDeMots(getLibrary().get(book));
		}
		return nbrMot;
	}

	public int getNombreDeMots(String titre) {
		return getNombreDeMots(getLibrary().get(titre));
	}

	public int getNombreDeMots(IBook book) {

		int nbrMot = 0;
		for (String mot : book.keySet()) {
			nbrMot += book.get(mot).size();
		}
		return nbrMot;
	}

	public ArrayList<IMot> getPlusNombreux() {
		return getPlusNombreux(getLibrary().masterBookLibrary());
	}

	public ArrayList<IMot> getPlusNombreux(String titre) {
		return getPlusNombreux(getLibrary().get(titre));
	}

	public ArrayList<IMot> getPlusNombreux(IBook book) {
		ArrayList<IMot> mArrayMot = null;
		int nombre = 0;
		for (String mot : book.keySet()) {
			if (nombre < book.get(mot).size()) {
				nombre = book.get(mot).size();
				mArrayMot = book.get(mot);
			}
		}
		return mArrayMot;
	}

	public ArrayList<IMot> getPlusRare() {
		return getPlusRare(getLibrary().masterBookLibrary());
	}

	public ArrayList<IMot> getPlusRare(String titre) {
		return getPlusRare(getLibrary().get(titre));
	}

	public ArrayList<IMot> getPlusRare(IBook book) {
		ArrayList<IMot> mArrayMot = null;
		int nombre = 999999;
		for (String mot : book.keySet()) {
			if (nombre > book.get(mot).size()) {
				nombre = book.get(mot).size();
				mArrayMot = book.get(mot);
				if (nombre == 1)
					break;
			}
		}
		return mArrayMot;
	}

	public float getNombreMoyenOccurencesParMot() {
		return getNombreMoyenOccurencesParMot(getLibrary().masterBookLibrary());
	}

	public float getNombreMoyenOccurencesParMot(String titre) {
		return getNombreMoyenOccurencesParMot(getLibrary().get(titre));
	}

	public float getNombreMoyenOccurencesParMot(IBook book) {
		return ((float) getNombreDeMots(book)) / (float) (book.keySet().size());
	}

	public IMot getMotPlusLong() {
		return getMotPlusLong(getLibrary().masterBookLibrary());
	}

	public IMot getMotPlusLong(String titre) {
		return getMotPlusLong(getLibrary().get(titre));
	}

	public IMot getMotPlusLong(IBook book) {
		IMot mMot = null;
		int longeur = -1;
		for (String Mot : book.keySet()) {
			if (longeur < book.get(Mot).get(0).getMot().length()) {
				longeur = book.get(Mot).get(0).getMot().length();
				mMot = book.get(Mot).get(0);
			}
		}
		return mMot;
	}

	public IMot getMotPlusCourt() {
		return getMotPlusCourt(getLibrary().masterBookLibrary());
	}

	public IMot getMotPlusCourt(String titre) {
		return getMotPlusCourt(getLibrary().get(titre));
	}

	public IMot getMotPlusCourt(IBook book) {
		IMot mMot = null;
		int longeur = 9999999;
		for (String mot : book.keySet()) {
			if (longeur > book.get(mot).get(0).getMot().length()) {
				longeur = book.get(mot).get(0).getMot().length();
				mMot = book.get(mot).get(0);
			}
			if (longeur == 1)
				break;
		}
		return mMot;
	}
}
