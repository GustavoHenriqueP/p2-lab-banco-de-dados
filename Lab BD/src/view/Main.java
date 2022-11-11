package view;

import java.util.List;

import control.ContaDAO;
import control.DepositoDAO;
import control.SaqueDAO;
import models.Conta;
import models.Deposito;
import models.Historico;
import models.Saque;

public class Main {

	public static void main(String[] args) {
		
		Conta c1 = new Conta(1, 01, 517437200, 200);
		Conta c2 = new Conta(2, 02, 517437200, 1000);
		Conta c3 = new Conta(3, 03, 517437200, 100);
		
		ContaDAO cd = new ContaDAO();
		cd.gravar(c1);
		cd.gravar(c2);
		cd.gravar(c3);
		
		Saque s1 = new Saque(1, 20, 1);
		Saque s2 = new Saque(2, 200, 2);
		Saque s3 = new Saque(3, 10, 3);
		
		SaqueDAO sd = new SaqueDAO();
		sd.gravar(s1);
		sd.gravar(s2);
		sd.gravar(s3);
		
		Deposito d1 = new Deposito(1, 40, 1);
		Deposito d2 = new Deposito(2, 300, 2);
		Deposito d3 = new Deposito(3, 5, 3);
		
		DepositoDAO dd = new DepositoDAO();
		dd.gravar(d1);
		dd.gravar(d2);
		dd.gravar(d3);
		
		sd.excluir(1);
		sd.excluir(2);
		sd.excluir(3);
		
		dd.excluir(1);
		dd.excluir(2);
		dd.excluir(3);
		
		Historico h = new Historico();
		List<Historico> listaSaque = h.listarHistorico("SELECT c.numero, c.agencia, s.valor FROM conta c INNER JOIN saque s ON c.numero = s.nrconta WHERE c.numero = 1;");
		for(Historico ls : listaSaque)
		{
			System.out.println(ls);
		}
		
		List<Historico> listaDeposito = h.listarHistorico("SELECT c.numero, c.agencia, d.valor FROM conta c INNER JOIN deposito d ON c.numero = d.nrconta WHERE c.numero = 1");
		for(Historico ld : listaDeposito)
		{
			System.out.println(ld);
		}
	}
}
