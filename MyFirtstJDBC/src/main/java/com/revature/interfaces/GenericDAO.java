package com.revature.interfaces;

public interface GenericDAO<T> {

	public void creat(T t);
	
	public T get(int id);
	
	public void update (T t);
	
	public void delete(T t);
}
