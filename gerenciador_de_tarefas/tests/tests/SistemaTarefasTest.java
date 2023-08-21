package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

enum Prioridade {
    ALTA, MEDIA, BAIXA
}

public class SistemaTarefasTest {
	private SistemaTarefas sistemaTarefas;

    @Before
    public void configurar() {
    	sistemaTarefas = new SistemaTarefas();
    }

    @Test
    public void testAdicionarTarefa() {
        Tarefa tarefa = new Tarefa("Jogar o lixo fora", "O caminhao passa as 09h", "21/08/2023", Prioridade.ALTA);
        sistemaTarefas.adicionarTarefa(tarefa);

        List<Tarefa> todasTarefas = sistemaTarefas.listarTodasTarefas();
        assertEquals(1, todasTarefas.size());
    }

    @Test
    public void testAtualizarTarefa() {
        Tarefa tarefa = new Tarefa("Jogar o lixo fora", "O caminhao passa as 10h", "21/08/2023", Prioridade.ALTA);
        sistemaTarefas.adicionarTarefa(tarefa);

        Tarefa tarefaAtualizada = new Tarefa("Tarefa Atualizada", "Descrição atualizada", "21/08/2023", Prioridade.BAIXA);
        sistemaTarefas.atualizarTarefa(0, tarefaAtualizada);

        List<Tarefa> todasTarefas = sistemaTarefas.listarTodasTarefas();
        assertEquals("Tarefa Atualizada", todasTarefas.get(0).getTitulo());
        assertEquals("Descrição atualizada", todasTarefas.get(0).getDescricao());
        assertEquals("2023-09-15", todasTarefas.get(0).getDataVencimento());
        assertEquals(Prioridade.BAIXA, todasTarefas.get(0).getPrioridade());
    }

    @Test
    public void testRemoverTarefa() {
        Tarefa tarefa = new Tarefa("Jogar o lixo fora", "O caminhao passa as 09h", "21/08/2023", Prioridade.ALTA);
        sistemaTarefas.adicionarTarefa(tarefa);

        sistemaTarefas.removerTarefa(0);

        List<Tarefa> todasTarefas = sistemaTarefas.listarTodasTarefas();
        assertEquals(0, todasTarefas.size());
    }
}
