/*
 * Created on 19-mar-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package jin.collection.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 */
public class CollectionFactory {

   Map defaults = new HashMap();

   {
      defaults.put(List.class, ArrayList.class);
      defaults.put(Set.class, HashSet.class);
   }

   /**
    * Create a new Collection of the same type of the one passed as parameter If
    * the passed Collection is a class without a public constructor but that
    * implements indeed List or Set, returns an ArrayList or an HashSet
    * respectively
    */
   public static Collection newInstance(Collection collection) {
      if (collection instanceof Set) {
         return new HashSet();
      } else if (collection instanceof List) {
         return new ArrayList();
      }
      throw new RuntimeException("Unsupported Collection Type: " + collection.getClass().getName());
   }

   public static ArrayList asArrayList(Iterator iterator) {
      ArrayList col = new ArrayList();
      for (Iterator it = iterator; it.hasNext();) {
         col.add(it.next());
      }
      return col;
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
