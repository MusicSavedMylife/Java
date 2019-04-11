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
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class SceneJeu6Model implements Initializable
{
	@FXML
	protected GridPane grid;
	@FXML
	protected Rectangle snakeee=new Rectangle(), snakeee1=new Rectangle(), snakeee2=new Rectangle(), snakeee3=new Rectangle(), snakeee4=new Rectangle(), snakeee5=new Rectangle(), snakeee6=new Rectangle(), snakeee7=new Rectangle(), snakeee8=new Rectangle(), snakeee9=new Rectangle();
	@FXML
	protected Rectangle Food;
	
	private ArrayList<Rectangle> Tab_Snake = new ArrayList<Rectangle>();
	private Snake snake = new Snake();
	private Food food = new Food();
	private int direction = 1;
	private int[] ancienposX = new int[100];
	private int[] ancienposY = new int[100];
	
	public void LancementJeu6()
	{
		try
		{
			Main.sceneLoader.switchTo(SceneLoader.SCENE_JEU6);
		}catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	private double vitesse = 200/(Main.scoreModel.getDifficulte()+0.1);
	Timeline tm = new Timeline(new KeyFrame(Duration.millis(vitesse),ae -> avance()));	
	@SuppressWarnings("static-access")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		Tab_Snake.add(snakeee);Tab_Snake.add(snakeee1);Tab_Snake.add(snakeee2);Tab_Snake.add(snakeee3);
		Tab_Snake.add(snakeee4);Tab_Snake.add(snakeee5);Tab_Snake.add(snakeee6);Tab_Snake.add(snakeee7);
		Tab_Snake.add(snakeee8);Tab_Snake.add(snakeee9);

		for(int i=2;i<10;i++)
		{
			Tab_Snake.get(i).setVisible(false);
			Tab_Snake.get(i).setDisable(true);
		}
		food.posX = 5;
		food.posY = 5;
		grid.setRowIndex(Food,food.posX);
		grid.setColumnIndex(Food,food.posY);
		tm.setCycleCount(tm.INDEFINITE);
		tm.play();
	}

	@SuppressWarnings("static-access")
	public void avance()
	{
		if(snake.taille==10)
		{
			Main.scoreModel.update(Main.scoreModel,"score");
			tm.stop();
			Main.gameManager.Timer();
		}
		else if(snake.posX[0] == food.posX && snake.posY[0] == food.posY)
		{
			food.posX = ((int)(Math.random() * 29)+1);
			food.posY = ((int)(Math.random() * 29)+1);
			grid.setColumnIndex(Food,food.posX);
			grid.setRowIndex(Food,food.posY);
			snake.taille++;
			Tab_Snake.get(snake.taille-1).setVisible(true);
			Tab_Snake.get(snake.taille-1).setDisable(false);
		}
		switch (this.direction) 
		{
			case 1:
				for(int i=0;i<snake.taille;i++)
				{
					ancienposX[i] = snake.posX[i];
					ancienposY[i] = snake.posY[i];
				}
				snake.posX[0]++;
				checkMort();
				for(int i=0;i<snake.taille;i++)
				{
					snake.posX[i+1] = ancienposX[i];
					snake.posY[i+1] = ancienposY[i];
					grid.setColumnIndex(Tab_Snake.get(i),snake.posX[i]);
					grid.setRowIndex(Tab_Snake.get(i),snake.posY[i]);
				}
				this.direction=1;
				break;
			case -1:
				for(int i=0;i<snake.taille;i++)
				{
					ancienposX[i] = snake.posX[i];
					ancienposY[i] = snake.posY[i];
				}
				snake.posX[0]--;
				checkMort();
				for(int i=0;i<snake.taille;i++)
				{
					snake.posX[i+1] = ancienposX[i];
					snake.posY[i+1] = ancienposY[i];
					grid.setColumnIndex(Tab_Snake.get(i),snake.posX[i]);
					grid.setRowIndex(Tab_Snake.get(i),snake.posY[i]);
				}
				this.direction=-1;
				break;
			case 2:
				for(int i=0;i<snake.taille;i++)
				{
					ancienposX[i] = snake.posX[i];
					ancienposY[i] = snake.posY[i];
				}
				snake.posY[0]--;
				checkMort();
				for(int i=0;i<snake.taille;i++)
				{
					snake.posX[i+1] = ancienposX[i];
					snake.posY[i+1] = ancienposY[i];
					grid.setColumnIndex(Tab_Snake.get(i),snake.posX[i]);
					grid.setRowIndex(Tab_Snake.get(i),snake.posY[i]);
				}
				this.direction=2;
				break;
			case -2:
				for(int i=0;i<snake.taille;i++)
				{
					ancienposX[i] = snake.posX[i];
					ancienposY[i] = snake.posY[i];
				}
				snake.posY[0]++;
				checkMort();
				for(int i=0;i<snake.taille;i++)
				{
					snake.posX[i+1] = ancienposX[i];
					snake.posY[i+1] = ancienposY[i];
					grid.setColumnIndex(Tab_Snake.get(i),snake.posX[i]);
					grid.setRowIndex(Tab_Snake.get(i),snake.posY[i]);
				}
				this.direction=-2;
				break;
		}
	}
	public void checkMort()
	{
		if(snake.posX[0]<0 || snake.posX[0]>29 || snake.posY[0]<0 || snake.posY[0]>29)
		{
			snake.posX[0]++;snake.posY[0]++;
			tm.stop();
			try
			{
				Main.sceneLoader.switchTo(SceneLoader.SCENE_LOOSE);
			} catch (IOException e) 
			{
				
			}
		}
		for(int i=0;i<snake.taille;i++)
		{
			if(snake.posX[0]==snake.posX[i+1] && snake.posY[0]==snake.posY[i+1])
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
		}
	}
	@FXML
	public void change(KeyEvent event)
	{
		if(event.getCode() == KeyCode.RIGHT && this.direction!=-1)
		{
			this.direction=1;
		}
		else if(event.getCode() == KeyCode.LEFT && this.direction!=1)
		{
			this.direction=-1;
		}
		else if(event.getCode() == KeyCode.UP && this.direction!=-2)
		{
			this.direction=2;
		}
		else if(event.getCode() == KeyCode.DOWN && this.direction!=2)
		{
			this.direction=-2;
		}

	}
	private class Snake
	{
		private int[] posX = new int[100];
		private int[] posY = new int[100];
		private int taille=2;
		
		public Snake()
		{
			this.posX[this.taille-2]=11;
			this.posY[this.taille-2]=10;
			this.posX[this.taille-1]=10;
			this.posY[this.taille-1]=10;
		}
	}
	private class Food
	{
		private int posX;
		private int posY;
		public Food()
		{
			
		}
	}
}
