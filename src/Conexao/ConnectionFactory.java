package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	//Verificar se dessa forma funciona se nao adicionar o Trows
	public static Connection realizarConexao(){
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/pais?user=root&password=@mcasquel123");
		}catch(SQLException e) {
				System.out.println(e);
				}
		return null;
			}
}