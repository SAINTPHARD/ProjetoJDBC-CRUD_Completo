package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	// 1. Método auxiliar para carregar as propriedades do arquivo db.properties
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) { // Abre o arquivo
			Properties props = new Properties(); // Cria o objeto Properties
			props.load(fs); 				// Carrega os dados do arquivo	
			return props; 					// Retorna os dados carregados
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	// 2. método para obter a conexão com o banco de dados
	private static Connection conn = null;
	public static Connection getConnection() {
		
		if (conn == null) {												// Verifica se a conexão já foi criada
			try {
				Properties props = loadProperties();					// Carrega as propriedades do arquivo
				String url = props.getProperty("dburl");				// Cria a conexão com o banco de dados	
				conn = java.sql.DriverManager.getConnection(url, props); 	// Atribui a conexão à variável estátic
			} catch (java.sql.SQLException e) {								// Trata possíveis erros de SQL
				throw new DbException(e.getMessage());						// Lança uma exceção personalizada
			}
		}
		return conn;	
	}
		// 3. método para fechar a conexão com o banco de dados
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (java.sql.SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	// 4. métodos para fechar Statement e ResultSet
	public static void closeResultSet(ResultSet rs) {
		// TODO Auto-generated method stub
		if (rs != null) {
			try {
				rs.close();
			} catch (java.sql.SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	// 5. método para fechar Statement
	public static void closeStatement(Statement st) {
		// TODO Auto-generated method stub
		if (st != null) {
			try {
				st.close();
			} catch (java.sql.SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
