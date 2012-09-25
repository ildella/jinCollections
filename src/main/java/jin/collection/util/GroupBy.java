package jin.collection.util;

import java.util.*;

import jin.collection.core.ChainedOperation;
import jin.collection.core.Iter;
import jin.collection.core.ReadAccessor;

/**
 * Effetta dei raggruppamenti (in una Map) degli elementi passati come
 * collection
 * 
 */
public class GroupBy {

	private final ReadAccessor accessor;

	public GroupBy(final String property) {
		this(new ReadAccessor() {
			public Object getValue(final Object element) {
				return PropertyUtil.getProperty(element, property);
			}
		});
	}

	public GroupBy(final ReadAccessor accessor) {
		this.accessor = accessor;
	}

	/**
	 * Groups a new Map of Lists based on the property
	 */
	public Map runOn(final Collection elements) {

		return (Map) Iter.chain(elements, new HashMap(), new ChainedOperation() {
			public Object execute(final Object element, final Object currValue) {

				final Map map = (Map) currValue;

				final Object propertyValue = accessor.getValue(element);
				List propertyList = (List) map.get(propertyValue);
				if (propertyList == null) {
					propertyList = new ArrayList();
					map.put(propertyValue, propertyList);
				}
				propertyList.add(element);
				return map;
			}
		});
	}

	/**
	 * Groups a new Map of Objects based on the property.
	 * 
	 * @throws #{@link IllegalArgumentException} there is more than one value for
	 *         the given property
	 */
	public Map<String, Map> map(final Collection elements) {
		return map(elements, false);
	}

	public Map<String, Map> map(final Collection elements, final boolean ignoreDuplicate) {
		return (Map) Iter.chain(elements, new HashMap(), new ChainedOperation() {
			public Object execute(final Object element, final Object currValue) {

				final Map map = (Map) currValue;

				final Object keyValue = accessor.getValue(element);
				final Object value = map.get(keyValue);
				if (value == null) {
					map.put(keyValue, element);
				} else if (!ignoreDuplicate) {
					throw new IllegalArgumentException("The list contains more than one object with key: " + keyValue);
				}
				return map;
			}
		});
	}

}

/**
 * Copyright 2007, Lorenzo Bolzani
 * 
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/MPL-1.1.html
 **/
