package processadorDeBoletos;

import java.util.ArrayList;
import java.util.List;

public class Processador {
	
	private List<Boleto> boletos;
	private Exceptions exceptions;
	
	public Processador() {
		this.boletos = new ArrayList<Boleto>();
		this.exceptions = new Exceptions();
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

}
