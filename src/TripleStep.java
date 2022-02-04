import java.util.HashMap;

public class TripleStep {
    public int countWays(int n){
        HashMap<Integer, Integer> map = new HashMap<>();
        return countWays(n, map);
    }

    public int countWays(int n, HashMap<Integer, Integer> map){
        if(n < 0) return 0;
        if(n == 0) return 1;

        if(map.containsKey(n)) return map.get(n);

        int value = countWays(n-1) + countWays(n-2) + countWays(n-3);
        map.put(n, value);
        return value;
    }

    public static void main(String[] args) {
        TripleStep ts = new TripleStep();
        int totalWays = ts.countWays(4);
        System.out.println(totalWays);
    }
}
