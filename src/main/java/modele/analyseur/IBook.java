package modele.analyseur;

import java.util.ArrayList;
import java.util.Map;

public interface IBook extends Map<String, ArrayList<IMot>> {

	public void addWord(IMot mot);

	public void mergeBook(IBook book);

	public String getTitle();

	public void setTitle(String bookName);

	public IBook getBlankBook();

}
