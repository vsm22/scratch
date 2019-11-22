import java.util.*;

public class PascalsTriangle {

    public static List<List<Integer>> pascals(int n) {
        List<List<Integer>> result = new ArrayList<>();

        result.add(Arrays.asList(1));

        for (int rowNum = 1; rowNum < n; rowNum++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> rowAbove = result.get(rowNum - 1);
            row.add(1);
            for (int i = 1; i <= rowNum - 1; i++) {
                int aboveLeft = rowAbove.get(i - 1);
                int aboveRight = rowAbove.get(i);
                row.add(aboveLeft + aboveRight);
            }
            row.add(1);
            result.add(row);
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = pascals(5);

        int maxOffset = result.size() + 1;

        for (int i = 0; i < result.size(); i++) {
            int curOffset = maxOffset - i;
            System.out.print("\n");
            for (int j = 0; j < curOffset; j++) {
                System.out.print(" ");
            }
            System.out.print("[" + result.get(i).get(0));
            for (int j = 1; j < result.get(i).size(); j++) {
                System.out.print("," + result.get(i).get(j));
            }
            System.out.println("],");
        }
    }
}
