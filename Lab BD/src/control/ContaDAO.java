package control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Conta;

public class ContaDAO {

	private BD bd;
	private String men, sql;
	
	public ContaDAO() {
		bd = new BD();
	}

	public String gravar(Conta conta)
	{
		sql = "CALL salvar_conta(?, ?, ?, ?)";
		bd.getConnection();
		try
		{
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1, conta.getNumero());
			bd.st.setDouble(2, conta.getAgencia());
			bd.st.setLong(3, conta.getCpf());
			bd.st.setDouble(4, conta.getSaldo());
			bd.st.executeUpdate();
			men = "Conta inserido com sucesso!";
		}
		catch(SQLException erro)
		{
			men = "Falha: "+ erro;
		}
		finally
		{
			bd.close();
		}
		return men;
	}
	
	public String excluir(int numero)
	{
		sql = "delete from conta where numero = ?";
		bd.getConnection();
		try
		{
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1, numero);
			int n = bd.st.executeUpdate();
			if(n == 1)
			{
				men = "Conta excluído com sucesso!";
			}
			else
			{
				men = "Conta não encontrado!";
			}
		}
		catch(SQLException erro)
		{
			men = "Erro" + erro;
		}
		finally
		{
			bd.close();
		}
		return men;
	}
	
	public List<Conta> listar(String sql)
	{
		List<Conta> lista = new ArrayList<Conta>();
		bd.getConnection();
		try
		{
			bd.st = bd.con.prepareStatement(sql);
			bd.rs = bd.st.executeQuery();
			while(bd.rs.next()) //enquanto existirem registros
			{
				Conta c = new Conta();
				c.setNumero((bd.rs.getInt("numero")));;;
				c.setAgencia((bd.rs.getInt("agencia")));;;
				c.setCpf((bd.rs.getLong("cpf")));;
				c.setSaldo((bd.rs.getDouble("saldo")));;
				lista.add(c);
			}
		}
		catch(SQLException erro)
		{
			lista = null;
		}
		finally
		{
			bd.close();
		}
		return lista;
	}
}