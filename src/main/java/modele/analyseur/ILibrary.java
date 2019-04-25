package modele.analyseur;

import java.util.ArrayList;
import java.util.Map;

public interface ILibrary extends Map<String, IBook> {

	public void addBook(String titre, IBook book);

	public ArrayList<String> getBookList();

	public IBook masterBookLibrary();

}
