/*
 * Created on Apr 22, 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package jin.collection.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Administrator
 * 
 *         To change the template for this generated type comment go to Window -
 *         Preferences - Java - Code Style - Code Templates
 */
public class Sort {

   private List listaCriteria = new ArrayList();
   private Comparator comp = new PropertyComparator();

   /**
    * @param dati
    */
   public void runOn(List dati) {

      Collections.sort(dati, comp);
   }

   /**
    * @param string
    * @param b
    */
   public Sort add(String propertyName, boolean ascending) {
      listaCriteria.add(new CompareCriteria(propertyName, ascending, false));
      return this;
   }

   /**
    * @param string
    * @return
    */
   public Sort addAsNumber(String propertyName) {
      return addAsNumber(propertyName, true);
   }

   public Sort addAsNumber(String propertyName, boolean ascending) {
      listaCriteria.add(new CompareCriteria(propertyName, ascending, true));
      return this;
   }

   /**
    * @param string
    * @return
    */
   public Sort add(String propertyName) {
      return add(propertyName, true);
   }

   private class PropertyComparator implements Comparator {

      public int compare(Object arg0, Object arg1) {

         for (Iterator iter = listaCriteria.iterator(); iter.hasNext();) {
            CompareCriteria criteria = (CompareCriteria) iter.next();
            int compareResult = criteria.compare(arg0, arg1);
            if (compareResult != 0) {
               return compareResult;
            }
         }
         return 0;
      }
   }

   private static class CompareCriteria {

      final String propertyName;
      final boolean ascending;
      final boolean asNumber;

      public CompareCriteria(String propertyName, boolean ascending, boolean asNumber) {
         this.propertyName = propertyName;
         this.ascending = ascending;
         this.asNumber = asNumber;
      }

      public int compare(Object arg0, Object arg1) {

         Comparable property0 = (Comparable) PropertyUtil.getProperty(arg0, propertyName);
         Comparable property1 = (Comparable) PropertyUtil.getProperty(arg1, propertyName);

         if (property0 == null || property1 == null) {
            return 0;
         }

         if (asNumber) {
            property0 = new Double(property0.toString());
            property1 = new Double(property1.toString());
         }
         int compareResult = property0.compareTo(property1);
         return ascending ? compareResult : -compareResult;
      }
   }

   public ArrayList runOn(Set artworks) {
      ArrayList result = new ArrayList(artworks);
      runOn(result);
      return result;
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
