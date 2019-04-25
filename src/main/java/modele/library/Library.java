package modele.library;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

import modele.analyseur.IBook;
import modele.analyseur.ILibrary;
import utilitaire.GestionFichier;

public class Library extends LinkedHashMap<String, IBook> implements ILibrary {

	private static final long serialVersionUID = 1L;
	private IBook masterBook;

	public Library() {
	}

	public Library(String dossier) {
		ConcurrentHashMap<String, IBook> list = GestionFichier.getInstance().loadAllTxtFilesMultiThread(dossier);
		for (String book : list.keySet()) {
			addBook(list.get(book).getTitle(), list.get(book));
		}
	}

	public void addBook(String titre, IBook book) {
		put(titre, book);
		addMasterBookLibrary(book);
	}

	@Override
	public ArrayList<String> getBookList() {
		ArrayList<String> mList = new ArrayList<String>();
		for (String book : keySet()) {
			mList.add(get(book).getTitle());
		}
		return mList;
	}

	// Utiliser seulement pour debug, a effacer / modifier
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("\n\n");
		for (String book : keySet()) {
			int nbrMots = 0;
			for (String mot : get(book).keySet()) {
				nbrMots += get(book).get(mot).size();
			}
			sb.append("Title : " + get(book).getTitle() + "\nMot distinct : " + get(book).size() + "\nNombre de Mots : "
					+ nbrMots + "\n\n");
		}
		return "Library [Book = " + sb + "]";
	}

	public void addMasterBookLibrary(IBook book) {
		if (masterBook == null) {
			masterBook = book.getBlankBook();
			masterBook.setTitle("Tout les livres");
		}
		masterBook.mergeBook(book);
	}

	public IBook masterBookLibrary() {
		return masterBook;
	}
}
