package modele.library;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import modele.analyseur.IBook;
import modele.analyseur.IMot;

public class Book extends LinkedHashMap<String, ArrayList<IMot>> implements IBook, Cloneable {

	private static final long serialVersionUID = 1L;

	String title;
	String extension;

	public Book() {
	}

	public Book(String bookName) {
		setTitle(bookName);
	}

	public Book(String bookName, String extension) {
		setTitle(bookName);
		setExtension(extension);
	}

	public void addWord(IMot mot) {
		if (containsKey(mot.getMot())) {
			get(mot.getMot()).add(mot);
		} else {
			put(mot.getMot(), new ArrayList<IMot>());
			get(mot.getMot()).add(mot);
		}
	}

	public void mergeBook(IBook book) {
		for (String mot : book.keySet()) {
			for (int i = 0; i < book.get(mot).size(); i++) {
				addWord(book.get(mot).get(i));
			}
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String bookName) {
		title = bookName;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String mExtension) {
		extension = mExtension;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public IBook getBlankBook() {
		return new Book();
	}

}
