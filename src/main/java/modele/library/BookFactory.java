package modele.library;

import java.io.BufferedReader;
import java.io.IOException;
import modele.analyseur.IBook;
import modele.analyseur.IMot;

public class BookFactory {

	private static BookFactory mBookFactory;
	private final static String SPLITTER_TEXT = "[-+.^:,/\"_!?;()« »\\]\\[\\*\n\t]"; // les caracteres qui separe le
																						// text

	public static BookFactory getInstance() {
		if (mBookFactory == null) {
			mBookFactory = new BookFactory();
		}
		return mBookFactory;
	}

	private BookFactory() {
	}

	// lit le BufferedReader ligne par ligne
	public IBook CreateBook(String bookName, String extension, BufferedReader br) {

		IBook mBook = new Book(bookName, extension);
		int position = 1;
		int ligne = 0;
		String strLine;
		try {
			while ((strLine = br.readLine()) != null) {
				ligne++;
				String[] token2 = strLine.split(SPLITTER_TEXT, -1);
				for (String s : token2) {
					String k = s.replaceAll(" ", "").toLowerCase();
					if (!k.isEmpty()) {
						// System.out.println(k.length());
						// System.out.println(k);
						IMot mot = new Mot(bookName, k, ligne, position);
						mBook.addWord(mot);
						position++;
					}
				}
				position = 1;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mBook;
	}
}
