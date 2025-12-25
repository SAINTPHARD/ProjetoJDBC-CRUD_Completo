package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import db.DB;

public class Program02_Select {

	public static void main(String[] args) {
		
		// Variaveis de conexao do JDBC
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		// Bloco try-catch-finally para tratar excecoes
		try {
			// 1.Estabelecer a conexao com o banco de dados
			conn = DB.getConnection();
			
			// 2.Criar o statement (Instanciar o objeto Statement)
			st = conn.createStatement();
			
			// 3.Executar a query e obter o resultado no ResultSet
			rs = st.executeQuery("SELECT * FROM department");
			
			System.out.println("List of Departments:\n");
			// 4.Percorrer o ResultSet enquanto houver uma proxima linha
			while (rs.next()) {
				int id = rs.getInt("Id");
				String name = rs.getString("Name");
				// System.out.print("Department: ");
				System.out.println(id + " - " + name);
			}
		}
		catch (Exception e) {  // Tratar possiveis erros
			e.printStackTrace();
		}
		finally {
			// Fechar os recursos
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}