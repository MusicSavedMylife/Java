package application.ViewModels;

import application.Main;
import application.SceneLoader;
import application.Models.ScoreModel;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SceneConnection 
{
	@FXML
	protected Text Txt = new Text();
	@FXML
	protected TextField TxtField1;
	@FXML
	protected PasswordField TxtField2;
	
	@FXML
	protected void connect()
	{
		if(!TxtField1.getText().equals("") && !TxtField2.getText().equals(""))
		{	
			if(Main.scoreModel.login(new ScoreModel(1,0,1,TxtField1.getText(),TxtField2.getText())))
			{
				try
				{
					Main.sceneLoader.switchTo(SceneLoader.SCENE_MENU);
				}
				catch(Exception e) 
				{
					
				}
			}
			else
			{
				Txt.setText("Merci de vous inscrire !");
			}
		}
	}
	@FXML
	protected void inscription()
	{
		if(!TxtField1.getText().equals("") && !TxtField2.getText().equals(""))
		{	
			if(!Main.scoreModel.login(new ScoreModel(1,0,1,TxtField1.getText(),TxtField2.getText())))
			{
				Main.scoreModel.insert(new ScoreModel(1,0,1,TxtField1.getText(),TxtField2.getText()));
				TxtField1.setText("");
				TxtField2.setText("");
				Txt.setText("Vous etes inscrit !");
			}
		}
	}
}
