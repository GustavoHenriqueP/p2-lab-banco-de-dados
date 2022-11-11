package control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Deposito;

public class DepositoDAO {

	private BD bd;
	private String men, sql;
	
	public DepositoDAO() {
		bd = new BD();
	}

	public String gravar(Deposito deposito)
	{
		sql = "CALL salvar_deposito(?, ?, ?)";
		bd.getConnection();
		try
		{
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1, deposito.getId());
			bd.st.setDouble(2, deposito.getValor());
			bd.st.setInt(3, deposito.getNrconta());
			bd.st.executeUpdate();
			men = "Saque inserido com sucesso!";
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
	
	public String excluir(int id)
	{
		sql = "delete from deposito where id = ?";
		bd.getConnection();
		try
		{
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1, id);
			int n = bd.st.executeUpdate();
			if(n == 1)
			{
				men = "Deposito exclu�do com sucesso!";
			}
			else
			{
				men = "Deposito n�o encontrado!";
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
	
	public List<Deposito> listar(String sql)
	{
		List<Deposito> lista = new ArrayList<Deposito>();
		bd.getConnection();
		try
		{
			bd.st = bd.con.prepareStatement(sql);
			bd.rs = bd.st.executeQuery();
			while(bd.rs.next()) //enquanto existirem registros
			{
				Deposito d = new Deposito();
				d.setId((bd.rs.getInt("id")));;
				d.setValor((bd.rs.getDouble("valor")));;
				d.setNrconta((bd.rs.getInt("nrconta")));;
				lista.add(d);
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