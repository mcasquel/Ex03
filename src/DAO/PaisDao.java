package DAO;

import Model.Pais;
import Conexao.ConnectionFactory;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaisDao {
	
	public void IncluirPais(Pais pais){
		String sqlInsert = "INSERT INTO paises (id, nome_pais, area_pais, pop_pais) VALUES(?, ?, ?, ?)";
		//Iniciar Conexao com o banco
		//Try para verificar se nao ocorre exeptions
		try {
			
			Connection conn = ConnectionFactory.realizarConexao();
			PreparedStatement stm = conn.prepareStatement(sqlInsert);
			stm.setInt(1, pais.getId());
			stm.setString(2,pais.getNome());
			stm.setDouble(3,pais.getArea());
			stm.setLong(4,pais.getPopulacao());
			
			stm.execute();
			
		}catch(SQLException e) {
			//Caso ocorram erros na parte de conexao
			System.out.println(e);
		}
		
	}
	
	
	public void AtualizarPais(Pais pais, String checkAlter) {
		
		String sqlAlter = "";
		PreparedStatement stm = null;
		try {
			Connection conn = ConnectionFactory.realizarConexao();
		
		if (checkAlter.equals("Populacao")) {
			 sqlAlter = "UPDATE paises SET pop_pais = ?  WHERE id=?";
			 stm = conn.prepareStatement(sqlAlter);
			 stm.setLong(1, pais.getPopulacao());
			 stm.setInt(2, pais.getId());
			 stm.execute();
		}
		
		else if (checkAlter.equals("Area")) {
			 sqlAlter = "UPDATE paises SET area_pais = ?  WHERE id=?";
			 stm = conn.prepareStatement(sqlAlter);
			 stm.setDouble(1, pais.getArea());
			 stm.setInt(2, pais.getId());
			 stm.execute();
		}
		
		else if(checkAlter.equals("Nome")) {
			sqlAlter = "UPDATE paises SET nome_pais = ?  WHERE id=?";
			stm = conn.prepareStatement(sqlAlter);
			stm.setString(1, pais.getNome());
			stm.setInt(2, pais.getId());
			stm.execute();
		}
		
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	}
	
	public void DeletarPais(Pais pais) {
		String sqlDelete = "DELETE from paises WHERE id = ?";
		try {
			Connection conn = ConnectionFactory.realizarConexao();
			PreparedStatement stm = conn.prepareStatement(sqlDelete);
			stm.setInt(1, pais.getId());
			stm.execute();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	}
	
	public void CarregarPais(Pais pais) {
		String sqlSelect = "Select nome_pais, area_pais, pop_pais FROM paises WHERE id = ?";
		try {
			Connection conn = ConnectionFactory.realizarConexao();
			PreparedStatement stm = conn.prepareStatement(sqlSelect);
			stm.setInt(1, pais.getId());
			
			//responsavel por retornar o valor que a query tras
			ResultSet rs = stm.executeQuery();
			
			try {
				if(rs.next()) {
					// o rs e necessario para poder retornar o resultado da query
					pais.setNome(rs.getString("nome_pais"));
					pais.setArea(rs.getDouble("area_pais"));
					pais.setPopulacao(rs.getLong("pop_pais"));
				}else {
					pais.setId(-1);
					pais.setNome(null);
					pais.setPopulacao(0);
					pais.setArea(0);
				}
			}catch(Exception e) {
				System.out.println(e);
			}
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		

	}
	
	public void getMenorArea(Pais pais) {
		try {
		String sqlQuery = "Select * from paises where area_pais = (Select Min(area_pais) from paises)";
		Connection conn  = ConnectionFactory.realizarConexao();
		PreparedStatement stm = conn.prepareStatement(sqlQuery);
		
		//Pegando o retorno
		ResultSet rs = stm.executeQuery();
		
		if(rs.next()) {
			pais.setId(rs.getInt("id"));
			pais.setNome(rs.getString("nome_pais"));
			pais.setArea(rs.getDouble("area_pais"));
			pais.setPopulacao(rs.getLong("pop_pais"));
		}
		
		}catch(SQLException e) {
			
			System.out.println(e);
		}
		
	}
	
	public void getHabitantes(Pais pais) {
		try {
		String sqlQuery =  "Select * from paises where pop_pais = (Select Max(pop_pais) from paises)";
		Connection conn  = ConnectionFactory.realizarConexao();
		PreparedStatement stm = conn.prepareStatement(sqlQuery);
		
		//Pegando o retorno
		ResultSet rs = stm.executeQuery();
		
		if(rs.next()) {
			pais.setId(rs.getInt("id"));
			pais.setNome(rs.getString("nome_pais"));
			pais.setArea(rs.getDouble("area_pais"));
			pais.setPopulacao(rs.getLong("pop_pais"));
		}
		
		}catch(SQLException e) {
			
			System.out.println(e);
		}
		
	}
	
	
	public String[] vetorPaises() {
		
		String[] vet = new String[3];
		int cont = 0;
		
		try {
		String sqlQuery = "Select nome_pais from paises order by nome_pais";
		Connection conn = ConnectionFactory.realizarConexao();
		
		PreparedStatement stm = conn.prepareStatement(sqlQuery);
		ResultSet rs = stm.executeQuery();
		
		while(rs.next() && cont < 3) {
			vet[cont] = rs.getString("nome_pais");
			cont++;
		}
		
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return vet;
	
	}
}