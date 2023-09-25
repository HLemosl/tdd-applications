package junit5Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import processadorDeBoletos.Boleto;
import processadorDeBoletos.Fatura;
import processadorDeBoletos.Pagamento;
import processadorDeBoletos.Processador;

class DecisionTablesTests {

	private Processador processador;
    
    @BeforeEach
    public void setUp() {
        processador = new Processador();
    }

    @ParameterizedTest
    @CsvSource({
        "B001, 01/01/2023, 100.00",
        ", 01/01/2023, 100.00",
        "B001, 30/02/2023, 100.00",
        "B001, 01/01/2023, 0.0"
    })
    @DisplayName("Teste Cadastra Boletos")
    @Test
    void testCadastraBoletos(String codigo, String data, double valor) {
        if (codigo.isEmpty() || data.equals("30/02/2023") || valor == 0.0) {
            assertThrows(IllegalArgumentException.class, () -> processador.cadastraBoletos(codigo, data, valor));
        } else {
            processador.cadastraBoletos(codigo, data, valor);
            assertEquals(1, processador.getBoletos().size());
        }
    }

    @ParameterizedTest
    @CsvSource({
        "01/01/2023, 100.00, abc",
        "01/01/2023, 0.0, abc",
        "01/01/2023, 100.00, ''"
    })
    @DisplayName("Teste Cadastra Fatura")
    @Test
    void testCadastraFatura(String data, double valor, String nome) {
        if (valor == 0.0 || nome.isEmpty()) {
            assertThrows(IllegalArgumentException.class, () -> processador.cadastraFatura(data, valor, nome));
        } else {
            processador.cadastraFatura(data, valor, nome);
            assertNotNull(processador.getFatura());
        }
    }

    @DisplayName("Teste para verificar o pagamento")
    @Test
    public void testVerificadorDePagamento() {
        processador.cadastraBoletos("B001", "01/01/2023", 100.00);
        processador.cadastraBoletos("B002", "01/02/2023", 100.00);
        processador.cadastraBoletos("B003", "01/03/2023", 100.00);
        processador.cadastraFatura("01/03/2023", 300.00, "João Vitor");
        processador.verificadorDePagamento(processador.getBoletos(), (Fatura) processador.getFatura());
        assertEquals(1, processador.getPagamentos().size());

        processador.cadastraBoletos("B001", "01/01/2023", 100.00);
        processador.cadastraBoletos("B002", "01/02/2023", 100.00);
        processador.cadastraBoletos("B003", "01/03/2023", 100.00);
        processador.cadastraFatura("01/03/2023", 400.00, "Pedro Henrique");
        assertThrows(Exception.class, () -> processador.verificadorDePagamento(processador.getBoletos(), (Fatura) processador.getFatura()));
    }
    
    @DisplayName("Teste para criação de fatura")
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
    
    @ParameterizedTest
    @CsvSource({
        "B001, 01/01/2023, 100.00",
        ", 01/01/2023, 100.00",
        "B001, 30/02/2023, 100.00",
        "B001, 01/01/2023, -100.00"
    })
    @DisplayName("Teste criação de boleto")
    @Test
    void testBoleto(String codigo, String data, double valor) {
        if (codigo == null || codigo.isEmpty() || data.equals("30/02/2023") || valor < 0) {
            assertThrows(IllegalArgumentException.class, () -> new Boleto(codigo, data, valor));
        } else {
            assertDoesNotThrow(() -> new Boleto(codigo, data, valor));
        }
    }

    @ParameterizedTest
    @CsvSource({
        "100.00, 01/01/2023, true",
        "-100.00, 01/01/2023, error",
        "100.00, 30/02/2023, error",
        "100.00, 01/01/2023, error"
    })
    @DisplayName("Teste Pagamento")
    @Test
    void testPagamento(double valor, String data, String expected) {
        Boleto boletoTeste = new Boleto("123", "01/01/2023", 100.00);
        
        if (expected.equals("error")) {
            assertThrows(IllegalArgumentException.class, () -> new Pagamento(valor, data, boletoTeste));
        } else {
            assertDoesNotThrow(() -> new Pagamento(valor, data, boletoTeste));
        }
    }
}
