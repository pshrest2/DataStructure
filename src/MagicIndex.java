public class MagicIndex {

    public boolean findMagicIndex(int[] array) {
        return findMagicIndex(0, array.length, array);
    }

    public boolean findMagicIndexNonDistinct(int[] array) {
        return findMagicIndexNonDistinct(0, array.length, array);
    }

    // when elements are distinct
    public boolean findMagicIndex(int l, int r, int[] array) {
        if (l > r)
            return false;

        int mid = (l + r) / 2;

        if (array[mid] == mid)
            return true;

        if (array[mid] < mid)
            return findMagicIndex(mid + 1, r, array);
        else
            return findMagicIndex(0, mid - 1, array);
    }

    // when elements are not distinct
    public boolean findMagicIndexNonDistinct(int l, int r, int[] array) {
        if (l > r)
            return false;

        int mid = (l + r) / 2;

        if (array[mid] == mid)
            return true;

        // search in left sub array
        int leftIndex = Math.min(mid - 1, array[mid]);
        if (findMagicIndexNonDistinct(0, leftIndex, array))
            return true;

        // search in right sub array
        int rightIndex = Math.max(mid + 1, array[mid]);
        return findMagicIndexNonDistinct(rightIndex, r, array);
    }

    public static void main(String[] args) {
        MagicIndex mi = new MagicIndex();

        // distinct
        // System.out.println(mi.findMagicIndex(new int[] { -5, 0, 1, 2, 3, 5, 7, 10
        // })); // true
        // System.out.println(mi.findMagicIndex(new int[] { 0 })); // true
        // System.out.println(mi.findMagicIndex(new int[] { 1 })); // false
        // System.out.println(mi.findMagicIndex(new int[] { 1, 2, 3 })); // false

        // sorted array with non-distinct elements
        int[] array = new int[] { -5, 0, 1, 2, 5, 5, 7, 10 };
        System.out.println(mi.findMagicIndexNonDistinct(array));
    }
}
