package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gerenciadorDeTarefas.Prioridade;
import gerenciadorDeTarefas.Tarefa;

public class TarefaTest {

    private Tarefa tarefa;

    @BeforeEach
    public void configurar() {
        tarefa = new Tarefa("Completar tarefa de Java", "Finalizar a tarefa de programação", "21/08/2023", Prioridade.ALTA);
    }
    
    @Test
	public void testBoletoNuloVazio() {
    	assertThrows(IllegalArgumentException.class, () -> new Tarefa("", "Finalizar a tarefa de programação", "21/08/2023", Prioridade.ALTA));
    	assertThrows(IllegalArgumentException.class, () -> new Tarefa("Completar tarefa de Java", "", "21/08/2023", Prioridade.ALTA));
    	
		assertThrows(NullPointerException.class, () -> new Tarefa(null, "Finalizar a tarefa de programação", "21/08/2023", Prioridade.ALTA));
		assertThrows(NullPointerException.class, () -> new Tarefa("Completar tarefa de Java", null, "21/08/2023", Prioridade.ALTA));
		assertThrows(NullPointerException.class, () -> new Tarefa("Completar tarefa de Java", "Finalizar a tarefa de programação", null, Prioridade.ALTA));
		assertThrows(NullPointerException.class, () -> new Tarefa("Completar tarefa de Java", "Finalizar a tarefa de programação", "21/08/2023", null));
	}

    @Test
    public void testGetters() {
        assertEquals("Completar tarefa de Java", tarefa.getTitulo());
        assertEquals("Finalizar a tarefa de programação", tarefa.getDescricao());
        assertEquals("21/08/2023", tarefa.getDataVencimento());
        assertEquals(Prioridade.ALTA, tarefa.getPrioridade());
    }

    @Test
    public void testSetters() {
        tarefa.setTitulo("Tarefa Atualizada");
        tarefa.setDescricao("Descrição atualizada");
        tarefa.setDataVencimento("21/08/2023");
        tarefa.setPrioridade(Prioridade.BAIXA);

        assertEquals("Tarefa Atualizada", tarefa.getTitulo());
        assertEquals("Descrição atualizada", tarefa.getDescricao());
        assertEquals("21/08/2023", tarefa.getDataVencimento());
        assertEquals(Prioridade.BAIXA, tarefa.getPrioridade());
    }
}