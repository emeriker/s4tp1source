package utilitaire;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import modele.analyseur.IBook;
import modele.library.BookFactory;

public class GestionFichier {

	private static GestionFichier gestionFichier;
	private static String splitter;
	private int threadNumber = 4; // pour le multithreads

	public static GestionFichier getInstance() {
		if (gestionFichier == null) {
			gestionFichier = new GestionFichier();
		}
		return gestionFichier;
	}

	private GestionFichier() {
		GestionPropieties gestionPropieties = GestionPropieties.getInstance();
		splitter = gestionPropieties.getStringProperty("spliter");
	}

	public LinkedHashMap<String, IBook> loadAllTxtFilesSingleThread(String dossier) {
		dossier = "/" + dossier;
		LinkedHashMap<String, IBook> bookMap = new LinkedHashMap<String, IBook>();
		File f = null;
		try {
			f = new File(getClass().getResource(dossier).toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		System.out.println(LocalTime.now());
		for (File bookName : f.listFiles()) {
			bookMap.put(bookName.getName(), loadTxtFile(dossier, bookName.getName()));
		}
		System.out.println(LocalTime.now());
		return bookMap;
	}

	public ConcurrentHashMap<String, IBook> loadAllTxtFilesMultiThread(String dossier) {
		dossier = "/" + dossier;
		final String finalDossier = dossier;
		ConcurrentHashMap<String, IBook> bookMap = new ConcurrentHashMap<>();
		File f = null;
		try {
			f = new File(getClass().getResource(dossier).toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		ExecutorService executor = Executors.newFixedThreadPool(threadNumber);
		for (File bookName : f.listFiles()) {

			Thread thread1 = new Thread(new Runnable() {
				@Override
				public void run() {
					bookMap.put(bookName.getName(), loadTxtFile(finalDossier, bookName.getName()));
				}
			});
			executor.execute(thread1);

		}
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		return bookMap;
	}

	public IBook loadTxtFile(String dossier, String bookName) {
		String urlBook = (dossier + "/" + bookName);
		String titre = "";
		String extension = "";
		IBook book;
		InputStream is = this.getClass().getResourceAsStream(urlBook);
		DataInputStream in = new DataInputStream(is);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String[] token2 = bookName.split(splitter);
		for (String s : token2) {
			if (titre.isEmpty())
				titre = s.replaceAll(" ", "");
			else
				extension = s.replaceAll(" ", "").toLowerCase();
		}

		book = BookFactory.getInstance().CreateBook(titre, extension, br);
		return book;
	}

}
