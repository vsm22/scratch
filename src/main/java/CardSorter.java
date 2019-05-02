import java.util.*;

public class CardSorter {

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> mapFreqByVal = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Insert into val:freq map
        for (int num : nums) {
            mapFreqByVal.merge(num, 1, (v1, v2) -> v1 + v2);
        }

        for (Map.Entry<Integer, Integer> entry : mapFreqByVal.entrySet()) {
            heap.offer(entry);
        }

        List<Integer> result = new ArrayList<>();
        while (result.size() < k) {
            Map.Entry<Integer,Integer> entry = heap.poll();
            for (int i = 0; i < Math.min(k - result.size(), entry.getValue() - result.size()); i++) {
                result.add(entry.getKey());
            }
        }

        return result;
    }



    public boolean isSortable(int[] cards, int numCards) {

        boolean isPossible = true;
        List<List<Integer>> ordered = new ArrayList<>();
        Arrays.sort(cards);

        for (int card : cards) {
            boolean doesContainDeck = false;
            for (List<Integer> list : ordered) {
                if (!doesContainDeck && list.size() < numCards && list.get(list.size() - 1) == card - 1) {
                    doesContainDeck = true;
                    list.add(card);
                }
            }
            if (!doesContainDeck) {
                ordered.add(new ArrayList(Arrays.asList(card)));
            }
        }


        for (List<Integer> list : ordered) {
            if (list.size() != numCards) {
                isPossible = false;
                break;
            }
        }

        return isPossible;


        /*
        {1, 2, 4, 9, 3, 2, 3, 8, 10};
        {1, 2, 2, 3, 3, 4, 8, 9, 10};

        1: 1 2 3
        2: 2 3 4
        8: 8 9 10

        1: 2 2
        2: 3 3
        4:
        9:
        3:
        8:
        10:

         */
    }
}
