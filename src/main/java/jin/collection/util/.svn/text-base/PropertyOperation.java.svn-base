package jin.collection.util;

import jin.collection.core.Operation;

public class PropertyOperation implements Operation {

   private final String property;

   public PropertyOperation(String property) {
      this.property = property;

   }

   public void execute(Object element) {
      try {
         element.getClass().getDeclaredMethod(property, null).invoke(element, null);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }

   }

}
