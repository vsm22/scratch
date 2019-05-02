import java.util.Arrays;
import java.util.List;

public class BinarySearch {

    public static <T extends Comparable> int binarySearch(List<T> list, T item) {
        int l = 0;
        int r = list.size() - 1;

        while (l <= r) {
            int mid = (l + r) / 2;

            if (list.get(mid).equals(item)) {
                return mid;
            } else if (item.compareTo(list.get(mid)) < 0) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        List<Integer> testL = Arrays.asList(1, 3, 4, 6, 8, 8, 12, 18, 20);
        System.out.println(binarySearch(testL, 21));
    }
}
