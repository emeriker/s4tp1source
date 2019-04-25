package controleur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modele.analyseur.Analyseur;
import modele.analyseur.IBook;
import modele.analyseur.IMot;
import modele.library.Library;

public class ControleurPrincipal extends Application {

	@FXML
	private Pane root;

	@FXML
	private VuePrincipale vuePrincipale;

	private ObservableList<String> obListComboBoxLivre; // la liste des livre analyser
	private ObservableList<String> obListRechercherMot; // le resultat de la recherche
	private Analyseur analyseur;
	private ArrayList<String> bookList;

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/layout/MainVue.fxml"));
		root = (Pane) fxmlLoader.load();

		vuePrincipale = fxmlLoader.<VuePrincipale>getController();

		// Initialisation ComboxBox pour les livres
		obListComboBoxLivre = FXCollections.observableArrayList();
		bookList = new ArrayList<>();
		bookList.add("Tout les livres");
		vuePrincipale.getComboBoxLivre().setItems(obListComboBoxLivre);

		// Initialisation ListView pour les mots rechercher
		obListRechercherMot = FXCollections.observableArrayList();
		vuePrincipale.getListViewMotRechercher().setItems(obListRechercherMot);

		ajouterEcouteurs();

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("TP - 1");
		primaryStage.setResizable(false);
		primaryStage.show();

		// pour initiliser l'analyseur et la liste observable sans ralentir FX
		Runnable task = () -> {
			analyseur = new Analyseur(new Library("livre"));
			// Observer, attend de finir du loader de fichier pour activer les buttons.
			analyseur.addObserver((Observable o, Object arg) -> {
				vuePrincipale.getBoutonAnalyser().setDisable(false);
				vuePrincipale.getBoutonRechercher().setDisable(false);
				vuePrincipale.getComboBoxLivre().setDisable(false);
			});
			bookList.addAll(analyseur.getLibrary().getBookList());
			setObList(obListComboBoxLivre, bookList);
		};

		Thread thread = new Thread(task, "Analyseur");
		thread.start();
	}

	private void ajouterEcouteurs() {
		vuePrincipale.getBoutonAnalyser().setOnAction(new EcouteurBoutons());
		vuePrincipale.getBoutonRechercher().setOnAction(new EcouteurBoutons());
	}

	private class EcouteurBoutons implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			if (e.getSource() instanceof Button) {
				if (e.getSource() == vuePrincipale.getBoutonAnalyser()) {
					analyser();
				}
				if (e.getSource() == vuePrincipale.getBoutonRechercher()) {
					rechercherMot(vuePrincipale.getComboBoxLivre().getValue(),
							vuePrincipale.getTexteFieldMotRechercher().getText());
				}
			}
		}

		// cree une instance de "FragmentLivre" pour chaque livre
		private void analyser() {
			vuePrincipale.getvBoxLivre().getChildren().clear();
			// pour tout les livres
			vuePrincipale.getvBoxLivre().getChildren()
					.add(creeFragmentLivre(analyseur.getLibrary().masterBookLibrary()));
			// pour chaque livre
			ExecutorService executor = Executors.newFixedThreadPool(4);
			for (String book : analyseur.getLibrary().keySet()) {
				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						TitledPane tp = creeFragmentLivre(analyseur.getLibrary().get(book));
						Platform.runLater(() -> {
							vuePrincipale.getvBoxLivre().getChildren().add(tp);
						});
					}
				});
				executor.execute(thread);
			}
			executor.shutdown();
		}

		private TitledPane creeFragmentLivre(IBook book) {
			TitledPane livre = null;
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/layout/FragmentLivre.fxml"));
				livre = (TitledPane) fxmlLoader.load();
				FragmentLivre fragmentLivre = fxmlLoader.<FragmentLivre>getController();

				fragmentLivre.setTitledPaneTitle(book.getTitle());
				fragmentLivre.setTitledPaneExpanded(false); // fermer le panneau
				fragmentLivre.setTexteMotPlusLong(analyseur.getMotPlusLong(book).getMot());
				fragmentLivre.setTexteMotPlusCourt(analyseur.getMotPlusCourt(book).getMot());
				fragmentLivre.setTexteMotPlusNombreux(analyseur.getPlusNombreux(book).get(0).getMot());
				fragmentLivre.setTexteMotPlusRare(analyseur.getPlusRare(book).get(0).getMot());
				fragmentLivre.setTexteMoyenneOccurence(String.valueOf(analyseur.getNombreMoyenOccurencesParMot(book)));
				fragmentLivre.setTexteNombreDeMot(String.valueOf(analyseur.getNombreDeMots(book)));
				fragmentLivre.setTexteNombreDeMotDisctinct(String.valueOf((book).size()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return livre;
		}

		private void rechercherMot(String book, String mot) {
			obListRechercherMot.clear();
			int nbrMotTrouver = 0;
			if (book != null && mot != null) {
				if (book.equals("Tout les livres")) {
					setObListIMotToString(obListRechercherMot, analyseur.getLibrary().masterBookLibrary().get(mot));
					if (analyseur.getLibrary().masterBookLibrary().get(mot)!=null)
					nbrMotTrouver = analyseur.getLibrary().masterBookLibrary().get(mot).size();
				} else {
					setObListIMotToString(obListRechercherMot, analyseur.getLibrary().get(book).get(mot));
					if (analyseur.getLibrary().get(book).get(mot) != null)
						nbrMotTrouver = analyseur.getLibrary().get(book).get(mot).size();
				}
				final int nombreFinal = nbrMotTrouver;
				Platform.runLater(() -> {
					vuePrincipale.setTexteFieldNombreDeMot(String.valueOf(nombreFinal));
					;
				});
			}
		}
	}

	// createur de liste observable
	private <K> boolean setObList(ObservableList<K> obList, List<K> list) {
		boolean ret = false;
		if (obList != null && list != null)
			ret = obList.addAll(list);
		return ret;
	}

	// Transfer les listes de <IMot> en <String> dans une liste Obervable
	private void setObListIMotToString(ObservableList<String> obList, List<IMot> list) {

		List<String> newList = new ArrayList<String>();
		if (list != null) {
			for (IMot mot : list) {
				newList.add(mot.toString());
			}
		}
		setObList(obList, newList);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
