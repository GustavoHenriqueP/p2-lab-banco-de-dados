package control;

import java.sql.*;

/**
 * Realiza a conex�o e prepara o Banco de dados para ser manipulado
 * @author Grupo 1
 */
public class BD {
	
	// realiza a conex�o ao BD usando o Driver
	public Connection con = null;
	
	// realiza a execu�a� de instru��es SQL
	public PreparedStatement st = null;
	
	// manipula uma tabela em mem�ria
	public ResultSet rs = null;
	
	private final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String DATABASENAME = "bdfinanceiro";
	private final String URL = "jdbc:mysql://localhost:3306/"+DATABASENAME;
	private final String LOGIN = "root";
	private final String PASSWORD = "";
	
	/**
	 * Realiza a conex�o ao banco de dados
	 * @return - true em caso de sucesso ou false caso contr�rio
	 */
	public boolean getConnection()
	{
		try
		{
			Class.forName(DRIVER); //carregando o DRIVER
			//System.out.println("Driver carregado com sucesso!");
			con = DriverManager.getConnection(URL,LOGIN,PASSWORD);
			System.out.println("Conectado com sucesso!");
			return true;
		}
		catch(ClassNotFoundException erro)
		{
			System.out.println("Driver n�o encontrado!");
			return false;
		}
		catch(SQLException erro)
		{
			System.out.println("Falha na conex�o " + erro);
			return false;
		}
	}
	
	/**
	 * Encerra a conex�o com o banco de dados, fechando todos os objetos utilizados
	 */
	public void close()
	{
		try
		{
			if(rs!=null) rs.close();
			if(st!=null) st.close();
			if(con!=null)
				{
					con.close();
					System.out.println("Desconectou..");
				}
		}
		catch(SQLException erro) {}
	}
	
	public static void main(String[] args) {
		BD bd = new BD();
		bd.getConnection();
		//executar uma instru��o
		bd.close();
	}
}