package control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Saque;

public class SaqueDAO {

	private BD bd;
	private String men, sql;
	
	public SaqueDAO() {
		bd = new BD();
	}

	public String gravar(Saque saque)
	{
		sql = "CALL salvar_saque(?, ?, ?)";
		bd.getConnection();
		try
		{
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1, saque.getId());
			bd.st.setDouble(2, saque.getValor());
			bd.st.setInt(3, saque.getNrconta());
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
		sql = "delete from saque where id = ?";
		bd.getConnection();
		try
		{
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1, id);
			int n = bd.st.executeUpdate();
			if(n == 1)
			{
				men = "Saque excluído com sucesso!";
			}
			else
			{
				men = "Saque não encontrado!";
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
	
	public List<Saque> listar(String sql)
	{
		List<Saque> lista = new ArrayList<Saque>();
		bd.getConnection();
		try
		{
			bd.st = bd.con.prepareStatement(sql);
			bd.rs = bd.st.executeQuery();
			while(bd.rs.next()) //enquanto existirem registros
			{
				Saque s = new Saque();
				s.setId((bd.rs.getInt("id")));;
				s.setValor((bd.rs.getDouble("valor")));;
				s.setNrconta((bd.rs.getInt("nrconta")));;
				lista.add(s);
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