import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

enum Prioridade {
    ALTA, MEDIA, BAIXA
}

public class TarefaTest {

    private Tarefa tarefa;

    @Before
    public void configurar() {
        tarefa = new Tarefa("Completar tarefa de Java", "Finalizar a tarefa de programação", "21/08/2023", Prioridade.ALTA);
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