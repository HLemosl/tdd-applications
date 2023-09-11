package functionalTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import processadorDeBoletos.Boleto;
import processadorDeBoletos.Fatura;
import processadorDeBoletos.Pagamento;
import processadorDeBoletos.Processador;

class DecisionTablesTests {

	private Processador processador;
    
    @Before
    public void setUp() {
        processador = new Processador();
    }

    @Test
    public void testCadastraBoletos() {
        processador.cadastraBoletos("B001", "01/01/2023", 100.00);
		assertEquals(1, processador.getBoletos().size());
		
        assertThrows(IllegalArgumentException.class, () -> processador.cadastraBoletos("", "01/01/2023", 100.00));
        assertThrows(IllegalArgumentException.class, () -> processador.cadastraBoletos("B001", "30/02/2023", 100.00));
        assertThrows(IllegalArgumentException.class, () -> processador.cadastraBoletos("B001", "01/01/2023", 0.0));
    }

    @Test
    public void testCadastraFatura() {
        processador.cadastraFatura("01/01/2023", 100.00, "abc");
		assertNotNull(processador.getFatura());

        assertThrows(IllegalArgumentException.class, () -> processador.cadastraFatura("01/01/2023", 0.0, "abc"));
        assertThrows(IllegalArgumentException.class, () -> processador.cadastraFatura("01/01/2023", 100.00, ""));

    }

    @Test
    public void testVerificadorDePagamento() {
        processador.cadastraBoletos("B001", "01/01/2023", 100.00);
        processador.cadastraBoletos("B002", "01/02/2023", 100.00);
        processador.cadastraBoletos("B003", "01/03/2023", 100.00);
        processador.cadastraFatura("01/03/2023", 300.00, "João Vitor");
        processador.verificadorDePagamento(processador.getBoletos(), (Fatura) processador.getFatura());
        
        // Teste 8: Pago
        assertEquals(1, processador.getPagamentos().size());

        processador.cadastraBoletos("B001", "01/01/2023", 100.00);
        processador.cadastraBoletos("B002", "01/02/2023", 100.00);
        processador.cadastraBoletos("B003", "01/03/2023", 100.00);
        processador.cadastraFatura("01/03/2023", 400.00, "Pedro Henrique");
        assertThrows(Exception.class, () -> processador.verificadorDePagamento(processador.getBoletos(), (Fatura) processador.getFatura()));
    }
    
    @Test
    public void testFatura() {
        assertEquals(false, (new Fatura("01/01/2023", 100.00, "abc")).getIsPaga());
        
        Fatura fatura = new Fatura("01/01/2023", 100.00, "abc");
        fatura.setIsPaga();
        assertEquals(true, fatura.getIsPaga());

        assertThrows(IllegalArgumentException.class, () -> new Fatura("30/02/2023", 100.00, "abc"));
        assertThrows(IllegalArgumentException.class, () -> new Fatura("01/01/2023", 0.0, "abc"));
        assertThrows(IllegalArgumentException.class, () -> new Fatura("01/01/2023", 100.0, ""));
    }
    
    @Test
    public void testBoleto() {
        assertDoesNotThrow(() -> new Boleto("B001", "01/01/2023", 100.00));

        assertThrows(IllegalArgumentException.class, () -> new Boleto("", "01/01/2023", 100.00));
        assertThrows(IllegalArgumentException.class, () -> new Boleto("B001", "30/02/2023", 100.00));
        assertThrows(IllegalArgumentException.class, () -> new Boleto("B001", "01/01/2023", -100.00));
    }

    @Test
    public void testPagamento() {
    	Boleto boletoTeste = new Boleto("123", "01/01/2023", 100.00);
    	
    	assertDoesNotThrow(() -> new Pagamento(100.00, "01/01/2023", boletoTeste));
    	
    	assertThrows(IllegalArgumentException.class, () -> new Pagamento(-100.00, "01/01/2023", boletoTeste));
    	assertThrows(IllegalArgumentException.class, () -> new Pagamento(100.00, "30/02/2023", boletoTeste));
    	assertThrows(NullPointerException.class, () -> new Pagamento(100.00, "01/01/2023", null));
    }
    
}
