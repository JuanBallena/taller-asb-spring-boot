package com.taller.asb.manager;

public interface UniqueValueManager {

	public boolean valueExistsInDatabase(String column, Object value);
}
