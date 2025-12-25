package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program03_InsertMultiplos {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.println("Pratica JDBC - Inserir Dados (Individual e Batch)");
		System.out.println("================================================");

		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();

			// ==================================================================
			// PARTE 1: INSERINDO UM ÚNICO VENDEDOR (SELLER)
			// ==================================================================

			System.out.println("\n--- 1. Inserindo Vendedor ---");

			st = conn.prepareStatement("INSERT INTO seller " + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES " + "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, "Robedson SaintPhard");
			// DICA: Se rodar 2x, mude o email para não dar erro de duplicidade
			st.setString(2, "robedson.novo@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
			st.setDouble(4, 3000.0);
			st.setInt(5, 3);

			int rowsAffected = st.executeUpdate(); // Executa inserção individual

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Sucesso! Novo ID do Vendedor: " + id);
				}
				DB.closeResultSet(rs);
			}

			// Fechar o statement antes de reutilizá-lo
			st.close();

			// ==================================================================
			// PARTE 2: INSERINDO MÚLTIPLOS DEPARTAMENTOS (BATCH)
			// ==================================================================

			System.out.println("\n--- 2. Inserindo Departamentos em Lote (Batch) ---");

			// Preparamos o SQL genérico com '?'
			st = conn.prepareStatement(""
					+ "INSERT INTO department "
					+ "(Name) VALUES "
					+ "(?)", 
					Statement.RETURN_GENERATED_KEYS);

			// Adicionando D1
			st.setString(1, "D1");
			st.addBatch(); // Adiciona ao carrinho

			// Adicionando D2
			st.setString(1, "D2");
			st.addBatch();

			// Adicionando D3
			st.setString(1, "D3");
			st.addBatch();

			// Adicionando D4
			st.setString(1, "D4");
			st.addBatch();

			// EXECUTA TUDO DE UMA VEZ
			// O executeBatch retorna um array de int (um resultado para cada comando)
			int[] rows = st.executeBatch();

			System.out.println("Comando em lote executado! Total inserido: " + rows.length);

			// Recuperando os IDs gerados para todos os itens do lote
			ResultSet rs = st.getGeneratedKeys();
			while (rs.next()) {
				// Vai imprimir um ID para cada departamento criado (D1, D2, D3, D4)
				System.out.println("Novo ID de Departamento gerado: " + rs.getInt(1));
			}
			DB.closeResultSet(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			// Fecha tudo na ordem inversa
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}