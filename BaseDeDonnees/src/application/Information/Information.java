package application.Information;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import application.DataBase;

public class Information 
{
	private Integer id;
	private String nom;
	private String email;
	private String password;
	private boolean admin;
	
	public Integer getId(){return id;}
	public void setId(Integer id){this.id = id;}
	public String getNom(){return nom;}
	public void setNom(String nom){this.nom = nom;}
	public String getEmail(){return email;}
	public void setEmail(String email){this.email = email;}
	public String getPassword(){return password;}
	public void setPassword(String password){this.password = password;}
	public boolean isAdmin(){return admin;}
	public void setAdmin(boolean admin){this.admin = admin;}

	public Information()
	{
		
	}
	public Information(int id, String nom, String email, String password, boolean admin)
	{
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.password = password;
		this.setAdmin(admin);
	}
	public void CreationTable()
	{
		String sql = "CREATE TABLE IF NOT EXISTS information("
					+"id integer PRIMARY KEY,"
					+"nom string,"
					+"email string,"
					+"password string,"
					+"admin boolean"
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
	
	public void InsertInfo(Information Info)
	{
		String sql = "INSERT INTO information(nom, email, password, admin) VALUES(?, ?, ?, ?);";
		try
		{
			PreparedStatement ps = DataBase.getInstance().prepareStatement(sql);
			ps.setString(1,Info.getNom());
			ps.setString(2, Info.getEmail());
			ps.setString(3, Info.getPassword());
			ps.setBoolean(4, Info.isAdmin());
			ps.executeUpdate();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public Information Update(Information Info, String nom)
	{
		String sql = "SELECT * FROM information WHERE email = ? ";
		String sql1 = "UPDATE information SET nom = ? " ;
		try
		{
			PreparedStatement ps = DataBase.getInstance().prepareStatement(sql);
			PreparedStatement ps1 = DataBase.getInstance().prepareStatement(sql1);
			ps.setString(1, Info.getEmail());
			ps1.setString(1, nom);
			ps.executeQuery();
			ps1.executeUpdate();
			this.setNom(nom);
		}catch(Exception e)
		{
			e.printStackTrace();
		}	
		return Info;
	}
	
	public static ArrayList<Information> listAll()
	{
		String sql = "SELECT * FROM information;";
		ArrayList<Information> list = new ArrayList<>();
		try
		{
			Statement s = DataBase.getInstance().createStatement();
			ResultSet r = s.executeQuery(sql);
			while(r.next())
			{
				list.add(new Information(r.getInt("id"),r.getString("nom"),r.getString("email"),r.getString("password"),r.getBoolean("admin")));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
 	}

	public String toString() 
	{
		return "id=" + id + ", nom =" + nom + ", email =" + email + ",password = "+ password +", admin = "+admin+"\n";
	}
	
}
