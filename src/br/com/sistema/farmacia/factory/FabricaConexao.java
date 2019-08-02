package br.com.sistema.farmacia.factory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class FabricaConexao {
	private static final String DRIVE = "com.mysql.cj.jdbc.Driver";
	private static final String USUARIO = "root";
	private static final String SENHA = "mariaclara";
	private static final String URL = "jdbc:mysql://localhost:3306/farmacia_bd?useTimezone=true&serverTimezone=UTC";
	private static Connection conexao;
        
	public static Connection getConection() {
		try {
			 Class.forName(DRIVE);
			conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
			System.out.println("conectado com sucesso!!");
		} catch (ClassNotFoundException | SQLException e) {						
			System.out.println("nao conectou"+e.getMessage());
			throw new RuntimeException("Erro na conexao", e);
		} 
                return conexao;
	}
	public static void closeConnection(Connection conn) {
		if(conn != null) {
			
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}
	
	public static void closeConnection(Connection conn, PreparedStatement stmt) {
		closeConnection(conn);
		if(stmt != null) {
			
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}
	
	public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs) {
		closeConnection(conn, stmt);
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}

}
