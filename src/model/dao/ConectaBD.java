package model.dao;
import java.sql.*;
public class ConectaBD {

	  public static Connection conexao() {
		  Connection conecta = null;
	  try {
	      String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	      String dbUrl = "jdbc:mysql://localhost:3306/Senac";
	      String dbUser = "root";
	      String dbPassword = "suceso3jlhu";

	      // Load the MySQL JDBC driver
	      Class.forName(jdbcDriver);

	      // Establish the conecta
	      conecta = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

	      if (conecta != null) {
	          System.out.println("Conexão bem-sucedida com o banco de dados MySQL!");
	      }
	  } catch (Exception e) {
	      System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
	      e.printStackTrace();
	  }
	  return conecta;
	}


}
