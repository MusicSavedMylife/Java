package application.ViewModels;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import application.SceneLoader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SceneJeu2Model implements Initializable
{
	@FXML
	protected Rectangle Rect;
	@FXML
	protected Circle C1,C2,C3,C4,C5,C6,C7,C8,C9,C10;
	@FXML
	protected Text Txt;

	private ArrayList<Circle> Tab_cercle = new ArrayList<Circle>(); 
	private int compteur_cercle = 0;
	private int temps;
	
	public void LancementJeu2()
	{
		try 
		{
			Main.sceneLoader.switchTo(SceneLoader.SCENE_JEU2);
		}catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	Timeline tm = new Timeline(new KeyFrame(Duration.millis(1000),ae -> ActionTime()));	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
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
			temps=12;
		}
		
		Txt.setText("Temps restant : "+temps+" !");
		tm.setCycleCount(temps);
		tm.play();
		Tab_cercle.add(C1);Tab_cercle.add(C2);Tab_cercle.add(C3);
		Tab_cercle.add(C4);Tab_cercle.add(C5);Tab_cercle.add(C6);
		Tab_cercle.add(C7);Tab_cercle.add(C8);Tab_cercle.add(C9);
		Tab_cercle.add(C10);
		for(int i=0;i<10;i++)
		{
			Tab_cercle.get(i).setLayoutX((int)(Math.random() * 303)+25);
			Tab_cercle.get(i).setLayoutY((int)(Math.random() * 375)+25);
		}
	}
	
	public void ActionTime()
	{
		temps--;
		if(temps==0)
		{
			tm.stop();
			try {
				Main.sceneLoader.switchTo(SceneLoader.SCENE_LOOSE);
			}catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		Txt.setText("Temps restant : "+temps+" !");
	}
	
	@FXML
	protected void BoutonJeu2(KeyEvent event)
	{		
		double posX = Rect.getX();
		double posY = Rect.getY();
		if(event.getCode() == KeyCode.RIGHT)
		{
			Rect.setX(posX+10);
		}
		else if(event.getCode() == KeyCode.LEFT)
		{
			Rect.setX(posX-10);
		}
		else if(event.getCode() == KeyCode.UP)
		{
			Rect.setY(posY-10);
		}
		else if(event.getCode() == KeyCode.DOWN)
		{
			Rect.setY(posY+10);
		}
		CheckCollisions();
	}
	
	public void CheckCollisions()
	{
		for(int i=0;i<10;i++)
		{
			if(Tab_cercle.get(i).getBoundsInParent().intersects(Rect.getBoundsInParent()))
			{
				Tab_cercle.get(i).setLayoutX(5000);
				compteur_cercle++;
			}
		}
		if(compteur_cercle==10)
		{
			Main.scoreModel.update(Main.scoreModel,"score");
			tm.stop();
			Main.gameManager.Timer();
		}
	}
}
