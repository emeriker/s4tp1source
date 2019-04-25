package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Text;
import utilitaire.GestionPropieties;

public class FragmentLivre implements Initializable {

	@FXML
	private TitledPane tiledPaneLivre;

	@FXML
	private Label LabelNombreMotDistinct, labelMotPlusLong, labelMotPlusCourt, labelNombreMot, labelMotPlusRare,
			labelMoyenneOccurence, labelMotPlusNombreux;

	@FXML
	private Text texteMotPlusLong, texteNombreDeMot, texteNombreDeMotDisctinct, texteMoyenneOccurence,
			texteMotPlusCourt, texteMotPlusRare, texteMotPlusNombreux;

	public FragmentLivre() {
	}

	public void setTitledPaneTitle(String value) {
		tiledPaneLivre.setText(value);
	}

	public void setTitledPaneExpanded(boolean bool) {
		tiledPaneLivre.setExpanded(bool);
	}

	public void setTexteMotPlusLong(String value) {
		this.texteMotPlusLong.setText(value);
	}

	public void setTexteNombreDeMot(String value) {
		this.texteNombreDeMot.setText(value);
	}

	public void setTexteNombreDeMotDisctinct(String value) {
		this.texteNombreDeMotDisctinct.setText(value);
	}

	public void setTexteMoyenneOccurence(String value) {
		this.texteMoyenneOccurence.setText(value);
	}

	public void setTexteMotPlusCourt(String value) {
		this.texteMotPlusCourt.setText(value);
	}

	public void setTexteMotPlusRare(String value) {
		this.texteMotPlusRare.setText(value);
	}

	public void setTexteMotPlusNombreux(String value) {
		this.texteMotPlusNombreux.setText(value);
	}

	public void setLabelNombreMotDistinct(String value) {
		LabelNombreMotDistinct.setText(value);
	}

	public void setLabelMotPlusLong(String value) {
		this.labelMotPlusLong.setText(value);
	}

	public void setLabelMotPlusCourt(String value) {
		this.labelMotPlusCourt.setText(value);
	}

	public void setLabelNombreMot(String value) {
		this.labelNombreMot.setText(value);
	}

	public void setLabelMotPlusRare(String value) {
		this.labelMotPlusRare.setText(value);
	}

	public void setLabelMoyenneOccurence(String value) {
		this.labelMoyenneOccurence.setText(value);
	}

	public void setLabelMotPlusNombreux(String value) {
		this.labelMotPlusNombreux.setText(value);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		GestionPropieties gestionPropieties = GestionPropieties.getInstance();
		setLabelNombreMot(gestionPropieties.getStringProperty("Fragment-1"));
		setLabelNombreMotDistinct(gestionPropieties.getStringProperty("Fragment-2"));
		setLabelMoyenneOccurence(gestionPropieties.getStringProperty("Fragment-3"));
		setLabelMotPlusNombreux(gestionPropieties.getStringProperty("Fragment-4"));
		setLabelMotPlusRare(gestionPropieties.getStringProperty("Fragment-5"));
		setLabelMotPlusLong(gestionPropieties.getStringProperty("Fragment-6"));
		setLabelMotPlusCourt(gestionPropieties.getStringProperty("Fragment-7"));
	}
}
