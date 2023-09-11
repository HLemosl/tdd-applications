package functionalTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import processadorDeBoletos.Boleto;
import processadorDeBoletos.Fatura;
import processadorDeBoletos.Pagamento;
import processadorDeBoletos.Processador;


class LimitValueAnalysisTests {

    private Processador processador;

    @Before
    public void setUp() {
        processador = new Processador();
    }

    @Test
    public void testCadastraBoletos() {
        processador.cadastraBoletos("123", "01/01/2023", 100.00);
        assertEquals(1, processador.getBoletos().size());
        
        processador.cadastraBoletos("123", "01/01/2023", 100000);
        assertEquals(2, processador.getBoletos().size());
        
        processador.cadastraBoletos("123", "01/01/2023", 0.1);
        assertEquals(3, processador.getBoletos().size());

        assertThrows(IllegalArgumentException.class, () -> processador.cadastraBoletos("123", "01/01/2023", -50.00));
        assertThrows(IllegalArgumentException.class, () -> processador.cadastraBoletos("123", "01/01/2023", 0.0));
        assertThrows(IllegalArgumentException.class, () -> processador.cadastraBoletos("123", "01/01/2023", 100001));
    }

    @Test
    public void testCadastraFatura() {
        processador.cadastraFatura("01/01/2023", 100.00, "abc");
        assertNotNull(processador.getFatura());

        processador.cadastraFatura("01/01/2023", 100000, "abc");
        assertNotNull(processador.getFatura());

        processador.cadastraFatura("01/01/2023", 0.1, "abc");
        assertNotNull(processador.getFatura());

        assertThrows(IllegalArgumentException.class, () -> processador.cadastraFatura("01/01/2023", -50.00, "abc"));
        assertThrows(IllegalArgumentException.class, () -> processador.cadastraFatura("01/01/2023", 0.0, "abc"));
        assertThrows(IllegalArgumentException.class, () -> processador.cadastraFatura("01/01/2023", 100001, "abc"));
    }
    
    @Test
    public void testFatura() {
        assertDoesNotThrow(() -> new Fatura("01/01/2023", 100.00, "abc"));
        assertDoesNotThrow(() -> new Fatura("01/01/2023", 100000, "abc"));
        assertDoesNotThrow(() -> new Fatura("01/01/2023", 0.1, "abc"));
        
        assertThrows(IllegalArgumentException.class, () -> new Fatura("01/01/2023", -50.00, "abc"));
        assertThrows(IllegalArgumentException.class, () -> new Fatura("01/01/2023", 0.0, "abc"));
        assertThrows(IllegalArgumentException.class, () -> new Fatura("01/01/2023", 100001, "abc"));
    }

    @Test
    public void testBoletos() {
        assertDoesNotThrow(() -> new Boleto("123", "01/01/2023", 100.00));
        assertDoesNotThrow(() -> new Boleto("123", "01/01/2023", 100000));
        assertDoesNotThrow(() -> new Boleto("123", "01/01/2023", 0.1));
        
        assertThrows(IllegalArgumentException.class, () -> new Boleto("123", "01/01/2023", -50.00));
        assertThrows(IllegalArgumentException.class, () -> new Boleto("123", "01/01/2023", 0.0));
        assertThrows(IllegalArgumentException.class, () -> new Boleto("123", "01/01/2023", 100001));
    }
    
    @Test
    public void testPagamento() {
    	Boleto boletoTeste = new Boleto("123", "01/01/2023", 100.00);
    	
    	assertDoesNotThrow(() -> new Pagamento(100.00, "01/01/2023", boletoTeste));
        assertDoesNotThrow(() -> new Pagamento(100000, "01/01/2023", boletoTeste));
        assertDoesNotThrow(() -> new Pagamento(0.1, "01/01/2023", boletoTeste));
        
        assertThrows(IllegalArgumentException.class, () -> new Pagamento(100001, "01/01/2023", boletoTeste));
        assertThrows(IllegalArgumentException.class, () -> new Pagamento(-50.00, "01/01/2023", boletoTeste));
        assertThrows(IllegalArgumentException.class, () -> new Pagamento(0.0, "01/01/2023", boletoTeste));
    }
}