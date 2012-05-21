/*
 * Created on 19-mar-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package jin.collection.core;

public class NegateCriteria implements Criteria {

	private final Criteria filter;

	public NegateCriteria(Criteria filter) {
		this.filter = filter;
	}
	
	public boolean match(Object element) {
		return !filter.match(element);
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
