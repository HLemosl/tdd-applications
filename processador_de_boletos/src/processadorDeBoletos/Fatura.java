package processadorDeBoletos;

public class Fatura {

	private String data;
	private double valorTotal;
	private String nomeDoCliente;
	private boolean isPaga;
	
	Exceptions exceptions = new Exceptions();

	public Fatura(String data, double valorTotal, String nomeDoCliente) {
		exceptions.verifier(data);
		exceptions.verifier(valorTotal);
		exceptions.verifier(nomeDoCliente);
		
		this.data = data;
		this.valorTotal = valorTotal;
		this.nomeDoCliente = nomeDoCliente;
		this.isPaga = false;
	}

	public Double getValor() {
		return this.valorTotal;
	}
	
	public boolean getIsPaga() {
		return this.isPaga;
	}

	public void setIsPaga() {
		this.isPaga = !getIsPaga();
	}
}
