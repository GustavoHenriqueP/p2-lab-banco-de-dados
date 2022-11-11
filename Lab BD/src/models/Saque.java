package models;

public class Saque {
	private int id, nrconta;
	private double valor;
	
	
	
	public Saque() {
		super();
	}

	public Saque(int id, double valor,int nrconta) {
		super();
		this.id = id;
		this.valor = valor;
		this.nrconta = nrconta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNrconta() {
		return nrconta;
	}

	public void setNrconta(int nrconta) {
		this.nrconta = nrconta;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
}
