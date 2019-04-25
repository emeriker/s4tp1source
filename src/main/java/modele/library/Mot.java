package modele.library;

import modele.analyseur.IMot;

public class Mot implements IMot {

	private String titre;
	private String mot;
	private int line;
	private int position;

	public Mot(String mTitre, String mMot, int mLine, int mPosition) {
		super();
		titre = mTitre;
		mot = mMot;
		line = mLine;
		position = mPosition;
	}

	public String getTitre() {
		return titre;
	}

	public String getMot() {
		return mot;
	}

	public int getLine() {
		return line;
	}

	public int getPosition() {
		return position;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mot == null) ? 0 : mot.hashCode());
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
		result = prime * result + line;
		result = prime * result + position;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mot other = (Mot) obj;
		if (mot == null) {
			if (other.mot != null)
				return false;
		} else if (!mot.equals(other.mot))
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		if (line != other.line)
			return false;
		if (position != other.position)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return titre + ", ligne : " + line + ", position : " + position;
	}
}
