package application.ViewModels;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.SceneLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class TitleViewModel implements Initializable
{
	@FXML
	protected Text Txt = new Text("Difficulte : "+Main.niveau_difficult+" !");
	@FXML
	protected Text Score = new Text();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		Score.setText("Monsieur : "+Main.scoreModel.getPseudo()+" Vous avez : "+Main.scoreModel.getScore()+" points Lvl : "+Main.scoreModel.getDifficulte());
	}
	@FXML
	public void deconnection()
	{
		try 
		{
			Main.sceneLoader.switchTo(SceneLoader.SCENE_CONNECTION);
		}catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	@FXML
	public void choix_level()
	{
		Txt.setText("Difficulte : "+Main.niveau_difficult+" !");
		try
		{
			Main.sceneLoader.switchTo(SceneLoader.SCENE_LEVEL);
		}catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	@FXML
	public void Level1()
	{
		Main.niveau_difficult = 1;
		Main.scoreModel.setDifficulte(1);
		Main.scoreModel.update(Main.scoreModel, "difficulte");
		Txt.setText("Difficulte : "+Main.niveau_difficult+"   EZ !");
	}
	@FXML
	public void Level2()
	{
		Main.niveau_difficult = 2;
		Main.scoreModel.setDifficulte(2);
		Main.scoreModel.update(Main.scoreModel, "difficulte");
		Txt.setText("Difficulte : "+Main.niveau_difficult+"   Soit Rapide !");
	}
	@FXML
	public void Level3()
	{
		Main.niveau_difficult = 3;
		Main.scoreModel.setDifficulte(3);
		Main.scoreModel.update(Main.scoreModel, "difficulte");
		Txt.setText("Difficulte : "+Main.niveau_difficult+"   Bon Courage !");
	}
	@FXML
	public void Menu() 
	{
		try 
		{
			Main.sceneLoader.switchTo(SceneLoader.SCENE_MENU);
		}catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	@FXML
	public void LancerJeu() 
	{
		Main.gameManager.Timer();
	}
	
	@FXML
	public void Affiche_Regles()
	{
		try 
		{
			Main.sceneLoader.switchTo(SceneLoader.SCENE_REGLE);
		}catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	public void QuitterJeu()
	{
		System.exit(0);
	}
	
	@FXML
	public void Retour()
	{
		try 
		{
			Main.sceneLoader.switchTo(SceneLoader.SCENE_MENU);
		}catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
