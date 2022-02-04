public class ModularExponentiation {
    // goal is to find value of (x^n) % m using recursion
    // we can use simple associativity rule here
    // Eg, (a * b) % m = [(a % m) * (b % m)] % m
    // Thus, (x^n) % m = [(x % m) * (x % m) * ...... * (x % m)] % m

    // We can further simplify that to:
    // (x ^ n) % m = {[(x^(n/2)) % m] * [(x^(n/2)) % m]} % m if n is even
    //             = {(x % m) * [x^(n-1) % m]} % m if n is odd
    //             = 1 if n is 0

    public int findModulo(int x, int n, int m){
        if(n == 0) return 1;

        if(n % 2 == 0){
            int y = findModulo(x, n/2, m);
            return (y * y) % m;
        }

        return ((x % m) * findModulo(x, n-1, m)) % m;
    }

    public static void main(String[] args) {
        ModularExponentiation me = new ModularExponentiation();
        int val = me.findModulo(2, 4, 3);
        System.out.println(val);
    }
}
