package tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FaturaTest {

	@Test
	void testFatura() {
		new Fatura("18052023", 5.32, "Kai Melo Pereira");
		new Fatura("23.06.2023", 96.84, "Luan Pereira Alves");
		new Fatura("15/07/2023", 18.66, "Emily Cavalcanti Almeida");
	}
	
	@Test
	void testFaturaNuloVazio() {
		assertThrows(NullPointerException.class, () -> new Fatura("18072023", 39.67, null));
		assertThrows(NullPointerException.class, () -> new Fatura("02.08.2023", null, ""));
		assertThrows(NullPointerException.class, () -> new Fatura(null, 97.00, ""));
		
		assertThrows(IllegalArgumentException.class, () -> new Fatura("12/08/2023", 78.39, ""));
		assertThrows(IllegalArgumentException.class, () -> new Fatura("10082023", 0.0, ""));
		assertThrows(IllegalArgumentException.class, () -> new Fatura("", 78.35, ""));
	}
	
	@Test
	void testGetValor() {
		Fatura faturaTest = new Fatura("18.06.2023", 48.02, "Kauan Correia Castro");
		assertEquals(faturaTest.getValor(), 48.02);
	}
	
	@Test
	void testGetIsPaga() {
		Fatura faturaTest = new Fatura("18.06.2023", 48.02, "Kauan Correia Castro");
		assertFalse(faturaTest.getIsPaga());
		
		faturaTest.setIsPaga();
		assertTrue(faturaTest.getIsPaga());
	}
}
