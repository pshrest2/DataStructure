public class UnionFind {
    // keeps track of total elements in union find
    private int size;

    // keeps track of size of each component/set
    private int[] sz;

    // keeps track of parent of each element
    private int[] id;

    // keeps track of total components/sets in union find
    private int numComponents;

    public UnionFind(int[] elements) {
        size = elements.length;
        id = new int[size];
        sz = new int[size];
        numComponents = size;

        for (int i = 0; i < size; i++) {
            // initially set every element as a root node
            id[i] = i;
            // initially set every component's size to 1
            sz[i] = 1;
        }
    }

    public int find(int p) {
        // find root of element p
        int root = p;
        while (root < size && root != id[root])
            root = id[root];

        // perform path compression
        while (p != root) {
            int next = id[p];
            id[p] = root;
            p = next;
        }
        return root;
    }

    public void union(int i, int j) {
        if (connected(i, j))
            return;

        int root_i = find(i);
        int root_j = find(j);

        if (sz[root_i] >= sz[root_j]) {
            sz[root_i] += sz[root_j];
            sz[root_j] = 0;
            id[root_j] = root_i;
        } else {
            sz[root_j] += sz[root_i];
            sz[root_i] = 0;
            id[root_i] = root_j;
        }
        numComponents--;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int componentSize(int p) {
        return sz[p];
    }

    public int size() {
        return size;
    }

    public int components() {
        return numComponents;
    }

    public static void main(String[] args) {
        int[] elements = new int[] { 1, 2, 3, 4, 5, 6 };
        UnionFind uf = new UnionFind(elements);

        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(4, 5);

        uf.union(1, 5);
        System.out.println(uf.find(5));
    }
}
