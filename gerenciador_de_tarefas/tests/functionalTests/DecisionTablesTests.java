package functionalTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    @DisplayName("Teste para adicionar tarefa válida")
    public void testAdicionarTarefaValida() {
        sistemaTarefas.adicionarTarefa(tarefaValida);

        List<Tarefa> todasTarefas = sistemaTarefas.listarTodasTarefas();

        assertEquals(1, todasTarefas.size());
    }

    @ParameterizedTest
    @CsvSource({ "Fazer compras, No mercado Assaí às 09h", "Estudar, 15/09/2023" })
    @DisplayName("Teste para adicionar tarefa inválida")
    public void testAdicionarTarefaInvalida(String descricao, String dataVencimento) {
        assertThrows(IllegalArgumentException.class, () -> sistemaTarefas.adicionarTarefa(new Tarefa("", descricao, dataVencimento, Prioridade.ALTA)));
    }

    @ParameterizedTest
    @CsvSource({ "-1" })
    @DisplayName("Teste para atualizar tarefa com índices inválidos")
    public void testAtualizarTarefaIndiceInvalido(int indice) {
        assertThrows(IllegalArgumentException.class, () -> sistemaTarefas.atualizarTarefa(indice, tarefaValida));
    }
}
