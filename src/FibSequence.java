import java.util.HashMap;

public class FibSequence {
    HashMap<Integer, Integer> map = new HashMap<>();
    public int getFibonacciNumber(int n){
        if(n <= 1) return 1;

        if(map.containsKey(n)) return map.get(n);

        int fibN = getFibonacciNumber(n-1) + getFibonacciNumber(n-2);
        map.put(n, fibN);
        return fibN;
    }

    public static void main(String[] args) {
        FibSequence fs = new FibSequence();
        System.out.println(fs.getFibonacciNumber(140));
    }
}
