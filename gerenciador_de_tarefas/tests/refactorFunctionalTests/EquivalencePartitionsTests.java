package refactorFunctionalTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import gerenciadorDeTarefas.Prioridade;
import gerenciadorDeTarefas.SistemaTarefas;
import gerenciadorDeTarefas.Tarefa;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EquivalencePartitionsTests {

    private Tarefa tarefaValida;
    private SistemaTarefas sistemaTarefas;

    @BeforeEach
    public void configurar() {
        tarefaValida = new Tarefa("Fazer compras", "No mercado Assaí às 09h", "12/09/2023", Prioridade.ALTA);
        sistemaTarefas = new SistemaTarefas();
    }

    @Test
    @Order(1)
    @DisplayName("Teste para adicionar tarefa válida")
    public void testAdicionarTarefaValida() {
        sistemaTarefas.adicionarTarefa(tarefaValida);
        List<Tarefa> todasTarefas = sistemaTarefas.listarTodasTarefas();
        assertEquals(1, todasTarefas.size());
    }

    @ParameterizedTest
    @MethodSource("invalidTarefaProvider")
    @Order(2)
    @DisplayName("Teste para adicionar tarefa inválida")
    public void testAdicionarTarefaInvalida(String titulo, String descricao, String dataVencimento, Prioridade prioridade) {
        assertThrows(IllegalArgumentException.class, () -> sistemaTarefas.adicionarTarefa(new Tarefa(titulo, descricao, dataVencimento, prioridade)));
    }

    private static Stream<Arguments> invalidTarefaProvider() {
        return Stream.of(
        		Arguments.of("", "No mercado Assaí às 09h", "12/09/2023", Prioridade.ALTA),
        		Arguments.of("Fazer compras", "", "12/09/2023", Prioridade.ALTA),
        		Arguments.of("Fazer compras", "No mercado Assaí às 09h", "", Prioridade.ALTA)
        );
    }

    @Test
    @Order(3)
    @DisplayName("Teste para atualizar tarefa válida")
    public void testAtualizarTarefaValida() {
        sistemaTarefas.adicionarTarefa(tarefaValida);
        Tarefa tarefaAtualizada = new Tarefa("Fazer compras", "No mercado Assaí às 09h", "12/10/2023", Prioridade.ALTA);
        sistemaTarefas.atualizarTarefa(0, tarefaAtualizada);

        List<Tarefa> todasTarefas = sistemaTarefas.listarTodasTarefas();

        assertEquals("12/10/2023", todasTarefas.get(0).getDataVencimento());
    }

    @ParameterizedTest
    @MethodSource("invalidTarefaProvider")
    @Order(4)
    @DisplayName("Teste para atualizar tarefa inválida")
    public void testAtualizarTarefaInvalida(String titulo, String descricao, String dataVencimento, Prioridade prioridade) {
        sistemaTarefas.adicionarTarefa(tarefaValida);
        assertThrows(IllegalArgumentException.class, () -> sistemaTarefas.atualizarTarefa(0, new Tarefa(titulo, descricao, dataVencimento, prioridade)));
    }
}
