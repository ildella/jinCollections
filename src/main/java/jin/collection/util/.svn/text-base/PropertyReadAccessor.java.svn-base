package jin.collection.util;

import jin.collection.core.ReadAccessor;

public class PropertyReadAccessor implements ReadAccessor {

   private final String property;

   public PropertyReadAccessor(String property) {
      this.property = property;
   }

   public Object getValue(Object element) {
      return PropertyUtil.getProperty(element, property);
   }

}
