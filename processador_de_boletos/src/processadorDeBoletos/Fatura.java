package processadorDeBoletos;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(data, nomeDoCliente, valorTotal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fatura other = (Fatura) obj;
		return Objects.equals(data, other.data) && Objects.equals(nomeDoCliente, other.nomeDoCliente)
				&& Double.doubleToLongBits(valorTotal) == Double.doubleToLongBits(other.valorTotal);
	}

}
