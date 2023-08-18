package tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PagamentoTest {

	@Test
	void testPagamento() {
		Boleto boletotest = new Boleto("4076426207", "18.06.2023", 478.78);
		Boleto boletotest2 = new Boleto("5933221982", "10082023", 188.77);
		
		new Pagamento(25.78, "10082023", boletoTest2);
		new Pagamento(51.07, "18.06.2023", boletoTest);
		new Pagamento(10.96, "15/07/2023", boletoTest2);
	}

	@Test
	void testPagamentoNuloVazio() {
		Boleto boletotest = new Boleto("7337230525", "12/08/2023", 866.02);
		Boleto boletotest2 = new Boleto("6601536759", "18052023", 46.81);
		
		assertThrows(NullPointerException.class, () -> new Pagamento(14.08, "18072023", null));
		assertThrows(NullPointerException.class, () -> new Pagamento(14.41, null, boletoTest));
		assertThrows(NullPointerException.class, () -> new Pagamento(null, "23.06.2023", boletoTest2));
		
		assertThrows(IllegalArgumentException.class, () -> new Pagamento(80.60, "", boletoTest));
		assertThrows(IllegalArgumentException.class, () -> new Pagamento(0.0, "18052023", boletoTest2));
	}
	
	@Test
	void testPagamento() {
		
	}

}
