package jin.collection.util;

import java.util.Collection;

import jin.collection.core.Criteria;
import jin.collection.core.Iter;
import jin.collection.core.ReadAccessor;

public class ByMethodCriteria implements Criteria {

   private final String methodName;
   private Object[] parameters;

   public ByMethodCriteria(String methodName) {
      this(methodName, null);
   }

   public ByMethodCriteria(String methodName, Object... parameters) {
      this.methodName = methodName;
      this.parameters = parameters;
   }

   public boolean match(Object element) {
      Collection types = Iter.collect(parameters, new ReadAccessor() {
         public Object getValue(Object element) {
            return element.getClass();
         }
      });
      Class[] parametersTypes = (Class[]) types.toArray(new Class[types.size()]);
      try {
         return (Boolean) element.getClass().getMethod(methodName, parametersTypes).invoke(element, parameters);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }
}
