package jin.collection.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class WrappedGroupBy {

   private GroupBy groupBy;
   private final Class<? extends MapEntry> klass;

   public WrappedGroupBy(String property) {
      this(property, StringMapEntry.class);
   }

   public WrappedGroupBy(String property, Class<? extends MapEntry> klass) {
      groupBy = new GroupBy(property);
      this.klass = klass;
   }

   public List runOn(Collection list) {
      Map grouped = groupBy.runOn(list);
      Collection wrapped = new Wrap(klass).runOn(grouped);
      List sorted = (List) wrapped;
      new Sort().add(MapEntry.KEY, true).runOn(sorted);
      return sorted;
   }

}
