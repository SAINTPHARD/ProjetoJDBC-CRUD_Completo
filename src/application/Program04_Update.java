package application;

import java.sql.Connection;
import java.sql.PreparedStatement;

import db.DB;

public class Program04_Update {

	public static void main(String[] args) {

		// Demo III - Atualizar dados na tabela (UPDATE)
		
		System.out.println("Pratica JDBC - Atualizar Dados (UPDATE)");
		System.out.println("=================================");
		
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"UPDATE seller "					// Nome da tabela a ser atualizada
					+ "SET BaseSalary = BaseSalary + ? " // Set=> O salário base será atualizado somando o valor passado tinha
					+ "WHERE "							// where(onde) - condição para atualização
					+ "(DepartmentId = ?)");		   	// Condição: atualizar apenas os vendedores do departamento 2 (Sales) que ganham menos de 3000.00
				
				// Substituindo os '?' pelos valores reiais
				// Atualizando o salário base dos vendedores do departamento 2 (Sales) que ganham menos de 3000.00
				st.setDouble(1, 3000.00);	// Primeiro '?' - valor a ser somado ao salário base
				st.setInt(2, 2); 			// Segundo '?' - valor do DepartmentId (2 = Sales)
				
				// Executando a atualização
				int rowsAffected = st.executeUpdate();
				System.out.println("Done! Rows affected: " + rowsAffected);
				System.out.println("=================================");
				System.out.println("Fim da Pratica JDBC - Atualizar Dados (UPDATE)");
	
	} catch (Exception e) {								// Tratamento de exceção
			e.printStackTrace();						// Imprime o rastreamento da pilha de exceção
		} 
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}