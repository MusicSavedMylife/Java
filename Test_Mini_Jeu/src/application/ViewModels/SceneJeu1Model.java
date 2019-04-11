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
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SceneJeu1Model implements Initializable
{
	@FXML
	protected ProgressBar PB;
	@FXML
	protected Text Txt;
	
	private double plus = 0.9;
	private boolean t = false;
	private int temps;
	
	public void LancementJeu1()
	{
		try 
		{
			Main.sceneLoader.switchTo(SceneLoader.SCENE_JEU1);
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
		}
		else if(Main.scoreModel.getDifficulte()==2)
		{
			temps=15;
		}
		else if(Main.scoreModel.getDifficulte()==3)
		{
			temps=10;
		}
		
		Txt.setText("Temps restant : "+temps+" !");
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
		Txt.setText("Temps restant : "+temps+" !");
	}
	
	@FXML
	protected void BoutonJeu1(KeyEvent event)
	{
		if(event.getCode() == KeyCode.LEFT)
		{
			if(plus<0.99 && t)
			{
				t = false;
				PB.setProgress(0.01+plus);
				plus = plus + 0.01;
			}
			else
			{
				plus = plus + 0.01;
			}
		}
		else if(event.getCode() == KeyCode.RIGHT)
		{
			if(plus<0.99 && !t)
			{
				plus = plus + 0.01;
				t = true;
				PB.setProgress(0.01+plus) ;
			}
			else
			{
				plus = plus + 0.01;
			}
		}
		if(plus>1)
		{
			Main.scoreModel.update(Main.scoreModel,"score");
			tm.stop();
			Main.gameManager.Timer();
		}
	}
}
