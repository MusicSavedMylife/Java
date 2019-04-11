package application.ViewModels;

import application.Information.Information;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Connection 
{
	@FXML
	protected TextField Nom=new TextField(),Email=new TextField();
	@FXML
	protected PasswordField Password=new PasswordField();
	@FXML
	protected CheckBox CheckAdmin=new CheckBox();

	private Information Info = new Information();
	
	@FXML
	public void Registration()
	{
		//System.out.println("Nom = "+Nom.getText()+" Email = "+Email.getText()+"ADMIN = "+CheckAdmin.isAllowIndeterminate());
		if(!Nom.getText().contains(" ") && !Email.getText().contains(" ") &&!Password.getText().contains(" "))
		{
			Info.CreationTable();
			Info.InsertInfo(new Information(1,Nom.getText(),Email.getText(),Password.getText(),CheckAdmin.isSelected()));
		}
		System.out.println(Information.listAll().toString());
		Info.Update(Info, "Albert");
		System.out.println(Information.listAll().toString());
	}
}
