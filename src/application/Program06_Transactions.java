package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement; // <--- AQUI ESTAVA O ERRO (Era java.beans)

import db.DB;
import db.DbException;

public class Program06_Transactions {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;
		
		try {
			conn = DB.getConnection();
			
			// 1. FUNDAMENTAL: Desligar o salvamento automático
			conn.setAutoCommit(false);
			
			System.out.println("Pratica JDBC - Transações");
			System.out.println("=====================================");
			
			st = conn.createStatement();
			
			// --- Primeira operação de update ---
			int rows1 = st.executeUpdate(""
					+ "UPDATE seller "
					+ "SET BaseSalary = 2090 "
					+ "WHERE DepartmentId = 1");
			
			System.out.println("Passo 1: First operation update completed. Rows: "
					+ "" + rows1);
			
			
			// --- Simula um erro para testar o rollback ---
			int x = 1;
			if (x < 2) {
				throw new SQLException("Simulated error: Falha de proposito afim testar O Rollback!");
			}
			
			
			// --- Segunda operação de update ---
			// O código nunca chegará aqui por causa do erro acima
			int rows2 = st.executeUpdate(""
					+ "UPDATE seller "
					+ "SET BaseSalary = 3090 "
					+ "WHERE DepartmentId = 2");
			System.out.println("Passo 2: Second operation update completed. Rows: "
					+ "" + rows2);
			
			// --- Terceira operação de update ---
			int rows3 = st.executeUpdate(""
					+ "UPDATE seller "
					+ "SET BaseSalary = 4090 "
					+ "WHERE DepartmentId = 3");
			System.out.println("Passo 3: Third operation update completed. Rows: "
					+ "" + rows3);
			
			// 2. Commit (Só grava se chegar até aqui sem erros)
			conn.commit();
			System.out.println("Sucesso! Transação Completo.");

		}
		catch (SQLException e) {
			// 3. Rollback (Desfaz tudo se der erro)
			try {
				conn.rollback();
				// Mensagem amigável para confirmar que o Rollback protegeu os dados
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Erro ao tentar voltar atrás (Rollback): " + e1.getMessage());
			}
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}