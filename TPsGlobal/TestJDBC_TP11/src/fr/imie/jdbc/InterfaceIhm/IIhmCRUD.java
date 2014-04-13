package fr.imie.jdbc.InterfaceIhm;

import java.util.List;

import fr.imie.jdbc.Exception.PersistanceException;

public interface IIhmCRUD {
//public interface InterfaceIhmCRUD<T> {
	public abstract void search() throws PersistanceException;
	public abstract void add() throws PersistanceException;
	public abstract void update() throws PersistanceException;
	public abstract void delete() throws PersistanceException;

//	public abstract List<T> search(T varDTO) throws PersistanceException;
//	public abstract T add(T varDTO) throws PersistanceException;
//	public abstract T update(T varDTO) throws PersistanceException;
//	public abstract void delete() throws PersistanceException;

}
