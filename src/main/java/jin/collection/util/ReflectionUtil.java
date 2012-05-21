package jin.collection.util;

import java.lang.reflect.Field;

public class ReflectionUtil {

   public static Object valueOfField_On(String fieldName, Object anObject) throws SecurityException,
         NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
      Field idField = recursiveGetField(anObject, fieldName);
      idField.setAccessible(true);
      return idField.get(anObject);
   }

   public static void setPrivateField(Object instance, String fieldName, Object value) throws SecurityException,
         NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

      Field field = recursiveGetField(instance, fieldName);
      field.setAccessible(true);
      field.set(instance, value);
   }

   public static Object getPrivateField(Object instance, String fieldName) throws SecurityException,
         NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

      Field field = recursiveGetField(instance, fieldName);
      field.setAccessible(true);
      return field.get(instance);
   }

   private static Field recursiveGetField(Object instance, String fieldName) throws NoSuchFieldException {

      Class clazz = instance.getClass();
      while (!clazz.equals(Object.class)) {

         Field field = getDeclaredField(clazz, fieldName);
         if (field != null) {
            return field;
         }
         clazz = clazz.getSuperclass();
      }
      throw new NoSuchFieldException("Field " + fieldName + " not found on any class and superclass");
   }

   private static Field getDeclaredField(Class clazz, String fieldName) {
      Field field;
      try {
         field = clazz.getDeclaredField(fieldName);
         return field;
      } catch (NoSuchFieldException e) {
         return null;
      }
   }

}
