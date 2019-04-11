package application.ViewModels;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SceneJeu7Model implements Initializable
{
	private final Group root = new Group();
	private final Scene scene = new Scene(root, 600, 400, Color.WHITE);

	private ArrayList<Circle> Tab_Asteroides = new ArrayList<Circle>();
	private Rectangle Sante = new Rectangle(), Rep_Casse = new Rectangle(), Rep_Lent = new Rectangle();
	
	private int nombre_asteroide =(int)((Math.random() * 50)+20);
	private double[] vitess_aste = new double[nombre_asteroide];
	private ImageView Vaisseau = new ImageView("file:vaisseau1.png");
	private Vaisseau vaisseau = new Vaisseau();
	private int compteur=0;
	
	@SuppressWarnings("static-access")
	public void LancementJeu7(Stage primaryStage)
	{
		Vaisseau.setRotate(90);
		Vaisseau.setLayoutX(0);
		Vaisseau.setLayoutY(300);
		Sante.setHeight(10);Sante.setWidth(10);
		Rep_Casse.setHeight(10);Rep_Casse.setWidth(10);
		Rep_Lent.setHeight(10);Rep_Lent.setWidth(10);
		Sante.setFill(Color.RED);Rep_Casse.setFill(Color.BLUE);Rep_Lent.setFill(Color.YELLOW);
		Sante.setVisible(false);Rep_Casse.setVisible(false);Rep_Lent.setVisible(false);
		
		
		for(int i=0;i<nombre_asteroide;i++)
		{
			vitess_aste[i] = (Math.random() * 1)+(Math.random() * 0.5);
			Tab_Asteroides.add(new Circle());
			Tab_Asteroides.get(i).setVisible(true);
			Tab_Asteroides.get(i).setRadius((int)((Math.random() * 10)+5));
			Tab_Asteroides.get(i).setLayoutX((int)((Math.random() * 120*(i+3))+1400));
			Tab_Asteroides.get(i).setLayoutY((int)((Math.random() * 720)+10));
		}
		root.getChildren().addAll(Tab_Asteroides);
		root.getChildren().add(Vaisseau);
		root.getChildren().add(Sante);root.getChildren().add(Rep_Casse);root.getChildren().add(Rep_Lent);
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		tm.setCycleCount(tm.INDEFINITE);
		tm.play();
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event) 
			{
				if(event.getCode() == KeyCode.UP && Vaisseau.getLayoutY()>0)
				{
					Vaisseau.setLayoutY(Vaisseau.getLayoutY()-vaisseau.vitesse);
					vaisseau.posY = Vaisseau.getLayoutY();
				}
				else if(event.getCode() == KeyCode.DOWN && Vaisseau.getLayoutY()<670)
				{
					Vaisseau.setLayoutY(Vaisseau.getLayoutY()+vaisseau.vitesse);
					vaisseau.posY = Vaisseau.getLayoutY();
				}
			}
		});
	}
	public void checkCollisions()
	{
		if(Vaisseau.getBoundsInParent().intersects(Sante.getBoundsInParent()))
		{
			System.out.println("Sante");
			vaisseau.sante++;
			Sante.setLayoutX(-100);
		}
		else if(Vaisseau.getBoundsInParent().intersects(Rep_Casse.getBoundsInParent()))
		{
			System.out.println("Repare");
			vaisseau.repare();
			Rep_Casse.setLayoutX(-100);
		}
		else if(Vaisseau.getBoundsInParent().intersects(Rep_Lent.getBoundsInParent()))
		{
			System.out.println("vitesse*2");
			vaisseau.vitesse= vaisseau.vitesse*2;
			Rep_Lent.setLayoutX(-100);
		}
		
		
		for(int i=0;i<nombre_asteroide;i++)
		{
			if(Vaisseau.getBoundsInParent().intersects(Tab_Asteroides.get(i).getBoundsInParent()))
			{
				Tab_Asteroides.get(i).setRadius((int)((Math.random() * 10)+5));
				Tab_Asteroides.get(i).setLayoutX((int)((Math.random() * 120*(i+3))+1400));
				Tab_Asteroides.get(i).setLayoutY((int)((Math.random() * 720)+10));
				Tab_Asteroides.get(i).setFill(Color.RED);
				if(!vaisseau.casse) {vaisseau.problemes();}
				vaisseau.sante--;
			}
		}
		if(vaisseau.sante==0) {System.out.println("perdu");}
	}
	Timeline tm = new Timeline(new KeyFrame(Duration.millis(5),ae -> Asteroides_Avance()));
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		
	}
	
	boolean sante =false;
	boolean repcasse=false;
	boolean replent=false;
	
	public void Asteroides_Avance()
	{
		compteur++;
		if(compteur>2000)
		{
			int next = (int)((Math.random()*3)+1);
			switch(next)
			{
				case 1:
					Sante.setLayoutX((int)((Math.random() * 100)+1400));
					Sante.setLayoutY((int)((Math.random() * 720)+10));
					Sante.setVisible(true);
					sante=true;
					break;
				case 2: 
					Rep_Casse.setLayoutX((int)((Math.random() * 100)+1400));
					Rep_Casse.setLayoutY((int)((Math.random() * 720)+10));
					Rep_Casse.setVisible(true);
					repcasse=true;
					break;
				case 3: 
					Rep_Lent.setLayoutX((int)((Math.random() * 100)+1400));
					Rep_Lent.setLayoutY((int)((Math.random() * 720)+10));
					Rep_Lent.setVisible(true);
					replent=true;
					break;
			}
			compteur=0;
			if(Sante.getLayoutX()<-10)
			{
				sante=false;
			}
			else if(Rep_Casse.getLayoutX()<-10)
			{
				repcasse=false;
			}
			else if(Rep_Lent.getLayoutX()<-10)
			{
				replent=false;
			}
		}
		if(sante)
		{
			Sante.setLayoutX(Sante.getLayoutX()-1);
		}
		else if(repcasse)
		{
			Rep_Casse.setLayoutX(Rep_Casse.getLayoutX()-1);
		}
		else if(replent)
		{
			Rep_Lent.setLayoutX(Rep_Lent.getLayoutX()-1);
		}
		checkCollisions();
		for(int i=0;i<nombre_asteroide;i++)
		{
			if(Tab_Asteroides.get(i).getLayoutX()<-10)
			{
				vitess_aste[i] = (Math.random() * 1)+(Math.random() * 0.5);
				Tab_Asteroides.get(i).setRadius((int)((Math.random() * 10)+5));
				Tab_Asteroides.get(i).setLayoutX((int)((Math.random() * 120*i)+1400));
				Tab_Asteroides.get(i).setLayoutY((int)((Math.random() * 600)+10));
			}
			Tab_Asteroides.get(i).setLayoutX(Tab_Asteroides.get(i).getLayoutX()-vitess_aste[i]);
		}
	}
	
	public class Vaisseau
	{
		double posY;
		int problemes;
		int vitesse;
		int sante = 10;

		boolean casse=false;
		
		public Vaisseau()
		{
			this.posY = Vaisseau.getLayoutX();
			this.problemes = 0;
			this.vitesse = 10;
		}
		
		public void repare()
		{
			this.vitesse = -this.vitesse;
		}
		
		public void problemes()
		{
			this.problemes = (int)((Math.random()*59)+1);
			
			if(this.problemes<10)
			{
				System.out.println("Moteur lent");
				this.vitesse = this.vitesse/2;
				casse=true;
			}
			else if(this.problemes>10 && this.problemes<20)
			{
				System.out.println("Moteur casse");
				this.vitesse = -this.vitesse;
				casse=false;
			}
			else
			{
				this.problemes = 0;
			}
		}
	}
}
