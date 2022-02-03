public class FactorialRecursion {

    public int factorial(int n) {
        if (n == 0)
            return 1;

        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        FactorialRecursion fr = new FactorialRecursion();
        int f5 = fr.factorial(5);
        System.out.println(f5);
    }

}
