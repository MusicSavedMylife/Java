package application;
	
import application.Models.ScoreModel;
import application.ViewModels.SceneConnection;
import application.ViewModels.SceneJeu1Model;
import application.ViewModels.SceneJeu2Model;
import application.ViewModels.SceneJeu3Model;
import application.ViewModels.SceneJeu5Model;
import application.ViewModels.SceneJeu6Model;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application 
{
	public static ScoreModel scoreModel;
	public static SceneLoader sceneLoader;
	public static SceneConnection sceneConnection;
	public static GameManager gameManager;
	public static SceneJeu1Model sceneJeu1;
	public static SceneJeu2Model sceneJeu2;
	public static SceneJeu3Model sceneJeu3;
	public static SceneJeu5Model sceneJeu5;
	public static SceneJeu6Model sceneJeu6;

	public static int niveau_difficult=1;
	
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			scoreModel = new ScoreModel();
			scoreModel.createTable();
			sceneConnection = new SceneConnection();
			gameManager =  new GameManager();
			sceneJeu1 = new SceneJeu1Model();
			sceneJeu2 = new SceneJeu2Model();
			sceneJeu3 = new SceneJeu3Model();
			sceneJeu5 = new SceneJeu5Model();
			sceneJeu6 = new SceneJeu6Model();
			sceneLoader = new SceneLoader(primaryStage);
			sceneLoader.switchTo(SceneLoader.SCENE_CONNECTION);
			primaryStage.show();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}
