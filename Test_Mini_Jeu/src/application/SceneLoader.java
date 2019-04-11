package application;

import java.io.IOException;

import application.ViewModels.SceneJeu4Model;
import application.ViewModels.SpaceInvaders;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneLoader 
{
	private Stage stage;
	public static final int SCENE_MENU = 1;
	public static final int SCENE_TIME = 2;
	public static final int SCENE_JEU1 = 3;
	public static final int SCENE_JEU2 = 4;
	public static final int SCENE_JEU3 = 5;
	public static final int SCENE_JEU4 = 6;
	public static final int SCENE_JEU5 = 7;
	public static final int SCENE_JEU6 = 8;
	public static final int SCENE_JEU7 = 9;
	public static final int SCENE_WIN = 10;
	public static final int SCENE_LOOSE = 11;
	public static final int SCENE_REGLE = 12;
	public static final int SCENE_LEVEL = 13;
	public static final int SCENE_CONNECTION = 0;
	
	public SceneLoader(Stage stage)
	{
		this.stage = stage;
	}

	public void switchTo(int scene) throws IOException
	{
		switch(scene)
		{
			case SCENE_MENU :
				Menu(stage);
				break;
			case SCENE_TIME : 
				Decompte(stage);
				break;
			case SCENE_JEU1 : 
				Jeu1(stage);
				break;
			case SCENE_JEU2 : 
				Jeu2(stage);
				break;
			case SCENE_JEU3 : 
				Jeu3(stage);
				break;
			case SCENE_JEU4 : 
				Jeu4(stage);
				break;
			case SCENE_JEU5 : 
				Jeu5(stage);
				break;
			case SCENE_JEU6 : 
				Jeu6(stage);
				break;
			case SCENE_JEU7 : 
				Jeu7(stage);
				break;
			case SCENE_WIN : 
				Win(stage);
				break;
			case SCENE_LOOSE: 
				GameOver(stage);
				break;
			case SCENE_REGLE :
				Regle(stage);
				break;
			case SCENE_LEVEL :
				Level(stage);
				break;
			case SCENE_CONNECTION :
				Connection(stage);
				break;
		}
	}
	private void Connection(Stage stage) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/Connection.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root,600,400);
		stage.setScene(scene);
	}
	private void Menu(Stage stage) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/Menu.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root,600,400);
		stage.setScene(scene);
	}
	private void Decompte(Stage stage) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/Decompte.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root,600,400);
		stage.setScene(scene);
	}
	private void Jeu1(Stage stage) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/Jeu.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root,600,400);
		stage.setScene(scene);
	}
	private void Jeu2(Stage stage) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/Jeu2.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root,600,400);
		stage.setScene(scene);
	}
	private void Jeu3(Stage stage) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/Jeu3.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root,328,400);
		stage.setScene(scene);
	}
	private void Jeu4(Stage stage) throws IOException
	{
		SceneJeu4Model sceneJeu4 = new SceneJeu4Model();
		sceneJeu4.LancementJeu4(stage);
	}
	private void Jeu5(Stage stage) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/Jeu5.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root,600,400);
		stage.setScene(scene);
	}
	private void Jeu6(Stage stage) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/Jeu6.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root,600,400);
		stage.setScene(scene);
	}
	private void Jeu7(Stage stage) throws IOException
	{
		SpaceInvaders spaceInvaders = new SpaceInvaders();
		spaceInvaders.InitialiseJeu7(stage);
	}
	private void Win(Stage stage) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/Win.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root,600,400);
		stage.setScene(scene);
	}
	private void GameOver(Stage stage) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/GameOver.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root,600,400);
		stage.setScene(scene);
	}
	private void Regle(Stage stage) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/Regle.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root,600,400);
		stage.setScene(scene);
	}
	private void Level(Stage stage) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/Level.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root,600,400);
		stage.setScene(scene);
	}
}
