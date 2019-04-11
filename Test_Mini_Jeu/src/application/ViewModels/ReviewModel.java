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
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class ReviewModel implements Initializable
{
	@FXML
	protected Circle R1;
	@FXML
	protected Circle R2;
	@FXML
	protected Circle R3;
	
	
	int compt = 4;
	Timeline tm = new Timeline(new KeyFrame(Duration.millis(1000),ae -> actionTime()));
	
	public void LancementTimer()
	{
		try {
			Main.sceneLoader.switchTo(SceneLoader.SCENE_TIME);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle Resources) 
	{
		R1.setVisible(false);
		R2.setVisible(false);
		R3.setVisible(false);
		tm.setCycleCount(4);
		tm.play();
	}
	protected void actionTime()
	{
		R1.setVisible(true);
		compt--;
		if(compt==0)
		{
			tm.stop();
			Main.gameManager.LoadNextGame();
		}
		else if(compt==2)
		{
			R2.setVisible(true);
		}
		else if(compt==1)
		{
			R3.setVisible(true);
		}
	}
}
