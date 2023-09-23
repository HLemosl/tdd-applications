package functionalTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest
    @CsvSource({
        "123, 01/01/2023, 100.00, 1",
        "123, 01/01/2023, 100000, 2",
        "123, 01/01/2023, 0.1, 3"
    })
    @DisplayName("Teste Cadastra Boletos")
    @Test
    void testCadastraBoletos(String codigo, String data, double valor, int expectedSize) {
        processador.cadastraBoletos(codigo, data, valor);
        assertEquals(expectedSize, processador.getBoletos().size());
    }

    @ParameterizedTest
    @CsvSource({
        "123, 01/01/2023, -50.00",
        "123, 01/01/2023, 0.0",
        "123, 01/01/2023, 100001"
    })
    @DisplayName("Teste Cadastra Boletos - Casos de Erro")
    @Test
    void testCadastraBoletosErro(String codigo, String data, double valor) {
        assertThrows(IllegalArgumentException.class, () -> processador.cadastraBoletos(codigo, data, valor));
    }

    @ParameterizedTest
    @CsvSource({
        "01/01/2023, 100.00, abc",
        "01/01/2023, 100000, abc",
        "01/01/2023, 0.1, abc"
    })
    @DisplayName("Teste Cadastra Fatura")
    @Test
    void testCadastraFatura(String data, double valor, String nome) {
        processador.cadastraFatura(data, valor, nome);
        assertNotNull(processador.getFatura());
    }

    @ParameterizedTest
    @CsvSource({
        "01/01/2023, -50.00, abc",
        "01/01/2023, 0.0, abc",
        "01/01/2023, 100001, abc"
    })
    @DisplayName("Teste Cadastra Fatura - Casos de Erro")
    @Test
    void testCadastraFaturaErro(String data, double valor, String nome) {
        assertThrows(IllegalArgumentException.class, () -> processador.cadastraFatura(data, valor, nome));
    }
    
    @ParameterizedTest
    @CsvSource({
        "01/01/2023, 100.00, abc",
        "01/01/2023, 100000, abc",
        "01/01/2023, 0.1, abc"
    })
    @DisplayName("Teste Fatura - Casos de Sucesso")
    @Test
    void testFaturaSucesso(String data, double valor, String nome) {
        assertDoesNotThrow(() -> new Fatura(data, valor, nome));
    }

    @ParameterizedTest
    @CsvSource({
        "01/01/2023, -50.00, abc",
        "01/01/2023, 0.0, abc",
        "01/01/2023, 100001, abc"
    })
    @DisplayName("Teste Fatura - Casos de Erro")
    @Test
    void testFaturaErro(String data, double valor, String nome) {
        assertThrows(IllegalArgumentException.class, () -> new Fatura(data, valor, nome));
    }

    @ParameterizedTest
    @CsvSource({
        "123, 01/01/2023, 100.00",
        "123, 01/01/2023, 100000",
        "123, 01/01/2023, 0.1"
    })
    @DisplayName("Teste Boletos - Casos de Sucesso")
    @Test
    void testBoletosSucesso(String codigo, String data, double valor) {
        assertDoesNotThrow(() -> new Boleto(codigo, data, valor));
    }

    @ParameterizedTest
    @CsvSource({
        "123, 01/01/2023, -50.00",
        "123, 01/01/2023, 0.0",
        "123, 01/01/2023, 100001"
    })
    @DisplayName("Teste Boletos - Casos de Erro")
    @Test
    void testBoletosErro(String codigo, String data, double valor) {
        assertThrows(IllegalArgumentException.class, () -> new Boleto(codigo, data, valor));
    }
    
    @ParameterizedTest
    @CsvSource({
        "100.00, 01/01/2023, 100.00",
        "100000, 01/01/2023, 100000",
        "0.1, 01/01/2023, 0.1"
    })
    @DisplayName("Teste Pagamento - Casos de Sucesso")
    @Test
    void testPagamentoSucesso(double valorPago, String data, double valorBoleto) {
        Boleto boletoTeste = new Boleto("123", "01/01/2023", valorBoleto);
        assertDoesNotThrow(() -> new Pagamento(valorPago, data, boletoTeste));
    }

    @ParameterizedTest
    @CsvSource({
        "100001, 01/01/2023, 100.00",
        "-50.00, 01/01/2023, 100.00",
        "0.0, 01/01/2023, 100.00"
    })
    @DisplayName("Teste Pagamento - Casos de Erro")
    @Test
    void testPagamentoErro(double valorPago, String data, double valorBoleto) {
        Boleto boletoTeste = new Boleto("123", "01/01/2023", valorBoleto);
        assertThrows(IllegalArgumentException.class, () -> new Pagamento(valorPago, data, boletoTeste));
    }
}