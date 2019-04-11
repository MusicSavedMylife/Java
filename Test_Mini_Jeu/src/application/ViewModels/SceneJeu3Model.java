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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SceneJeu3Model implements Initializable
{
	@FXML
	protected ImageView I1;
	@FXML
	protected ImageView I2;
	@FXML
	protected ImageView I3;
	@FXML
	protected ImageView I4;
	@FXML
	protected ImageView I5;
	@FXML
	protected ImageView I6;
	@FXML
	protected ImageView I7;
	@FXML
	protected ImageView I8;
	@FXML
	protected ImageView I9;
	@FXML
	protected ImageView I10;
	@FXML
	protected Text Txt;
	
	private int compteur_tir = 0;
	private ArrayList<ImageView> Tab_Image = new ArrayList<ImageView>();
	private int temps;
	
	public void LancementJeu3()
	{
		try 
		{
			Main.sceneLoader.switchTo(SceneLoader.SCENE_JEU3);
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
			this.temps=10;
		}
		else if(Main.scoreModel.getDifficulte()==2)
		{
			this.temps=7;
		}
		else if(Main.scoreModel.getDifficulte()==3)
		{
			this.temps=5;
		}
		Txt.setText("Temps restant : "+temps+" !");
		tm.setCycleCount(this.temps);
		tm.play();
		Tab_Image.add(I1);Tab_Image.add(I2);Tab_Image.add(I3);
		Tab_Image.add(I4);Tab_Image.add(I5);Tab_Image.add(I6);
		Tab_Image.add(I7);Tab_Image.add(I8);Tab_Image.add(I9);
		Tab_Image.add(I10);

		for(int i=0;i<10;i++)
		{
			Tab_Image.get(i).setLayoutX((int)(Math.random() * 303)+25);
			Tab_Image.get(i).setLayoutY((int)(Math.random() * 375)+25);
		}
	}

	public void ActionTime()
	{
		this.temps--;
		if(this.temps==0)
		{
			tm.stop();
			try
			{
				Main.sceneLoader.switchTo(SceneLoader.SCENE_LOOSE);
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		Txt.setText("Temps restant : "+this.temps+" !");
	}
	
	public void Tir(MouseEvent event)
	{
		for(int i=0;i<10;i++)
		{
			if(Tab_Image.get(i).isPressed())
			{
				Tab_Image.get(i).setLayoutX(1000);
				this.compteur_tir++;
			}
		}
		fin();
	}
	public void fin()
	{
		if(this.compteur_tir==10)
		{
			Main.scoreModel.update(Main.scoreModel,"score");
			tm.stop();
			Main.gameManager.Timer();
		}
	}
}
