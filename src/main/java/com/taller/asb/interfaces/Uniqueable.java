package com.taller.asb.interfaces;

public interface Uniqueable {

	public boolean uniqueValueOnCreate(String field, Object value);
	public boolean uniqueValueOnUpdate(String field, Object value, Integer id);
}
