
public class Tower {
    private static Stack<Integer> disks = new Stack<Integer>();

    public Tower(Stack<Integer> d) {
        disks = d;
    }

    public void add(int d) {
        if (!disks.isEmpty() && disks.peek() <= d) {
            System.out.println("Error placing disk " + d);
        } else {
            disks.push(d);
        }
    }

    public static void moveTopTo(Tower t) {
        int top = disks.pop();
        t.add(top);
    }

    public static void moveDisks(int n, Tower destination, Tower buffer) {
        if (n <= 0)
            return;
        moveDisks(n - 1, buffer, destination);
        moveTopTo(destination);
        moveDisks(n - 1, destination, buffer);
    }
}
