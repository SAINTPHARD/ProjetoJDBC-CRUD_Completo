package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program05_DeleteMultiple {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement st = null;
		
		// 1. Bloco try-catch-finally para tratar a conexão e possíveis erros
		try {
			
			// 2. Estabelecendo a conexão com o banco de dados
			conn = DB.getConnection();
			
			System.out.println("Pratica JDBC - Delete com Condicionais (OR / AND)");
			System.out.println("=================================================");
			
			// ----------------------------------------------------------
			// 3. EXEMPLO COM 'OR' (Apagar múltiplos registros)
			// Tradução: "Apague se o ID for X OU se o ID for Y"
			// ----------------------------------------------------------
			st = conn.prepareStatement(
					"DELETE FROM department "
					+ "WHERE "
					+ "Id = ? OR Id = ?"); // lógica 'OR' para múltiplos IDs

			// Substituindo os valores:
			// Vamos apagar o ID 16 e o ID 20 (ajuste conforme seu banco)
			st.setInt(1, 16); // Primeiro '?'
			st.setInt(2, 20); // Segundo '?'
			
			// ----------------------------------------------------------
			// 4. EXEMPLO COM 'AND' (Trava de segurança)
			// Se quiser testar este, comente o código acima e descomente este.
			// ----------------------------------------------------------
			
			/**
			st = conn.prepareStatement(
					"DELETE FROM department "
					+ "WHERE "
					+ "Id = ? AND Name = ?"); // Só apaga se ID e Nome baterem
			
			st.setInt(1, 17);				 // ID do departamento
			st.setString(2, "D1");			 // Se o ID 15 não chamar "D1", nada acontece!
			*/

			// Executando
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Pronto! Registros apagados: " + rowsAffected);
			
			
		}
		catch (SQLException e) {
			// Captura erro de integridade (se tentar apagar dept com vendedores)
			System.out.println("Erro: Não foi possível apagar. Verifique se há vendedores vinculados.");
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}