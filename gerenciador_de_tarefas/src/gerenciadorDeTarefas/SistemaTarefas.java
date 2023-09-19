package gerenciadorDeTarefas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import gerenciadorDeTarefas.Exceptions;

public class SistemaTarefas {
    private List<Tarefa> tarefas;
    private Exceptions exceptions;

    public SistemaTarefas() {
    	this.exceptions = new Exceptions();
        tarefas = new ArrayList<>();
    }

    public void adicionarTarefa(Tarefa tarefa) {
    	exceptions.verifier(tarefa);
    	
        tarefas.add(tarefa);
    }

    public void atualizarTarefa(int indice, Tarefa tarefaAtualizada) {
    	exceptions.verifier(indice);
    	exceptions.verifier(tarefaAtualizada);
    	
        tarefas.set(indice, tarefaAtualizada);
    }

    public void removerTarefa(int indice) {
        tarefas.remove(indice);
    }

    public List<Tarefa> listarTodasTarefas() {
        // Ordena as tarefas por data de vencimento e, em seguida, por prioridade
        Collections.sort(tarefas, Comparator.comparing(Tarefa::getDataVencimento).thenComparing(Tarefa::getPrioridade));
        return tarefas;
    }
}