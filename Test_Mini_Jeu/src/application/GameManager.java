package application;


import java.io.IOException;
import java.util.Random;
import application.Main;
import application.ViewModels.ReviewModel;

public class GameManager
{	
	private int nbOfGames = 6;
	private int winCount = 0;
	private Random rand;
	private int next=0;
	
	public void Timer()
	{
		if(this.winCount>=10)
		{
			//this.win();
		}
		else
		{
			ReviewModel reviewModel = new ReviewModel();
			reviewModel.LancementTimer();
		}
	}
	public void LoadNextGame()
	{
		int valeur=this.next;
		this.rand = new Random();
		this.next = rand.nextInt(nbOfGames)+1;
		
		//this.winCount++;
		while(valeur==this.next)
		{
			this.next = rand.nextInt(nbOfGames)+1;
		}
		next=3;
		switch(next)
		{
			case 1 :
					Main.sceneJeu1.LancementJeu1();
				break;
				
			case 2 :
					Main.sceneJeu2.LancementJeu2();
				break;
				
			case 3 :
					Main.sceneJeu3.LancementJeu3();
				break;
				
			case 4 :
					try 
					{
						Main.sceneLoader.switchTo(SceneLoader.SCENE_JEU4);
					}catch (IOException e) 
					{
						e.printStackTrace();
					}
				break;
			case 5 :
					Main.sceneJeu5.LancementJeu5();
				break;
			case 6 :
					Main.sceneJeu6.LancementJeu6();
				break;
			case 7 :
					try 
					{
						Main.sceneLoader.switchTo(SceneLoader.SCENE_JEU7);
					}catch (IOException e) 
					{
						e.printStackTrace();
					}
				break;
		}
	}

	public void win()
	{
		try 
		{
			Main.sceneLoader.switchTo(SceneLoader.SCENE_WIN);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void lose()
	{
		
	}

}
