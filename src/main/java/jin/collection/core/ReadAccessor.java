package jin.collection.core;

/**
 * Una classe che permette di accedere ad una proprieta' di un'altra classe,
 * eventualmente elaborandone il valore.
 * 
 * @param <E>
 */
public interface ReadAccessor<I, O> {
	/**
	 * Ritorna una property presente su element o un valore ricavato in base alle
	 * caratteristiche di element
	 */
	O getValue(I element);
}

/**
 * Copyright 2007, Lorenzo Bolzani
 * 
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/MPL-1.1.html
 **/
