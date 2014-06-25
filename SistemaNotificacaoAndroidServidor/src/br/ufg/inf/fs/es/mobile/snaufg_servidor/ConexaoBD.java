package br.ufg.inf.fs.es.mobile.snaufg_servidor;

import java.sql.*;

public class ConexaoBD {

	private String nome_Tabela;

	Connection c = null;
	Statement stmt = null;

	public String getNomeTabela() {
		return nome_Tabela;
	}

	public void setNomeTabela(String nomeTabela) {
		this.nome_Tabela = nomeTabela;
	}

	public void criarConexao() {

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:snaufg.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public void criarTabela() {
		try {
			stmt = c.createStatement();
			String sql = "CREATE TABLE " + getNomeTabela() + " "
					+ "(ID INT PRIMARY KEY NOT NULL,"
					+ " API_KEY TEXT NOT NULL,"
					+ " ID_DISPOSITIVO_GCM TEXT NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void inserirDado(String apiKey, String idDispositivoGCM) {
		try {
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(ID_DISPOSITIVO_GCM) FROM " + getNomeTabela() + ";");

			int quantElementos = rs.getInt("ID");
			int id = quantElementos + 1; // Caso quantidade for 0, id será 1, e assim por diante
			System.out.println("quantidade: " + quantElementos);
			
			String sql = "INSERT INTO " + getNomeTabela()
					+ " (ID,API_KEY,ID_DISPOSITIVO_GCM) "
					+ "VALUES (" + id + ", "+ "'" + apiKey + "'" + ", " + "'"
					+ idDispositivoGCM + "'" + " );";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public String[] buscarIdDispositivo(){
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + getNomeTabela());
			String[] idDispositivoGCM = null;
			int count=0;
			while(rs.next()){
				idDispositivoGCM[count] = rs.getString("ID_DISPOSITIVO_GCM");
				count++;
			}
			rs.close();
			stmt.close();
			return idDispositivoGCM;
		} catch (SQLException e) {
			return null;
		}
	}

}
