package jin.collection.mapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jin.collection.core.Iter;
import jin.collection.core.Operation;
import jin.collection.util.PropertyUtil;



public class Mapping {

	private List mappingDataList = new ArrayList();
	private Collection validationErrors = new ArrayList();
	
	public void add(String fromProperty, String toProperty) {
		add(fromProperty, toProperty, null);
	}

	public void add(String fromProperty, String toProperty, final Object defaultValue) {
		add(fromProperty, toProperty, new Validation() {
			void validate(Object value) {
				if (value == null)
					setNewValue(defaultValue);
			}
		});
	}

	public void add(String fromProperty, String toProperty, Validation validation) {
		
		MappingData mappingData = new MappingData();
		mappingData.fromProperty = fromProperty;
		mappingData.toProperty = toProperty;
		//mappingData.defaultValue = defaultValue;
		mappingData.validation = validation;
		mappingDataList.add(mappingData);
	}


	public void runOn(final Object from, final Object to) {
		
		Iter.forEach(mappingDataList , new Operation() {
			public void execute(Object element) {
				MappingData mappingData = (MappingData) element;
				Object fromValue = PropertyUtil.getProperty(from, mappingData.fromProperty);
				
				if (mappingData.validation != null) {
					
					mappingData.validation.validate(fromValue);
					
					if (mappingData.validation.getValidationError() != null) {
						validationErrors.add(mappingData.validation.getValidationError());
					}
					else {
						if (mappingData.validation.isNewValueDefined()) {
							fromValue = mappingData.validation.getNewValue();
						}
						PropertyUtil.setProperty(to, mappingData.toProperty, fromValue);
					}
					mappingData.validation.reset();
				}
				else {
					PropertyUtil.setProperty(to, mappingData.toProperty, fromValue);
				}
			}
		});
	}

	private static class MappingData {
		public Validation validation;
		String fromProperty;
		String toProperty;
		//Object defaultValue;
	}

	public Collection getValidationErrors() {
		return validationErrors;
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
