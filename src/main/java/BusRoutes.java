import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.Assert;

import java.util.*;
import java.util.stream.*;

class BusRoutes {

    public static int numBusesToDestination(int[][] routes, int S, int T) {
        List<List<Integer>> routesList = new ArrayList<>();
        for (int[] route : routes) {
            List<Integer> routeList = Arrays.stream(route).mapToObj(i -> (Integer) i).collect(Collectors.toList());
            routesList.add(routeList);
        }

        System.out.println(routesList);
        int curRoute = -1;
        for (int i = 0; i < routesList.size(); i++) {
            if (routesList.get(i).contains(S)) {
                curRoute = i;
            }
        }

        if (curRoute == -1) {
            return -1;
        }

        return bestRoute(routesList, S, T, curRoute, 1, new HashSet<>());
    }


    public static int bestRoute(List<List<Integer>> routes, int start, int end, int curRoute, int curCount, Set<Integer> visited) {

        visited.add(curRoute);

        if (routes.get(curRoute).contains(end)) {
            return curCount;
        } else {
            for (int stop : routes.get(curRoute)) {
                for (List<Integer> route : routes) {
                    int routeNum = routes.indexOf(route);
                    if (!visited.contains(routeNum) && routes.get(routeNum).contains(stop)) {
                        return bestRoute(routes, start, end, routeNum, curCount + 1, visited);
                    }
                }
            }
        }

        return -1;
    }

    @Test
    public static void testMethod() {
        Assert.assertNotNull(null);
    }

//    public static void main(String[] args) {
//        JUnitCore.runClasses(BusRoutes.class);
//
//        int[][] routes = new int[][] {
//            {1, 2, 7},
//            {3, 6, 7}
//        };
//
//        System.out.println(numBusesToDestination(routes, 1, 6));
//    }
}
