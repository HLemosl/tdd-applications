package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import processadorDeBoletos.Boleto;
import processadorDeBoletos.Fatura;
import processadorDeBoletos.Processador;

class ProcessadorTest {
	
	private Processador processador;

	@BeforeEach
	void setUp() throws Exception {
		processador = new Processador();
		
		List<Boleto> boletos = new ArrayList<Boleto>();
		
		boletos.add(new Boleto("4076426207", "12.06.2023", 500.00));
		boletos.add(new Boleto("5933221982", "12072023", 400.00));
		boletos.add(new Boleto("7337230525", "12/08/2023", 600.00));
		
		Fatura fatura = new Fatura("12052023", 1500.00, "Kai Melo Pereira");
	}

	@Test
	void testCadastraBoleto() {
		processador.cadastraBoletos("4076426207", "12.06.2023", 500.00);
		processador.cadastraBoletos("7337230525", "12/08/2023", 600.00);
		
		assertEquals(processador.getBoletos().size(), 2);
	}

	@Test
	void testCadastraBoletoNullVazio() {
		assertThrows(NullPointerException.class, () -> processador.cadastraBoletos("6925150679", null, 742.79));
		assertThrows(NullPointerException.class, () -> processador.cadastraBoletos(null, "18072023", 278.35));
		
		assertThrows(IllegalArgumentException.class, () -> processador.cadastraBoletos("8055553163", "15/07/2023", 0.0));
		assertThrows(IllegalArgumentException.class, () -> processador.cadastraBoletos("3479182926", "", 866.54));
		assertThrows(IllegalArgumentException.class, () -> processador.cadastraBoletos("", "23.06.2023", 652.27));
	}
	
	@Test
	void testCadastraFatura() {
		Fatura fatura = new Fatura("12052023", 1500.00, "Kai Melo Pereira");
		processador.cadastraFatura("12052023", 1500.00, "Kai Melo Pereira");
		
		assertEquals(processador.getFatura(), fatura);
	}
	
	@Test
	void testCadastraFaturaNullVazio() {
		assertThrows(NullPointerException.class, () -> processador.cadastraFatura("18072023", 39.67, null));
		assertThrows(NullPointerException.class, () -> processador.cadastraFatura(null, 97.00, ""));
		
		assertThrows(IllegalArgumentException.class, () -> processador.cadastraFatura("12/08/2023", 78.39, ""));
		assertThrows(IllegalArgumentException.class, () -> processador.cadastraFatura("10082023", 0.0, ""));
		assertThrows(IllegalArgumentException.class, () -> processador.cadastraFatura("", 78.35, ""));
	}
	
	@Test
	void testVerificadorDePagamento() {
		processador.verificadorDePagamento(boletos, fatura);
		assertTrue(fatura.getIsPaga());
		
		int numPagamentos = boletos.size();
		assertEquals(numPagamentos, 3);
	}
}
