/*
 * Created on 19-mar-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package jin.collection.util;

import jin.collection.core.Criteria;

/**
 * Factory per criteri di confronto tra numeri
 */
public class NumberCriteriaFactory {

	public static Criteria greaterThan(final Double value) {

		return new Criteria() {
			public boolean match(Object element) {
				return ((Number)element).doubleValue() > value.doubleValue();
			}
		};
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
