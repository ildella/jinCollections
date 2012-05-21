package jin.collection.core;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import jin.collection.util.ByMethodCriteria;

/*
 * Created on 19-mar-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * Fornisce le operazioni di iterazione su una collection
 * 
 * Nonostante questa sia la classe principale del package non dovrebbe quasi mai
 * essere usata direttamente, per lo meno non con delle classi anonime. In
 * pratica bisognerebbe sempre nasconderla dietro a classi tipo Sum o GroupBy o
 * generare i criteria con classi tipo NumberCriteriaFactory o
 * PropertyCriteriaFactory
 * 
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class Iter {

	/**
	 * This one break convention that the returned Collection is of the same
	 * concrete type of the one passed as a parameter cause it is not possible to
	 * deduce the Collection type from its iterator
	 * 
	 * @param iter
	 * @param op
	 */
	public static void forEach(final Iterator iter, final Operation op) {
		Collection result = new ArrayList();

		Iter.chain(iter, result, new ChainedOperation() {
			public Object execute(final Object element, final Object currValue) {
				op.execute(element);
				return null;
			}
		});
	}

	public static Collection collect(final Iterator iter, final ReadAccessor accessor) {
		final Collection result = new ArrayList();
		Iter.forEach(iter, new Operation() {
			public void execute(final Object element) {
				result.add(accessor.getValue(element));
			}
		});
		return result;
	}

	public static Collection collectNotNull(final Iterator iter, final ReadAccessor accessor) {
		final Collection result = new ArrayList();
		Iter.forEach(iter, new Operation() {
			public void execute(final Object element) {
				Object value = accessor.getValue(element);
				if (value != null) {
					result.add(value);
				}
			}
		});
		return result;
	}

	public static void forEach(final Collection elements, final Operation op) {

		Collection result = CollectionFactory.newInstance(elements);
		forEach(elements, result, op);
	}

	public static void forEach(final Collection elements, final Collection result, final Operation op) {
		Iter.chain(elements, result, new ChainedOperation() {
			public Object execute(final Object element, final Object currValue) {
				op.execute(element);
				return null;
			}
		});
	}

	public static Collection extract(final Collection elements, final Criteria includeCriteria) {
		return extract(elements, includeCriteria, false);
	}

	public static Collection extract(final Collection elements, final Criteria includeCriteria,
	      final boolean guessListType) {

		final Collection result = initResultList(elements, guessListType);

		return (Collection) Iter.chain(elements, result, new ChainedOperation() {
			public Object execute(final Object element, final Object currValue) {

				if (includeCriteria.match(element)) {
					Collection collection = (Collection) currValue;
					collection.add(element);
				}
				return currValue;
			}
		});
	}

	public static Collection filter(final Collection elements, final Criteria excludeCriteria) {
		return extract(elements, new NegateCriteria(excludeCriteria));
	}

	/**
	 * Simile al forEach, ma durante tutta l'iterazione e' disponibile un
	 * elemento (currValue) che viene passato da un'iterazione a quella
	 * successiva.
	 */
	public static Object chain(final Collection elements, final Object firstValue, final ChainedOperation cOp) {

		return chain(elements.iterator(), firstValue, cOp);
	}

	public static Object chain(final Iterator iterator, final Object firstValue, final ChainedOperation chainedOp) {
		Object currValue = firstValue;
		for (Iterator iter = iterator; iter.hasNext();) {
			Object element = iter.next();
			currValue = chainedOp.execute(element, currValue);
		}
		return currValue;
	}

	/**
	 * Caso particolare di forEach in cui si vuole produrre una nuova lista
	 * basata sugli elementi della lista passata (E' giusto il nome rispetto a
	 * SmallTalk?)
	 * 
	 * @param <O>
	 * @param <I>
	 */
	public static <I, O> Collection<O> collect(final Collection<I> elements, final ReadAccessor<I, O> accessor) {
		return collect(elements, true, accessor);
	}

	public static <I, O> Collection<O> collect(final Collection<I> elements, final boolean guessListType,
	      final ReadAccessor accessor) {
		final Collection result = initResultList(elements, guessListType);

		Iter.forEach(elements, result, new Operation() {
			public void execute(final Object element) {
				result.add(accessor.getValue(element));
			}
		});
		return result;
	}

	private static Collection initResultList(final Collection elements, final boolean guessListType) {
		final Collection result;
		if (guessListType) {
			result = CollectionFactory.newInstance(elements);
		} else {
			result = new ArrayList();
		}
		return result;
	}

	/**
	 * Used when from a collection it is needed to extract more element from a
	 * single one of the original collection Ex: collectMany(artists, new
	 * ArtistToPicturesAccessor()). Each artists has_many pictures.
	 * 
	 * @param guessListType
	 */
	public static Collection collectMany(final Collection elements, final HasManyReadAccessor accessor,
	      final boolean guessListType) {
		final Collection result = initResultList(elements, guessListType);

		Iter.forEach(elements, result, new Operation() {
			public void execute(final Object element) {
				result.addAll(accessor.getValue(element));
			}
		});
		return result;
	}

	public static Collection extract(final Object[] elements, final Criteria criteria) {
		return Iter.extract(Arrays.asList(elements), criteria);
	}

	public static Collection extract(final Object[] elements, final String method, final Object parameter) {
		return Iter.extract(Arrays.asList(elements), method, parameter);
	}

	public static Collection extract(final Collection elements, final String method, final Object parameter) {
		return Iter.extract(elements, new ByMethodCriteria(method, parameter));
	}

	public static Collection collect(final Object[] parameters, final ReadAccessor accessor) {
		return collect(Arrays.asList(parameters), accessor);
	}

	public static <T> T lookUp(final Collection<T> elements, final Criteria<T> criteria) {
		T result = read(elements, criteria);
		if (result != null) {
			return result;
		}
		throw new IllegalStateException(MessageFormat.format("No element match criteria. {0}", criteria));
	}

	public static <T> T read(final Collection<T> elements, final Criteria<T> criteria) {
		return read(elements, criteria, false);
	}

	public static <T> T read(final Collection<T> elements, final Criteria<T> criteria, final boolean guessListType) {
		Collection results = extract(elements, criteria, guessListType);
		if (results.isEmpty()) {
			return null;
		}
		if (results.size() > 1) {
			throw new IllegalStateException("Lookup has found more than one unique result. " + elements);
		}
		return (T) results.iterator().next();
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
