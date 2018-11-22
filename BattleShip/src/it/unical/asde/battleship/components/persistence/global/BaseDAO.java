package it.unical.asde.battleship.components.persistence.global;

import java.util.List;

public interface BaseDAO<T, X> {

	public void save(T object);

	public T getById(X id);

	public void update(T object);

	public void delete(T object);

	public List<T> getAll();

	public void deleteById(X id);

}
