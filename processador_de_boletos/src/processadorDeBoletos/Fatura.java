package processadorDeBoletos;

public class Fatura {

	private String data;
	private double valorTotal;
	private String nomeDoCliente;

	public Fatura(String data, double valorTotal, String nomeDoCliente) {
		this.data = data;
		this.valorTotal = valorTotal;
		this.nomeDoCliente = nomeDoCliente;
	}
}
