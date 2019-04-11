package application.Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import application.DataBase;
import application.Main;

public class ScoreModel
{
	private int score;
	private int id;
	private int difficulte;
	private String pseudo;
	private String password;
	
	public ScoreModel()
	{
		
	}
	public ScoreModel(int id, int score, int difficulte, String pseudo, String password)
	{
		this.id = id;
		this.score = score;
		this.difficulte = difficulte;
		this.pseudo = pseudo;
		this.password = password;
	}
	public int getScore() 
	{
		return score;
	}
	public void setScore(int score) 
	{
		this.score = score;
	}
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public int getDifficulte() 
	{
		return difficulte;
	}
	public void setDifficulte(int difficulte)
	{
		this.difficulte = difficulte;
	}
	public String getPseudo() 
	{
		return pseudo;
	}
	public void setPseudo(String pseudo) 
	{
		this.pseudo = pseudo;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	public void createTable()
	{
		String sql = "CREATE TABLE IF NOT EXISTS scores(" 
					 +" id integer PRIMARY KEY,"
					 +" score integer,"
					 +" difficulte integer,"
					 +" pseudo string,"
					 +" password string"
					 +");";
		try
		{
			Statement s = DataBase.getInstance().createStatement();
			s.execute(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void insert(ScoreModel sm)
	{
		String sql = "INSERT INTO scores(score,difficulte,pseudo,password) VALUES(?, ?, ?, ?);";
		try
		{
			PreparedStatement ps = DataBase.getInstance().prepareStatement(sql);
			ps.setInt(1, sm.getScore());
			ps.setInt(2, sm.getDifficulte());
			ps.setString(3, sm.getPseudo());
			ps.setString(4, sm.getPassword());
			ps.executeUpdate();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void update(ScoreModel sm, String choix)
	{
		if(choix.equals("score"))
		{
			String sql = "SELECT * FROM scores WHERE pseudo = ? ";
			String sql1 = "UPDATE scores SET score = score + 1 " ;
			try
			{
				PreparedStatement ps = DataBase.getInstance().prepareStatement(sql);
				PreparedStatement ps1 = DataBase.getInstance().prepareStatement(sql1);
				ps.setString(1, sm.getPseudo());
				ps1.executeUpdate();
				ResultSet r = ps.executeQuery();
				Main.scoreModel.setScore(r.getInt("score"));
	
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(choix.equals("difficulte"))
		{
			String sql1 = "UPDATE scores SET difficulte = ? WHERE pseudo = ?" ;
			try
			{
				PreparedStatement ps1 = DataBase.getInstance().prepareStatement(sql1);
				ps1.setInt(1, sm.getDifficulte());
				ps1.setString(2, sm.getPseudo());
				ps1.executeUpdate();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public static ArrayList<ScoreModel> listAll()
	{
		String sql = "SELECT * FROM scores;";
		ArrayList<ScoreModel> list = new ArrayList<>();
		try
		{
			Statement s = DataBase.getInstance().createStatement();
			ResultSet r = s.executeQuery(sql);
			while(r.next())
			{
				list.add(new ScoreModel(r.getInt("id"),r.getInt("score"),r.getInt("difficulte"),r.getString("pseudo"),r.getString("password")));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
 	}

	public boolean login(ScoreModel sm)
	{
		boolean exist = false;
		String sql = "SELECT * FROM scores WHERE pseudo = ? AND password = ?";
		try
		{
			PreparedStatement ps = DataBase.getInstance().prepareStatement(sql);
			ps.setString(1, sm.getPseudo());
			ps.setString(2, sm.getPassword());
			ResultSet r = ps.executeQuery();
			Main.scoreModel.setPseudo(r.getString("pseudo"));
			Main.scoreModel.setScore(r.getInt("score"));
			Main.scoreModel.setDifficulte(r.getInt("difficulte"));

            if(!r.next())
            {
            	
            }
            else
            {
                exist=true;
            }
		}catch(Exception e)
		{
			//e.printStackTrace();
		}
		return exist;
 	}
	
	@Override
	public String toString() 
	{
		return "ScoreModel [id=" + id + ", score =" + score + ", diificulte =" + difficulte + ",pseudo = "+pseudo+", password = "+password+"]";
	}
	
}
