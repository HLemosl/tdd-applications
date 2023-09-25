package refactorFunctionalTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Test;

import gerenciadorDeTarefas.Prioridade;
import gerenciadorDeTarefas.SistemaTarefas;
import gerenciadorDeTarefas.Tarefa;

public class DecisionTablesTests {

    private Tarefa tarefaValida;
    private SistemaTarefas sistemaTarefas;

    @BeforeEach
    public void configurar() {
        tarefaValida = new Tarefa("Fazer compras", "No mercado Assaí às 09h", "12/09/2023", Prioridade.ALTA);
        sistemaTarefas = new SistemaTarefas();
    }

    @Test
    @DisplayName("Teste criar tarefa válida")
    public void testTarefa() {
        sistemaTarefas.adicionarTarefa(tarefaValida);

        List<Tarefa> todasTarefas = sistemaTarefas.listarTodasTarefas();

        assertEquals(1, todasTarefas.size());
        assertThrows(IllegalArgumentException.class, () -> new Tarefa("", "No mercado Assaí às 09h", "12/09/2023", Prioridade.ALTA));
        assertThrows(IllegalArgumentException.class, () -> new Tarefa("Fazer compras", "", "12/09/2023", Prioridade.ALTA));
        assertThrows(IllegalArgumentException.class, () -> new Tarefa("Fazer compras", "No mercado Assaí às 09h", "", Prioridade.ALTA));
        assertThrows(NullPointerException.class, () -> new Tarefa("Fazer compras", "No mercado Assaí às 09h", "12/09/2023", null));
    }

    @ParameterizedTest
    @DisplayName("Teste criar tarefas inválidas")
    @MethodSource("invalidTaskArguments")
    public void testInvalidTarefa(String titulo, String descricao, String dataVencimento, Prioridade prioridade) {
        assertThrows(IllegalArgumentException.class, () -> new Tarefa(titulo, descricao, dataVencimento, prioridade));
    }

    private static Stream<Arguments> invalidTaskArguments() {
        return Stream.of(
            Arguments.of("", "No mercado Assaí às 09h", "12/09/2023", Prioridade.ALTA),
            Arguments.of("Fazer compras", "", "12/09/2023", Prioridade.ALTA),
            Arguments.of("Fazer compras", "No mercado Assaí às 09h", "", Prioridade.ALTA)
        );
    }

    @Test
    @DisplayName("Teste adicionar tarefa")
    public void testAdicionarTarefa() {
        sistemaTarefas.adicionarTarefa(tarefaValida);

        List<Tarefa> todasTarefas = sistemaTarefas.listarTodasTarefas();

        assertEquals(1, todasTarefas.size());
    }

    @ParameterizedTest
    @DisplayName("Teste adicionar tarefas inválidas")
    @MethodSource("invalidTaskArguments")
    public void testInvalidAdicionarTarefa(String titulo, String descricao, String dataVencimento, Prioridade prioridade) {
        assertThrows(IllegalArgumentException.class, () -> sistemaTarefas.adicionarTarefa(new Tarefa(titulo, descricao, dataVencimento, prioridade)));
    }

    @Test
    @DisplayName("Teste atualizar tarefa")
    public void testAtualizarTarefa() {
        sistemaTarefas.adicionarTarefa(tarefaValida);
        Tarefa tarefaAtualizada = new Tarefa("Fazer compras", "No mercado Assaí às 09h", "12/10/2023", Prioridade.ALTA);
        sistemaTarefas.atualizarTarefa(0, tarefaAtualizada);

        List<Tarefa> todasTarefas = sistemaTarefas.listarTodasTarefas();

        assertEquals("12/10/2023", todasTarefas.get(0).getDataVencimento());
    }

    @ParameterizedTest
    @DisplayName("Teste atualizar tarefas inválidas")
    @MethodSource("invalidTaskArguments")
    public void testInvalidAtualizarTarefa(String titulo, String descricao, String dataVencimento, Prioridade prioridade) {
        sistemaTarefas.adicionarTarefa(tarefaValida);
        assertThrows(IllegalArgumentException.class, () -> sistemaTarefas.atualizarTarefa(0, new Tarefa(titulo, descricao, dataVencimento, prioridade)));
    }
}
