package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import db.DB;

public class Program03_Insert {

	public static void main(String[] args) {

		// Demo - Inserir Dados
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Pratica JDBC - Inserir Dados");
		System.out.println("=================================");
		
		Connection conn = null;
		PreparedStatement st = null;
		
		// 1. Tratamento de exceções
		try {
			conn = DB.getConnection(); 
			
			// 2. Instanciando o PreparedStatement que é uma instrução SQL pré-compilada
			st = conn.prepareStatement( 									// Preparando a instrução SQL
					"INSERT INTO seller "									// Nome da tabela
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) " // Nome das colunas
					+ "VALUES "												 
					+ "(?, ?, ?, ?, ?)",									// Valores a serem inseridos, colunas
					java.sql.Statement.RETURN_GENERATED_KEYS);			 	// Retornar as chaves geradas(IDs)
		
			/** 3. Substituir os '?' pelos valores reais.
			 *  Atenção: No JDBC, o índice começa em 1, não em 0!
			 *  1 = Name, 2 = Email, 3 = BirthDate, 4 = BaseSalary, 5 = DepartmentId
			 */
			
			st.setString(1, "Robedson SaintPhard");									// Definindo o valor do primeiro parâmetro
			st.setString(2, "robedson@gmail.com");							
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
			st.setDouble(4, 3000.0);
			st.setInt(5, 3);														// DepartmentId
			
			
			// 4. Executar o comando
			int rowsAffected = st.executeUpdate();
			System.out.println("Done! Rows affected: " + rowsAffected);
			
			// 5. Verificar se funcionou e pega o novo ID gerado
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);									// 1 = posição do ID gerado
					System.out.println("Generated ID: " + id);
				}
				DB.closeResultSet(rs);
			} else {
				System.out.println("No rows affected!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}