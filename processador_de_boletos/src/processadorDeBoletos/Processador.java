package processadorDeBoletos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Processador {
	
	private List<Boleto> boletos;
	private Fatura fatura;
	private List<Pagamento> pagamentos; 
	private Exceptions exceptions;
	
	public Processador() {
		this.boletos = new ArrayList<Boleto>();
		this.exceptions = new Exceptions();
		this.pagamentos = new ArrayList<Pagamento>();
	}

	public void cadastraBoletos(String codigo, String data, double valorPago) {
		exceptions.verifier(codigo);
		exceptions.verifier(data);
		exceptions.verifier(valorPago);
		
		Boleto newBoleto = new Boleto(codigo, data, valorPago);
		this.boletos.add(newBoleto);
	}

	public List<Boleto> getBoletos() {
		return this.boletos;
	}

	public void cadastraFatura(String data, double valorTotal, String nomeDoCliente) {
		exceptions.verifier(data);
		exceptions.verifier(valorTotal);
		exceptions.verifier(nomeDoCliente);
		
		this.fatura = new Fatura(data, valorTotal, nomeDoCliente);
	}

	public Object getFatura() {
		return this.fatura;
	}

	public void verificadorDePagamento(List<Boleto> boletos, Fatura fatura) {
		double somaDosBoletos = 0.0;
		
		for (Boleto boleto : boletos) {
			somaDosBoletos += boleto.getValor();
		}
		if (somaDosBoletos == fatura.getValor()) {
			Date dataAtual = new Date();
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			String dataFormatada = formatoData.format(dataAtual);
			
			for (Boleto boleto : boletos) {
				pagamentos.add(new Pagamento(boleto.getValor(), dataFormatada, boleto));
			}
			fatura.setIsPaga();
		}
	}

}
