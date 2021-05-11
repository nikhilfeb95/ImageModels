package imagemodel.patterngenerator;

import java.util.Comparator;
import java.util.regex.Pattern;

/**
 * A helper class used to compare strings for the DMC values.
 */
class DmcValuesComparator implements Comparator<String> {
  @Override
  public int compare(String o1, String o2) {
    if (o1 == null && o2 == null) {
      return 0;
    }
    if (o1 == null && o2 != null) {
      return 1;
    }
    if (o1 != null && o2 == null) {
      return -1;
    }
    Integer first = -1;
    Integer second = -1;
    String regex = "[0-9]+[\\.]?[0-9]*";
    // check if both can be parsable.
    if (Pattern.matches(regex, o1)) {
      first = Integer.parseInt(o1);
    }
    if (Pattern.matches(regex, o2)) {
      second = Integer.parseInt(o2);
    }
    if (first != -1 && second != -1) {
      return first.compareTo(second);
    } else if (first != -1 && second == -1) {
      return 1;
    } else if (first == -1 && second != -1) {
      return -1;
    }
    return o1.compareTo(o2);
  }
}
