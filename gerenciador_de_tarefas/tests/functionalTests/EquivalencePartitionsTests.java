package functionalTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gerenciadorDeTarefas.Prioridade;
import gerenciadorDeTarefas.SistemaTarefas;
import gerenciadorDeTarefas.Tarefa;

public class EquivalencePartitionsTests {

	private Tarefa tarefaValida;
	private SistemaTarefas sistemaTarefas;

    @BeforeEach
    public void configurar() {
    	tarefaValida = new Tarefa("Fazer compras", "No mercado Assaí às 09h", "12/09/2023", Prioridade.ALTA);
    	sistemaTarefas = new SistemaTarefas();
    }

    @Test
    public void testTarefa() {
        sistemaTarefas.adicionarTarefa(tarefaValida);

        List<Tarefa> todasTarefas = sistemaTarefas.listarTodasTarefas();
        
        assertEquals(1, todasTarefas.size());
        assertThrows(IllegalArgumentException.class, () -> new Tarefa("", "No mercado Assaí às 09h", "12/09/2023", Prioridade.ALTA));
        assertThrows(IllegalArgumentException.class, () -> new Tarefa("Fazer compras", "", "12/09/2023", Prioridade.ALTA));
        assertThrows(IllegalArgumentException.class, () -> new Tarefa("Fazer compras", "No mercado Assaí às 09h", "", Prioridade.ALTA));
        assertThrows(NullPointerException.class, () -> new Tarefa("Fazer compras", "No mercado Assaí às 09h", "12/09/2023", null));
    }

    @Test
    public void testAdicionarTarefa() {
        // Casos de teste para adicionarTarefa
    	
    	sistemaTarefas.adicionarTarefa(tarefaValida);

        List<Tarefa> todasTarefas = sistemaTarefas.listarTodasTarefas();
        
        assertEquals(1, todasTarefas.size()); 
        assertThrows(IllegalArgumentException.class, () -> sistemaTarefas.adicionarTarefa(new Tarefa("", "No mercado Assaí às 09h", "12/09/2023", Prioridade.ALTA)));
        assertThrows(IllegalArgumentException.class, () -> sistemaTarefas.adicionarTarefa(new Tarefa("Fazer compras", "", "12/09/2023", Prioridade.ALTA)));
        assertThrows(IllegalArgumentException.class, () -> sistemaTarefas.adicionarTarefa(new Tarefa("Fazer compras", "No mercado Assaí às 09h", "", Prioridade.ALTA)));
        assertThrows(NullPointerException.class, () -> sistemaTarefas.adicionarTarefa(new Tarefa("Fazer compras", "No mercado Assaí às 09h", "12/09/2023", null)));
        assertThrows(NullPointerException.class, () -> sistemaTarefas.adicionarTarefa(new Tarefa(null, "No mercado Assaí às 09h", "12/09/2023", Prioridade.ALTA)));
    }

    @Test
    public void testAtualizarTarefa() {
    	sistemaTarefas.adicionarTarefa(tarefaValida);
        Tarefa tarefaAtualizada = new Tarefa("Fazer compras", "No mercado Assaí às 09h", "12/10/2023", Prioridade.ALTA);
        sistemaTarefas.atualizarTarefa(0, tarefaAtualizada);

        List<Tarefa> todasTarefas = sistemaTarefas.listarTodasTarefas();
        
        assertEquals("12/10/2023", todasTarefas.get(0).getDataVencimento());
        assertThrows(IllegalArgumentException.class, () -> sistemaTarefas.atualizarTarefa(-1, tarefaAtualizada));
        assertThrows(NullPointerException.class, () -> sistemaTarefas.atualizarTarefa(0, new Tarefa(null, "No mercado Assaí às 09h", "12/09/2023", Prioridade.ALTA)));
    }
}
