/*
 * Created on 19-mar-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package jin.collection.util;

import java.util.Collection;

import jin.collection.core.ChainedOperation;
import jin.collection.core.Iter;
import jin.collection.core.ReadAccessor;

/**
 * Classe per sommare dei valori estratti da una collection
 * 
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class Sum {

   private final Collection elements;

   public Sum(final Collection elements) {
      this.elements = elements;
   }

   public Double getValue() {

      return getValue(new ReadAccessor() {
         public Object getValue(final Object element) {
            return element;
         }
      });
   }

   public Double getValue(final String property) {

      return getValue(new ReadAccessor() {
         public Object getValue(final Object element) {
            return PropertyUtil.getProperty(element, property);
         }
      });
   }

   public Double getValue(final ReadAccessor accessor) {

      return (Double) Iter.chain(elements, new Double(0), new SumOperation(accessor));
   }

   private final class SumOperation implements ChainedOperation {

      private final ReadAccessor accessor;

      private SumOperation(final ReadAccessor accessor) {
         this.accessor = accessor;
      }

      public Object execute(final Object element, final Object currValue) {
         final Double totale = (Double) currValue;
         final Number newNumber = (Number) accessor.getValue(element);
         return new Double(totale.doubleValue() + newNumber.doubleValue());
      }
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
