package jin.collection.util;

import java.util.Collection;

public class StringMapEntry extends MapEntry {

   public StringMapEntry(String key, Collection values) {
      super(key, values);
   }

   @Override
   public String getKey() {
      return (String) super.getKey();
   }

}
