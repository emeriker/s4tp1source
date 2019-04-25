package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import utilitaire.GestionPropieties;

public class VuePrincipale implements Initializable {

	@FXML
	private TextField texteFieldMotRechercher, texteFieldNombreDeMot;

	@FXML
	private ComboBox<String> comboBoxLivre;

	@FXML
	private Button boutonRechercher, boutonAnalyser;

	@FXML
	private ListView<String> listViewMotRechercher;

	@FXML
	private ScrollPane scrollPaneLivre;

	@FXML
	private VBox vBoxLivre;

	@FXML
	private Label labelNombreDeMot;

	public ComboBox<String> getComboBoxLivre() {
		return comboBoxLivre;
	}

	public Button getBoutonRechercher() {
		return boutonRechercher;
	}

	public void setBoutonRechercher(String value) {
		this.boutonRechercher.setText(value);
		;
	}

	public Button getBoutonAnalyser() {
		return boutonAnalyser;
	}

	public void setBoutonAnalyser(String value) {
		this.boutonAnalyser.setText(value);
	}

	public void setComboBoxLivre(ComboBox<String> comboBoxLivre) {
		this.comboBoxLivre = comboBoxLivre;
	}

	public ListView<String> getListViewMotRechercher() {
		return listViewMotRechercher;
	}

	public ScrollPane getScrollPaneLivre() {
		return scrollPaneLivre;
	}

	public void setScrollPaneLivre(ScrollPane scrollPaneLivre) {
		this.scrollPaneLivre = scrollPaneLivre;
	}

	public VBox getvBoxLivre() {
		return vBoxLivre;
	}

	public void setvBoxLivre(VBox vBoxLivre) {
		this.vBoxLivre = vBoxLivre;
	}

	public TextField getTexteFieldMotRechercher() {
		return texteFieldMotRechercher;
	}

	public void setTexteFieldMotRechercher(String value) {
		this.texteFieldMotRechercher.setPromptText(value);
	}

	private void setComboBoxLivre(String value) {
		comboBoxLivre.setPromptText(value);
	}

	public void setLabelNombreDeMot(String value) {
		this.labelNombreDeMot.setText(value);
	}

	public void setTexteFieldNombreDeMot(String value) {
		this.texteFieldNombreDeMot.setText(value);
	}

	public TextField getTexteFieldNombreDeMot() {
		return this.texteFieldNombreDeMot;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		GestionPropieties gestionPropieties = GestionPropieties.getInstance();
		setBoutonAnalyser(gestionPropieties.getStringProperty("Button-Analyser"));
		setBoutonRechercher(gestionPropieties.getStringProperty("Button-Rechercher"));
		setTexteFieldMotRechercher(gestionPropieties.getStringProperty("TextField-Mot-Rechercher"));
		setComboBoxLivre(gestionPropieties.getStringProperty("ComboBox-Vue-Principal"));
		setLabelNombreDeMot(gestionPropieties.getStringProperty("Label-NombreDeMot-Vue-Principal"));
		setTexteFieldNombreDeMot(gestionPropieties.getStringProperty("TextField-NombreDeMot-Vue-Principal"));
		getTexteFieldNombreDeMot().setEditable(false);

		getBoutonAnalyser().setDisable(true);
		getBoutonRechercher().setDisable(true);
		getComboBoxLivre().setDisable(true);
	}
}
