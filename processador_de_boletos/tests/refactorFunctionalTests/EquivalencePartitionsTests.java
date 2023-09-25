package refactorFunctionalTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import processadorDeBoletos.Boleto;
import processadorDeBoletos.Fatura;
import processadorDeBoletos.Pagamento;
import processadorDeBoletos.Processador;

class EquivalencePartitionsTests {

	private Processador processador;

    @BeforeEach
    public void setUp() {
        processador = new Processador();
    }

    @ParameterizedTest
    @CsvSource({
        "B123, 01/01/2023, 100.00, 1",
        "B123, 31/12/2023, 100.00, 2",
        "B123, 29/02/2020, 100.00, 3",
        "B123, 30/02/2023, 100.00, error",
        "B123, 32/01/2023, 100.00, error",
        "B123, 0/01/2023, 100.00, error",
        "B123, 31/09/2023, 100.00, error",
        "B123, 11/13/2023, 100.00, error",
        "B123, 11/0/2023, 100.00, error"
    })
    @DisplayName("Teste Cadastra Boletos")
    @Test
    void testCadastraBoletos(String codigo, String data, double valor, String expected) {
        if (expected.equals("error")) {
            assertThrows(IllegalArgumentException.class, () -> processador.cadastraBoletos(codigo, data, valor));
        } else {
            processador.cadastraBoletos(codigo, data, valor);
            assertEquals(Integer.parseInt(expected), processador.getBoletos().size());
        }
    }

    @ParameterizedTest
    @CsvSource({
        "01/01/2023, 100.00, abc",
        "31/12/2023, 100.00, abc",
        "29/02/2020, 100.00, abc",
    })
    @DisplayName("Teste Cadastra Fatura - Casos Válidos")
    @Test
    void testCadastraFaturaValid(String data, double valor, String nome) {
        processador.cadastraFatura(data, valor, nome);
        assertNotNull(processador.getFatura());
    }

    @ParameterizedTest
    @CsvSource({
        "30/02/2023, 100.00, abc",
        "32/01/2023, 100.00, abc",
        "0/01/2023, 100.00, abc",
        "31/09/2023, 100.00, abc",
        "11/13/2023, 100.00, abc",
        "11/0/2023, 100.00, abc"
    })
    @DisplayName("Teste Cadastra Fatura - Casos Inválidos")
    @Test
    void testCadastraFaturaInvalid(String data, double valor, String nome) {
        assertThrows(IllegalArgumentException.class, () -> processador.cadastraFatura(data, valor, nome));
    }
    
    @ParameterizedTest
    @CsvSource({
        "01/01/2023, 100.00, abc",
        "31/12/2023, 100.00, abc",
        "29/02/2020, 100.00, abc"
    })
    @DisplayName("Teste Fatura - Casos Válidos")
    @Test
    void testFaturaValid(String data, double valor, String nome) {
        assertDoesNotThrow(() -> new Fatura(data, valor, nome));
    }

    @ParameterizedTest
    @CsvSource({
        "30/02/2023, 100.00, abc",
        "32/01/2023, 100.00, abc",
        "0/01/2023, 100.00, abc",
        "31/09/2023, 100.00, abc",
        "11/13/2023, 100.00, abc",
        "11/0/2023, 100.00, abc"
    })
    @DisplayName("Teste Fatura - Casos Inválidos")
    @Test
    void testFaturaInvalid(String data, double valor, String nome) {
        assertThrows(IllegalArgumentException.class, () -> new Fatura(data, valor, nome));
    }

    @ParameterizedTest
    @CsvSource({
        "B123, 01/01/2023, 100.00",
        "B123, 31/12/2023, 100.00",
        "B123, 29/02/2020, 100.00"
    })
    @DisplayName("Teste Boleto - Casos Válidos")
    @Test
    void testBoletosValid(String codigo, String data, double valor) {
        assertDoesNotThrow(() -> new Boleto(codigo, data, valor));
    }

    @ParameterizedTest
    @CsvSource({
        "B123, 30/02/2023, 100.00",
        "B123, 32/01/2023, 100.00",
        "B123, 0/01/2023, 100.00",
        "B123, 31/09/2023, 100.00",
        "B123, 11/13/2023, 100.00",
        "B123, 11/0/2023, 100.00"
    })
    @DisplayName("Teste Boleto - Casos Inválidos")
    @Test
    void testBoletosInvalid(String codigo, String data, double valor) {
        assertThrows(IllegalArgumentException.class, () -> new Boleto(codigo, data, valor));
    }
    
    @ParameterizedTest
    @CsvSource({
        "100.00, 01/01/2023",
        "100.00, 31/12/2023",
        "100.00, 29/02/2020"
    })
    @DisplayName("Teste Pagamento - Casos Válidos")
    @Test
    void testPagamentoValid(double valor, String data) {
        Boleto boletoTeste = new Boleto("123", "01/01/2023", 100.00);
        assertDoesNotThrow(() -> new Pagamento(valor, data, boletoTeste));
    }
    
    @ParameterizedTest
    @CsvSource({
        "30/02/2023",
        "32/01/2023",
        "0/01/2023",
        "31/09/2023",
        "11/13/2023",
        "11/0/2023"
    })
    @DisplayName("Teste Pagamento - Casos Inválidos")
    @Test
    void testPagamentoInvalid(String data) {
        Boleto boletoTeste = new Boleto("123", "01/01/2023", 100.00);
        assertThrows(IllegalArgumentException.class, () -> new Pagamento(100.00, data, boletoTeste));
    }
}
