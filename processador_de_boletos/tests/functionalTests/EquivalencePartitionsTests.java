package functionalTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import processadorDeBoletos.Boleto;
import processadorDeBoletos.Fatura;
import processadorDeBoletos.Pagamento;
import processadorDeBoletos.Processador;

class EquivalencePartitionsTests {

	private Processador processador;

    @Before
    public void setUp() {
        processador = new Processador();
    }

    @Test
    public void testCadastraBoletos() {
    	processador.cadastraBoletos("B123", "01/01/2023", 100.00);
    	assertEquals(1, processador.getBoletos().size());
    	
    	processador.cadastraBoletos("B123", "31/12/2023", 100.00);
    	assertEquals(2, processador.getBoletos().size());
    	
    	processador.cadastraBoletos("B123", "29/02/2020", 100.00);
    	assertEquals(3, processador.getBoletos().size());
    	
    	assertThrows(IllegalArgumentException.class, () -> processador.cadastraBoletos("B123", "30/02/2023", 100.00));
    	assertThrows(IllegalArgumentException.class, () -> processador.cadastraBoletos("B123", "32/01/2023", 100.00));
    	assertThrows(IllegalArgumentException.class, () -> processador.cadastraBoletos("B123", "0/01/2023", 100.00));
    	assertThrows(IllegalArgumentException.class, () -> processador.cadastraBoletos("B123", "31/09/2023", 100.00));
    	assertThrows(IllegalArgumentException.class, () -> processador.cadastraBoletos("B123", "11/13/2023", 100.00));
    	assertThrows(IllegalArgumentException.class, () -> processador.cadastraBoletos("B123", "11/0/2023", 100.00));
    }

    @Test
    public void testCadastraFatura() {
    	processador.cadastraFatura("01/01/2023", 100.00, "abc");
    	assertNotNull(processador.getFatura());

    	processador.cadastraFatura("31/12/2023", 100.00, "abc");
    	assertNotNull(processador.getFatura());

    	processador.cadastraFatura("29/02/2020", 100.00, "abc");
    	assertNotNull(processador.getFatura());
    	
        assertThrows(IllegalArgumentException.class, () -> processador.cadastraFatura("30/02/2023", 100.00, "abc"));
        assertThrows(IllegalArgumentException.class, () -> processador.cadastraFatura("32/01/2023", 100.00, "abc"));
        assertThrows(IllegalArgumentException.class, () -> processador.cadastraFatura("0/01/2023", 100.00, "abc")); 
        assertThrows(IllegalArgumentException.class, () -> processador.cadastraFatura("31/09/2023", 100.00, "abc"));
        assertThrows(IllegalArgumentException.class, () -> processador.cadastraFatura("11/13/2023", 100.00, "abc"));
        assertThrows(IllegalArgumentException.class, () -> processador.cadastraFatura("11/0/2023", 100.00, "abc")); 
    }
    
    @Test
    public void testFatura() {
        assertDoesNotThrow(() -> new Fatura("01/01/2023", 100.00, "abc"));
        assertDoesNotThrow(() -> new Fatura("31/12/2023", 100.00, "abc"));
        assertDoesNotThrow(() -> new Fatura("29/02/2020", 100.00, "abc"));
        
        assertThrows(IllegalArgumentException.class, () -> new Fatura("30/02/2023", 100.00, "abc"));
        assertThrows(IllegalArgumentException.class, () -> new Fatura("32/01/2023", 100.00, "abc"));
        assertThrows(IllegalArgumentException.class, () -> new Fatura("0/01/2023", 100.00, "abc"));
        assertThrows(IllegalArgumentException.class, () -> new Fatura("31/09/2023", 100.00, "abc"));
        assertThrows(IllegalArgumentException.class, () -> new Fatura("11/13/2023", 100.00, "abc"));
        assertThrows(IllegalArgumentException.class, () -> new Fatura("11/0/2023", 100.00, "abc"));
    }

    @Test
    public void testBoletos() {
        assertDoesNotThrow(() -> new Boleto("B123", "01/01/2023", 100.00));
        assertDoesNotThrow(() -> new Boleto("B123", "31/12/2023", 100.00));
        assertDoesNotThrow(() -> new Boleto("B123", "29/02/2020", 100.00));
        
        assertThrows(IllegalArgumentException.class, () -> new Boleto("B123", "30/02/2023", 100.00));
        assertThrows(IllegalArgumentException.class, () -> new Boleto("B123", "32/01/2023", 100.00));
        assertThrows(IllegalArgumentException.class, () -> new Boleto("B123", "0/01/2023", 100.00));
        assertThrows(IllegalArgumentException.class, () -> new Boleto("B123", "31/09/2023", 100.00));
        assertThrows(IllegalArgumentException.class, () -> new Boleto("B123", "11/13/2023", 100.00));
        assertThrows(IllegalArgumentException.class, () -> new Boleto("B123", "11/0/2023", 100.00));
    }
    
    @Test
    public void testPagamento() {
    	Boleto boletoTeste = new Boleto("123", "01/01/2023", 100.00);
    	
    	assertDoesNotThrow(() -> new Pagamento(100.00, "01/01/2023", boletoTeste));
    	assertDoesNotThrow(() -> new Pagamento(100.00, "31/12/2023", boletoTeste));
    	assertDoesNotThrow(() -> new Pagamento(100.00, "29/02/2020", boletoTeste));
        
    	assertThrows(IllegalArgumentException.class, () -> new Pagamento(100.00, "30/02/2023", boletoTeste));
        assertThrows(IllegalArgumentException.class, () -> new Pagamento(100.00, "32/01/2023", boletoTeste));
        assertThrows(IllegalArgumentException.class, () -> new Pagamento(100.00, "0/01/2023", boletoTeste));
        assertThrows(IllegalArgumentException.class, () -> new Pagamento(100.00, "31/09/2023", boletoTeste));
        assertThrows(IllegalArgumentException.class, () -> new Pagamento(100.00, "11/13/2023", boletoTeste));
        assertThrows(IllegalArgumentException.class, () -> new Pagamento(100.00, "11/0/2023", boletoTeste));
    }
}
