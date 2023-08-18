package tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import processadorDeBoletos.Boleto;

class BoletoTest {

	@Test
	void testBoleto() {
		new Boleto("4076426207", "18.06.2023", 478.78);
		new Boleto("5933221982", "10082023", 188.77);
		new Boleto("7337230525", "12/08/2023", 866.02);
	}
	
	@Test
	void testBoletoNuloVazio() {
		assertThrows(NullPointerException.class, () -> new Boleto("6925150679", null, 742.79));
		assertThrows(NullPointerException.class, () -> new Boleto(null, "18072023", 278.35));
		
		assertThrows(IllegalArgumentException.class, () -> new Boleto("8055553163", "15/07/2023", 0.0));
		assertThrows(IllegalArgumentException.class, () -> new Boleto("3479182926", "", 866.54));
		assertThrows(IllegalArgumentException.class, () -> new Boleto("", "23.06.2023", 652.27));
	}
	
	@Test
	void testGetValor() {
		Boleto boletoTest = new Boleto("6601536759", "18052023", 46.81);
		assertEquals(boletoTest.getValor(), 46.81);
	}
	
}
