package jin.collection.util;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import jin.collection.core.CollectionFactory;
import jin.collection.core.Iter;
import jin.collection.core.ReadAccessor;

public class Wrap {

   private final Class clazz;
   private final Class paramClass;

   public Wrap(Class clazz) {
      this(clazz, null);
   }

   public Wrap(Class entityClass, Class paramClass) {
      clazz = entityClass;
      this.paramClass = paramClass;
   }

   public Collection runOn(Collection list) {

      if (list.isEmpty()) {
         return CollectionFactory.newInstance(list);
      }

      Class paramType = paramClass;
      if (paramType == null) {
         paramType = list.iterator().next().getClass();
      }
      final Constructor c;
      try {
         c = clazz.getConstructor(new Class[] { paramType });
      } catch (Exception e) {
         throw new RuntimeException(e.toString());
      }

      return Iter.collect(list, new ReadAccessor() {
         public Object getValue(Object element) {
            try {
               return c.newInstance(new Object[] { element });
            } catch (Exception e) {
               throw new RuntimeException(e.toString(), e);
            }
         }
      });
   }

   public Collection runOn(Map<? extends Object, ? extends Collection> result) {

      Set keySet = result.keySet();

      if (keySet.isEmpty()) {
         return new ArrayList();
      }

      Class<? extends Object> keyType = keySet.iterator().next().getClass();
      final Constructor c;
      try {
         c = clazz.getConstructor(keyType, Collection.class);
      } catch (Exception e) {
         throw new RuntimeException(e.toString());
      }

      return Iter.collect(result.entrySet(), false, new ReadAccessor() {
         public Object getValue(Object element) {
            try {
               Entry<? extends Object, ? extends Collection> entry = (Entry) element;
               return c.newInstance(entry.getKey(), entry.getValue());
            } catch (Exception e) {
               throw new RuntimeException(e.toString(), e);
            }
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
