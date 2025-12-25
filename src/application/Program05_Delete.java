package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbException;

public class Program05_Delete {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			System.out.println("Pratica JDBC - Deletar Dados (DELETE)");
			System.out.println("=====================================");
			
			// 1. Preparando o comando SQL
			// "Delete da tabela department ONDE o Id for igual a ?"
			st = conn.prepareStatement(
					"DELETE FROM department "
					+ "WHERE "
					+ "Id = ?");

			// ----------------------------------------------------------
			// TESTE 1: Substitua o ? pelo ID do departamento que deseja apagar(ex: 15) 1 é o ID do departamento "D1"
			// Olhe no seu Workbench qual ID tem os departamentos "D1".
			// ----------------------------------------------------------
			st.setInt(1, 13); 
			
			// 2. Executando
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Pronto! Linhas apagadas: " + rowsAffected);
		}
		catch (SQLException e) {
			// Se tentarmos apagar um departamento com vendedores, cai aqui (Integrity Constraint)
			System.out.println("Erro ao apagar departamento: " + e.getMessage());
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}