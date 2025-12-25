package application;

import java.sql.Connection;

import db.DB;

public class Program01_TestConexao {

	public static void main(String[] args) {

		System.out.println("1. Tentando conectar ao banco...");

		// Abre a conexão
		Connection conn = DB.getConnection();

		System.out.println("2. Conexão realizada com SUCESSO!");

		// Fecha a conexão
		DB.closeConnection();

		System.out.println("3. Conexão fechada. Fim do programa.");
	}
}