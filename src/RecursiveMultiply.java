public class RecursiveMultiply {
    // multiply two POSITIVE integer, a and b, without using * or / operations
    int total = 0;

    public int multiply(int a, int b) {
        if (a == 0 || b == 0)
            return 0;
        if (a < 0 || b < 0) {
            System.out.print("Numbers cannot be negative: ");
            return -1;
        }
        int smaller = a < b ? a : b;
        int bigger = a < b ? b : a;

        return recursiveMultiply(bigger, smaller);
    }

    // Gives O(log N) time complexity
    public int recursiveMultiply(int a, int b) {
        if (b == 1)
            return a;
        int s = b >> 1; // divide by 2
        int halfTotal = recursiveMultiply(a, s);
        if (b % 2 == 0) {
            return halfTotal + halfTotal;
        } else {
            return halfTotal + halfTotal + a;
        }
    }

    public static void main(String[] args) {
        RecursiveMultiply rm = new RecursiveMultiply();
        System.out.println(rm.multiply(5, 4));
        System.out.println(rm.multiply(5, 3));
        System.out.println(rm.multiply(5, 0));
    }
}