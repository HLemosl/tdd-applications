package processadorDeBoletos;

public class Boleto {

	private String codigo;
	private String data;
	private double valorPago;
	
	Exceptions exception = new Exceptions();
	
	public Boleto(String codigo, String data, double valorPago) {
		exception.verifier(codigo);
		exception.verifier(data);
		exception.verifier(valorPago);
		
		this.codigo = codigo;
		this.data = data;
		this.valorPago = valorPago;
	}

	public Double getValor() {
		return this.valorPago;
	}

}
