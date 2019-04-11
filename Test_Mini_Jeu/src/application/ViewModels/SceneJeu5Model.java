package application.ViewModels;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.SceneLoader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SceneJeu5Model implements Initializable
{
	@FXML
	protected Text Timer;
	@FXML
	protected Text Txt;
	@FXML
	protected Text Txt1;
	@FXML
	protected Text Txt2;
	@FXML
	protected TextField TxtField;

	private char valeur_String_lettre;
	private String nouvelle_lettre;
	
	private int nb_bon=0,nb_pasbon=0;
	private int difficulte=0;
	private int temps;
	public void LancementJeu5()
	{
		try 
		{
			Main.sceneLoader.switchTo(SceneLoader.SCENE_JEU5);
		}catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	Timeline tm = new Timeline(new KeyFrame(Duration.millis(1000),ae -> ActionTime()));
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		if(Main.scoreModel.getDifficulte()==1)
		{
			temps=20;
			difficulte=10;
		}
		else if(Main.scoreModel.getDifficulte()==2)
		{
			temps=15;
			difficulte=5;
		}
		else if(Main.scoreModel.getDifficulte()==3)
		{
			temps=10;
			difficulte=1;
		}
		Txt1.setText("Nombre de bonnes lettres : "+nb_bon+" !");
		Txt2.setText("Nombre de mauvaises lettres("+difficulte+") : "+nb_pasbon+" !");
		
		valeur_String_lettre = (char)((Math.random() * 26)+65);
		nouvelle_lettre = String.valueOf(valeur_String_lettre);
		Txt.setText(nouvelle_lettre);
		
		Timer.setText("Temps restant : "+temps+" !");
		tm.setCycleCount(temps);
		tm.play();
	}
	public void ActionTime()
	{
		temps--;
		if(temps==0)
		{
			tm.stop();
			try {
				Main.sceneLoader.switchTo(SceneLoader.SCENE_LOOSE);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		Timer.setText("Temps restant : "+temps+" !");
	}
	public void Choix_lettre(KeyEvent event)
	{
		if(!TxtField.getText().equals(""))
		{
			if(event.getCode() == KeyCode.ENTER && TxtField.getText().equals(nouvelle_lettre))
			{
				TxtField.setText("");
				valeur_String_lettre = (char)((Math.random() * 26)+65);
				nouvelle_lettre = String.valueOf(valeur_String_lettre);
				Txt.setText(nouvelle_lettre);
				nb_bon++;
				Txt1.setText("Nombre de bonnes lettres : "+nb_bon+" !");
			}
			else if(event.getCode() == KeyCode.ENTER && !TxtField.getText().equals(nouvelle_lettre))
			{
				TxtField.setText("");
				nb_pasbon++;
				Txt2.setText("Nombre de mauvaises lettres("+difficulte+") : "+nb_pasbon+" !");
			}
			
			if(nb_bon==10)
			{
				Main.scoreModel.update(Main.scoreModel,"score");
				tm.stop();
				Main.gameManager.Timer();
			}
			else if(nb_pasbon==difficulte)
			{
				try {
					Main.sceneLoader.switchTo(SceneLoader.SCENE_LOOSE);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
