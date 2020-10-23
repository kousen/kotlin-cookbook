import java.util.*;
import java.util.stream.Collectors;

public class GroupByDemo {
    public Collection<String> doSomethingStrangeWithCollection(Collection<String> collection) {
        Map<Integer, List<String>> groupsByLength = collection.stream()
                .collect(Collectors.groupingBy(String::length));

        int maximumSizeOfGroup = groupsByLength.values().stream()
                .mapToInt(List::size)
                .max().orElse(0);

        return groupsByLength.values().stream()
                .filter(group -> group.size() == maximumSizeOfGroup)
                .findFirst().orElse(null);
    }

}
