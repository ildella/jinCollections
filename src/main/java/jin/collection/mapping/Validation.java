package jin.collection.mapping;

public abstract class Validation {

	private Object newValue;
	private boolean newValueDefined = false;

	private ValidationError validationError;
	
	abstract void validate(Object value);

	/**
	 * @return Returns the newValue.
	 */
	public Object getNewValue() {
		return newValue;
	}
	

	/**
	 * @param newValue The newValue to set.
	 */
	public void setNewValue(Object newValue) {
		newValueDefined = true;
		this.newValue = newValue;
	}

	/**
	 * @return Returns the newValueDefined.
	 */
	public boolean isNewValueDefined() {
		return newValueDefined;
	}
	
	void reset() {
		newValue = null;
		newValueDefined = false;
	}

	/**
	 * @return Returns the validationError.
	 */
	public ValidationError getValidationError() {
		return validationError;
	}
	

	/**
	 * @param validationError The validationError to set.
	 */
	public void setValidationError(ValidationError validationError) {
		this.validationError = validationError;
	}
	
}


/**
* Copyright 2007, Lorenzo Bolzani
* 
* The contents of this file are subject to the Mozilla Public
* License Version 1.1 (the "License"); you may not use this file
* except in compliance with the License. You may obtain a copy of
* the License at http://www.mozilla.org/MPL/MPL-1.1.html
**/
