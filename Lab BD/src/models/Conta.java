package models;

public class Conta {
	private int numero, agencia;
	private long cpf;
	double saldo;
	

	public Conta() {
		super();
	}
	
	public Conta(int numero, int agencia, long cpf, double saldo) {
		super();
		this.numero = numero;
		this.agencia = agencia;
		this.cpf = cpf;
		this.saldo = saldo;
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

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
}
