package br.ufrpe.siga.dado.repositorio;

import br.ufrpe.siga.dado.excecao.RegistroNaoEncontradoException;
import br.ufrpe.siga.negocio.entidade.Entidade;

public abstract class RepositorioArray<E extends Entidade> {
	
	protected Object[] elementos= new Object[100];
	private int idx= 0;
	private int id= 1;
	
	@SuppressWarnings("unchecked")
	public E buscar(int id) throws RegistroNaoEncontradoException {
		E elemento= null;
		for (int i = 0; i < elementos.length; i++) {
			if ( id == ( (E) elementos[i] ).getId() ) {
				elemento= (E) elementos[i];
				break;
			}
		}
		return elemento;
	}

	public Object[] listar() {
		return elementos;
	}

	public void inserir(E e) {
		e.setId(id++);
		elementos[idx++]= e;
	}

	@SuppressWarnings("unchecked")
	public void alterar(E e) throws RegistroNaoEncontradoException {
		for (int i = 0; i < elementos.length; i++) {
			if ( elementos[i] != null && e.getId() == ( (E) elementos[i] ).getId()) {
				elementos[i]= e;
				break;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void apagar(int id) throws RegistroNaoEncontradoException {
		if ( buscar(id) == null ) {
			for (int i = 0; i < elementos.length; i++) {
				if ( id == ( (E) elementos[i] ).getId() ) {
					elementos[i]= null;
				}
			}
		} else {
			throw new RegistroNaoEncontradoException();
		}
	}
	
}
