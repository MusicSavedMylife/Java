package application.ViewModels;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import application.SceneLoader;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SceneJeu4Model implements Initializable
{
	private final Group root = new Group();
	private final Scene scene = new Scene(root, 600, 400, Color.RED);
	private int rate;
	private int difficulte;
	@FXML
	protected Text Txt;
	@FXML
	protected Circle C1 = new Circle(0,0,10);
	@FXML
	protected Circle C2 = new Circle(0,0,10);
	@FXML
	protected Circle C3 = new Circle(0,0,10);
	@FXML
	protected Circle C4 = new Circle(0,0,10);
	@FXML
	protected Circle C5 = new Circle(0,0,10);
	@FXML
	protected Circle C6 = new Circle(0,0,10);
	@FXML
	protected Circle C7 = new Circle(0,0,10);
	@FXML
	protected Circle C8 = new Circle(0,0,10);
	@FXML
	protected Circle C9 = new Circle(0,0,10);
	@FXML
	protected Circle C10 = new Circle(0,0,10);
	@FXML
	protected Rectangle R1 = new Rectangle(10,2,15,15);
	@FXML
	protected Rectangle R2 = new Rectangle(26,2,15,15);
	@FXML
	protected Rectangle R3 = new Rectangle(42,2,15,15);
	@FXML
	protected Rectangle R4 = new Rectangle(58,2,15,15);
	@FXML
	protected Rectangle R5 = new Rectangle(74,2,15,15);
	@FXML
	protected Rectangle R6 = new Rectangle(90,2,15,15);
	@FXML
	protected Rectangle R7 = new Rectangle(106,2,15,15);
	@FXML
	protected Rectangle R8 = new Rectangle(122,2,15,15);
	@FXML
	protected Rectangle R9 = new Rectangle(138,2,15,15);
	@FXML
	protected Rectangle R10 = new Rectangle(154,2,10,15);
	
	private ArrayList<Circle> Tab_Cercles = new ArrayList<Circle>();
	private ArrayList<Rectangle> Tab_Rec = new ArrayList<Rectangle>();
	private ArrayList<Color> tab_color = new ArrayList<Color>();
	
	private int nb_cercle = (int)((Math.random() * 8)+2);
	private int compteur=0;
	private boolean bon = false;
	
	
	public void LancementJeu4(Stage primaryStage)
	{
		if(Main.scoreModel.getDifficulte()==1)
		{
			this.difficulte=10;
		}
		else if(Main.scoreModel.getDifficulte()==2)
		{
			this.difficulte=5;
		}
		else if(Main.scoreModel.getDifficulte()==3)
		{
			this.difficulte=1;
		}
		Txt = new Text(50,50,"Rate ("+difficulte+") : "+rate);
		Tab_Cercles.add(C1);Tab_Cercles.add(C2);Tab_Cercles.add(C3);
		Tab_Cercles.add(C4);Tab_Cercles.add(C5);Tab_Cercles.add(C6);
		Tab_Cercles.add(C7);Tab_Cercles.add(C8);Tab_Cercles.add(C9);
		Tab_Cercles.add(C10);Tab_Rec.add(R1);Tab_Rec.add(R2);Tab_Rec.add(R3);
		Tab_Rec.add(R4);Tab_Rec.add(R5);Tab_Rec.add(R6);
		Tab_Rec.add(R7);Tab_Rec.add(R8);Tab_Rec.add(R9);
		Tab_Rec.add(R10);
		tab_color.add(Color.BEIGE);tab_color.add(Color.AQUA);tab_color.add(Color.YELLOW);
		tab_color.add(Color.DARKMAGENTA);tab_color.add(Color.LIGHTSALMON);tab_color.add(Color.ORANGE);
		tab_color.add(Color.SNOW);tab_color.add(Color.LIME);tab_color.add(Color.TOMATO);
		tab_color.add(Color.SLATEBLUE);
		
		for(int i = 0;i<10;i++)
		{
			Tab_Cercles.get(i).setVisible(false);
			Tab_Rec.get(i).setVisible(false);
		}
		for(int i=0;i<nb_cercle;i++)
		{
			Color color = tab_color.get((int)(Math.random() * 10));
			Tab_Cercles.get(i).setVisible(true);
			Tab_Cercles.get(i).setFill(color);
			Tab_Cercles.get(i).setLayoutX((int)(Math.random() * 590)+10);
			Tab_Cercles.get(i).setLayoutY((int)(Math.random() * 390)+10);
			Tab_Cercles.get(i).setRadius((int)(Math.random() * 17)+3);
			Tab_Rec.get(i).setVisible(true);
			Tab_Rec.get(i).setFill(color);
		}
		root.getChildren().add(Txt);
		root.getChildren().addAll(Tab_Cercles);
		root.getChildren().addAll(Tab_Rec);
		primaryStage.setScene(scene);
	
		scene.setOnMousePressed(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event) 
			{
				for(int i=0;i<nb_cercle;i++)
				{
					if(Tab_Cercles.get(i).isPressed() )
					{
						Color couleur_cercle = (Color)Tab_Cercles.get(i).getFill();
						Color couleur_rec = (Color)Tab_Rec.get(compteur).getFill();
						if(couleur_cercle==couleur_rec)
						{
							Tab_Cercles.get(i).setVisible(false);
							Tab_Cercles.get(i).setDisable(true);
							compteur++;
							bon = true;
						}
					}
				}
				if(!bon)
				{
					rate++;
					Txt.setText("Rate ("+difficulte+") : "+rate);
				}
				bon=false;
				
				if(compteur==nb_cercle)
				{
					Main.scoreModel.update(Main.scoreModel,"score");
					Main.gameManager.Timer();
				}
				else if(rate==difficulte)
				{
					try {
						Main.sceneLoader.switchTo(SceneLoader.SCENE_LOOSE);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		 });
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		
	}
	
	public int getCompteur() 
	{
		return compteur;
	}

	public void setCompteur(int compteur) 
	{
		this.compteur = compteur;
	}	

}
