package processadorDeBoletos;

public class Pagamento {

	private double valorPago;
	private String data;
	private Object tipoPagamento;
	
	Exceptions exceptions = new Exceptions();

	public Pagamento(double valorPago, String data, Object tipoPagamento) {
		exceptions.verifier(valorPago);
		exceptions.verifier(data);
		exceptions.verifier(tipoPagamento);
		
		this.valorPago = valorPago;
		this.data = data;
		this.tipoPagamento = tipoPagamento;
	}

}
