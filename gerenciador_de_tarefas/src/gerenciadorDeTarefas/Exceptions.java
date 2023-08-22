package gerenciadorDeTarefas;

public class Exceptions {

	public Exceptions() {}

	public void verifier(String object) {
		if (object == null) {
			throw new NullPointerException();
		} else if ("".equals(object.trim())) {
			throw new IllegalArgumentException();
		}
	}

	public void verifier(Object object) {
		if (object == null) {
			throw new NullPointerException();
		}
	}
}
