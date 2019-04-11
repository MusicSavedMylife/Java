package application.ViewModels;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SpaceInvaders implements Initializable
{
	///Mettre en place la scene
	private final Group root = new Group();
	private final Scene scene = new Scene(root, 600, 400, Color.WHITE);
	private SceneDeJeu sceneDeJeu;
	///ASTEROIDES
	private int compteur=0;
	private boolean creer_un_soin = false;
	private boolean deja_un_soin = false;
	
	Timeline tm = new Timeline(new KeyFrame(Duration.millis(5),ae -> UpdateJeu()));
	@SuppressWarnings("static-access")
	public void InitialiseJeu7(Stage primaryStage)
	{
		sceneDeJeu = new SceneDeJeu();
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		tm.setCycleCount(tm.INDEFINITE);
		tm.play();
	}
	
	public class SceneDeJeu
	{
		private ArrayList<Asteroides> Tab_Asteroides = new ArrayList<Asteroides>();
		private int nombre_asteroides = (int)((Math.random()*20)+10);
		private Vaisseau vaisseau = new Vaisseau();
		private Objet Soin = new Objet();
		//INTERFACE DE SCENE//
		private Rectangle tableau_de_bord = new Rectangle();
		private Rectangle vie=new Rectangle(70,70),casse=new Rectangle(70,70),lent=new Rectangle(70,70);
		private final ImageView Vie = new ImageView("file:Coeur.png");
		private final ImageView Casse = new ImageView("file:cle.png");
		private final ImageView Lent = new ImageView("file:Moteur.png");
		private Text Sante = new Text();
		private Text Failure = new Text();
		
		private int etat = 3;
		
		public SceneDeJeu()
		{
			Sante.setLayoutX(10);Sante.setLayoutY(640);
			Failure.setLayoutX(10);Failure.setLayoutY(660);
			Sante.setText("Sante = "+vaisseau.sante);
			Failure.setText("Failure = ");
			tableau_de_bord.setLayoutX(0);tableau_de_bord.setLayoutY(610);
			tableau_de_bord.setWidth(1280);tableau_de_bord.setHeight(110);
			tableau_de_bord.setStrokeWidth(5);tableau_de_bord.setStroke(Color.BLUE);
			tableau_de_bord.setFill(Color.GREY);
			Lent.setLayoutX(1182);Lent.setLayoutY(630);lent.setLayoutX(1182);lent.setLayoutY(630);lent.setStrokeWidth(10);lent.setStroke(Color.BLACK);
			Casse.setLayoutX(1100);Casse.setLayoutY(630);casse.setLayoutX(1100);casse.setLayoutY(630);casse.setStrokeWidth(10);casse.setStroke(Color.BLACK);
			Vie.setLayoutX(1018);Vie.setLayoutY(630);vie.setLayoutX(1018);vie.setLayoutY(630);vie.setStrokeWidth(10);vie.setStroke(Color.BLACK);
			
			for(int i=0;i<nombre_asteroides;i++)
			{
				Tab_Asteroides.add(new Asteroides());
			}

			root.getChildren().add(tableau_de_bord);
			root.getChildren().add(vie);root.getChildren().add(Vie);
			root.getChildren().add(lent);root.getChildren().add(Lent);
			root.getChildren().add(casse);root.getChildren().add(Casse);
			root.getChildren().add(Sante);root.getChildren().add(Failure);
		}
		
		public void Change_Couleur(int etat)
		{
			if(etat==0) 
			{
				this.vie.setStroke(Color.RED);
				this.etat=0;
			}
			else if(etat==1) 
			{
				this.casse.setStroke(Color.YELLOW);
				this.etat=1;
			}
			else if(etat==2) 
			{
				this.lent.setStroke(Color.ORANGE);
				this.etat=2;
			}
			else if(etat==3)
			{
				this.vie.setStroke(Color.BLACK);this.casse.setStroke(Color.BLACK);this.lent.setStroke(Color.BLACK);
				this.etat=3;
			}
		}
		
		private int compt = 0;
		public void update()
		{
			if(this.etat<3)
			{
				compt++;
			}
			if(compt>200)
			{
				this.etat=3;
				compt=0;
			}
			if(this.etat==3) {this.Change_Couleur(3);}
			else { this.Change_Couleur(this.etat);}

			for(int i=0;i<this.nombre_asteroides;i++)
			{
				this.vaisseau.CheckCollisions(this.Tab_Asteroides);		//------COLLISIONS-----//
				this.Tab_Asteroides.get(i).CheckPosx();				//------POSITION ASTEROIDES-----//
				this.Tab_Asteroides.get(i).update();					//------AVANCEMENT ASTEROIDES-----//
			}
			this.vaisseau.CheckSante();
			Sante.setText("Sante = "+vaisseau.sante);
			if(vaisseau.lent) {this.Failure.setText("Failure = Vos recateurs sont deffectueux !");}
			else if(vaisseau.casse){this.Failure.setText("Failure = Votre moteur est casse !");}
			else {this.Failure.setText("Failure =");}
		}
		
	}
	
	//UPDATE DE MON ECRAN
	private void UpdateJeu()
	{
		compteur++;
		if(compteur>1000)
		{
			creer_un_soin=true;
		}
	
		if(creer_un_soin && !deja_un_soin)
		{
			sceneDeJeu.Soin = new Objet();
			deja_un_soin=true;
		}
		else if(creer_un_soin && deja_un_soin)
		{
			sceneDeJeu.Soin.update();
			sceneDeJeu.Soin.CheckPosx();
		}
		sceneDeJeu.update();
		//-----BOUGER VAISSEAU------//
		scene.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event) 
			{
				if(event.getCode() == KeyCode.UP)
				{
					sceneDeJeu.vaisseau.update(-1);
				}
				else if(event.getCode() == KeyCode.DOWN)
				{
					sceneDeJeu.vaisseau.update(1);
				}
			}
		});
	}
	///
	//-------------------//VAISSEAU
	public class Vaisseau
	{
		private double posY;
		private int vitesse;
		private int sante;
		private boolean lent;
		private boolean casse;
		private final ImageView Vaisseau = new ImageView("file:vaisseau1.png");
		
		public double getPosY(){return posY;}
		public void setPosY(double posY){this.posY = posY;}
		public int getVitesse(){return vitesse;}
		public void setVitesse(int vitesse){this.vitesse = vitesse;}
		public int getSante(){return sante;}
		public void setSante(int sante){this.sante = sante;}
		public boolean isLent(){return lent;}
		public void setLent(boolean lent){this.lent = lent;}
		public boolean isCasse(){return casse;}
		public void setCasse(boolean casse){this.casse = casse;}

		public Vaisseau()
		{
			Vaisseau.setRotate(90);
			Vaisseau.setLayoutX(0);
			Vaisseau.setLayoutY(300);
			this.posY = Vaisseau.getLayoutY();
			this.vitesse=10;
			this.sante=10;
			this.lent=false;
			this.casse=false;
			root.getChildren().add(Vaisseau);
		}
		
		public void Probleme(Asteroides aste)
		{
			if(aste.etat == 1 && !this.casse && !this.lent)//Lenteur
			{
				this.setVitesse(5);
				this.setSante(this.getSante()-1);
				lent=true;
			}
			else if(aste.etat == 2 && !this.casse && !this.lent)//Changement Direction
			{
				sceneDeJeu.Failure.setText("Failure = Votre moteur est casse !");
				this.setVitesse(-this.getVitesse());
				this.setSante(this.getSante()-1);
				casse=true;
			}
			else if(aste.etat == 0)
			{
				this.setSante(this.getSante()-1);
			}
		}
		public void CheckSante()
		{
			if(this.sante<=0)
			{
				Main.scoreModel.update(Main.scoreModel,"score");
				tm.stop();
				Main.gameManager.Timer();
			}
		}
		public void Reparation(int etat)
		{
			if(etat==0)
			{
				this.setSante(this.getSante()+1);
				sceneDeJeu.Change_Couleur(etat);
			}
			else if(etat==1 && lent)
			{
				this.setVitesse(10);
				lent=false;
				sceneDeJeu.Change_Couleur(etat);
			}
			else if(etat==2 && casse)
			{
				this.setVitesse(-this.getVitesse());
				casse=false;
				sceneDeJeu.Change_Couleur(etat);
			}
			sceneDeJeu.Soin.Reparation.setLayoutX(-50);
			deja_un_soin=false;
			creer_un_soin=false;
			compteur=0;
		}
		public void CheckCollisions(ArrayList<Asteroides> Tab)
		{
			if(Vaisseau.getBoundsInParent().intersects(sceneDeJeu.Soin.Reparation.getBoundsInParent()))///REPARATION AVEC TROIS OBJETS 
			{
				this.Reparation(sceneDeJeu.Soin.getEtat());
				
			}
			for(int i=0;i<sceneDeJeu.nombre_asteroides;i++)
			{
				if(Vaisseau.getBoundsInParent().intersects(Tab.get(i).Mon_Asteroide.getBoundsInParent()))
				{
					Tab.get(i).Relocate();
					this.Probleme(Tab.get(i));
				}
			}
		}
		public void update(int direction)
		{
			if(direction==1 && this.getPosY()<600)//DESCEND
			{
				this.setPosY(this.getPosY()+this.getVitesse());
			}
			else if(direction==-1 && this.getPosY()>0)//MONTE
			{
				this.setPosY(this.getPosY()-this.getVitesse());
			}
			Vaisseau.setLayoutY(this.posY);
		}
	}
	//-------------------//REPARATION
	public class Objet
	{
		private Rectangle Reparation = new Rectangle(20,20);
		private double posX;
		private double posY;
		private int vitesse;
		private Color color;
		private int rand = (int)((Math.random()*3)+1);
		private int etat;//0=SANTE  1=REPLENT 2=REPDIR
		
		public Objet()
		{
			this.vitesse=2;
			this.Relocate();
			this.EtatDeSoin();
			root.getChildren().add(Reparation);
		}
		public void EtatDeSoin()
		{
			switch(this.rand)
			{
				case 1:
					this.etat=0;
					this.color = Color.RED;
					Reparation.setFill(this.color);
					break;
				case 2:
					this.etat=1;
					this.color = Color.YELLOW;
					Reparation.setFill(this.color);
					break;
				case 3:
					this.etat=2;
					this.color = Color.ORANGE;
					Reparation.setFill(this.color);
					break;
			}
		}
		public void CheckPosx()
		{
			if(this.posX<-30)
			{
				sceneDeJeu.Soin.Reparation.setLayoutX(-50);
				deja_un_soin=false;
				creer_un_soin=false;
				compteur=0;
			}
		}
		public void update()
		{
			this.posX = this.posX - this.vitesse;
			Reparation.setLayoutX(this.posX);
		}
		public void Relocate()
		{
			this.posX=(int)((Math.random() * 1500)+1200);;
			this.posY=(int)((Math.random() * 600)+5);;
			this.Reparation.setLayoutX(this.posX);
			this.Reparation.setLayoutY(this.posY);
			this.EtatDeSoin();
		}
		public int getEtat()
		{
			return this.etat;
		}
	}
	//-------------------//ASTEROIDES
	public class Asteroides
	{
		private Circle Mon_Asteroide = new Circle();
		private double vitesse;
		private double posX;
		private double posY;
		private double radius; 
		private double etat;//0=rien 1=lenteur 2=changementdirection
		
		public Asteroides()
		{
			this.vitesse=1;
			this.posX=(int)((Math.random() * 1500)+1200);;
			this.posY=(int)((Math.random() * 600)+5);;
			this.radius = (int)((Math.random() * 10)+5);
			this.Relocate();
			this.etat=Etat_de_l_asteroide(this.etat);
			root.getChildren().add(Mon_Asteroide);
		}
		
		public void update()
		{
			this.posX = this.posX - this.vitesse;
			Mon_Asteroide.setLayoutX(this.posX);
		}
		public double Etat_de_l_asteroide(double etat)
		{
			int nb_alea = (int)((Math.random()*19)+1);
			if(nb_alea>13 && nb_alea<=15)
			{
				etat = 1;
			}
			else if(nb_alea>17 && nb_alea<=20)
			{
				etat = 2;
			}
			else
			{
				etat=0;
			}
			return etat;	
		}
		
		public void CheckPosx()
		{
			if(this.posX<-10)
			{
				this.Relocate();
			}
		}
		
		public void Relocate()
		{
			this.posX=(int)((Math.random() * 1500)+1200);;
			this.posY=(int)((Math.random() * 600)+5);;
			this.radius = (int)((Math.random() * 10)+5);
			this.Mon_Asteroide.setLayoutX(this.posX);
			this.Mon_Asteroide.setLayoutY(this.posY);
			this.Mon_Asteroide.setRadius(this.radius);
			this.Etat_de_l_asteroide(this.etat);
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
	}
}
