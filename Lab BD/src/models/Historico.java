package models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import control.BD;

public class Historico {
	private BD bd;
	private int numero, agencia;
	private double valor;
	
	public Historico() {
		super();
		bd = new BD();
	}

	public Historico(int numero, int agencia, double valor) {
		super();
		bd = new BD();
		this.numero = numero;
		this.agencia = agencia;
		this.valor = valor;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return "Historico [numero=" + numero + ", agencia=" + agencia + ", valor=" + valor + "]";
	}

	public List<Historico> listarHistorico(String sql)
	{
		List<Historico> lista = new ArrayList<Historico>();
		bd.getConnection();
		try
		{
			bd.st = bd.con.prepareStatement(sql);
			bd.rs = bd.st.executeQuery();
			while(bd.rs.next()) //enquanto existirem registros
			{
				Historico h = new Historico();
				h.setNumero((bd.rs.getInt("numero")));;
				h.setAgencia((bd.rs.getInt("agencia")));;
				h.setValor((bd.rs.getDouble("valor")));;
				lista.add(h);			
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
