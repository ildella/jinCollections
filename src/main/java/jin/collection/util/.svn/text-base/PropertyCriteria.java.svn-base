package jin.collection.util;

import jin.collection.core.Criteria;

/*
 * Created on 19-mar-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * Factory per criteri di confronto su property
 */
public class PropertyCriteria {

   public static Criteria isNull(final String property) {

      return new Criteria() {
         public boolean match(final Object element) {
            return PropertyUtil.getProperty(element, property) == null;
         }
      };
   }

   public static Criteria isEqual(final String property, final Object object) {

      return new Criteria() {
         public boolean match(final Object element) {
            final Object propValue = PropertyUtil.getProperty(element, property);
            if (propValue == null) {
               return object == null;
            }
            return propValue.equals(object);
         }
      };
   }

   public static Criteria isTrue(final String property) {
      return new Criteria() {

         public boolean match(final Object element) {
            final Boolean propValue = (Boolean) PropertyUtil.getProperty(element, property);
            return Boolean.TRUE.equals(propValue);
         }
      };
   }

   /**
    * This criteria works only for String properties and is case insensitive
    */
   public static Criteria contains(final String property, final String value) {
      return new Criteria() {

         public boolean match(final Object element) {
            final String propValue = PropertyUtil.nullSafeGet(element, property);
            return propValue.toLowerCase().contains(value.toLowerCase());
         }
      };
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
