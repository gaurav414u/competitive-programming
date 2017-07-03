
import java.util.*;

public class GridXors {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int ts = s.nextInt();
        for (int t = 0; t < ts; t++) {
            processTestCase(s);
        }
    }

    public static void processTestCase(Scanner s) {
        int N = s.nextInt();
        int M = s.nextInt();
        int[][] arr = new int[N][M];
        int[][] c = new int[N][M];

        s.nextInt();
        s.nextInt();
        s.nextInt();
        s.nextInt();

        EdgeWeightedGraph graph = new EdgeWeightedGraph(N*M);

        int count = 0;
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = s.nextInt();
                c[i][j] = count++;
            }
        }

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0; j < M; j++) {
                int weight = 0;
                if (j+1 != M) {
                    weight = arr[i][j] ^ arr[i][j+1];
                    graph.addEdge(new WeightedEdge(c[i][j], c[i][j+1], weight));
                }
                if (i +1 != N) {
                    weight = arr[i][j] ^ arr[i+1][j];
                    graph.addEdge(new WeightedEdge(c[i][j], c[i+1][j], weight));
                }
            }
        }

        MST mst = new MST(graph);
        System.out.println(mst.weight);
    }
}

class QuickUnion {
    int[] id;
    int[] sz;

    public QuickUnion(int V) {
        id = new int[V];
        sz = new int[V];

        for (int i = 0 ; i < V ; i++) {
            id[i] = i;
        }
    }

    private int root(int v) {
        while (v != id[v]) {
            id[v] = id[id[v]];
            v = id[v];
        }
        return v;
    }


    public boolean connected(int v, int w) {
        return root(v) == root(w);

    }

    public void union(int v, int w) {
        int i = root(v);
        int j = root(w);
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }
}

class MST {
    List<WeightedEdge> mst;
    int weight;


    public MST(EdgeWeightedGraph g) {
        mst = new ArrayList<>();

        g.edges().sort((o1, o2) -> Long.compare(o1.weight, o2.weight));
        QuickUnion uf = new QuickUnion(g.V());

        int edgeSize = g.E();

        int i = 0;
        while(i < edgeSize) {
            WeightedEdge e = g.edges().get(i++);
            int v = e.either();
            int w = e.other();
            if (!uf.connected(v, w)) {
                uf.union(v, w);
                weight += e.weight();
                mst.add(e);
            }
        }
    }

}

class EdgeWeightedGraph implements Graph<WeightedEdge> {
    private int mV;
    private List<WeightedEdge>[] mAdjList;
    private List<WeightedEdge> edges;

    /**
     * Create a graph with V vertices
     * @param V
     */
    public EdgeWeightedGraph(int V) {
        this.mV = V;
        edges = new ArrayList<>();
        mAdjList = (List<WeightedEdge>[]) new List[V];
        for (int i = 0 ; i < V ; i ++) {
            mAdjList[i] = new ArrayList<>();
        }
    }

    public void addEdge(WeightedEdge e) {
        int v = e.either();
        int w = e.other(v);
        mAdjList[v].add(e);
        mAdjList[w].add(e);
        edges.add(e);
    }

    @Override
    public void addEdge(int v, int w) {
        throw new RuntimeException("Cannot call on this type of graph");
    }

    @Override
    public Iterable<WeightedEdge> adj(int v) {
        return mAdjList[v];
    }

    @Override
    public int V() {
        return mV;
    }

    @Override
    public int E() {
        return edges().size();
    }

    public List<WeightedEdge> edges() {
        return edges;
    }


    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("");
        for (int i = 0 ; i < mAdjList.length ; i++) {
            b.append("v" + i+"\n");
            b.append(mAdjList[i].toString());
            b.append("\n===============================\n");
        }
        return b.toString();
    }
}


class WeightedEdge implements Edge, Comparable<WeightedEdge> {
    public long weight;
    protected int v;
    protected int w;

    public WeightedEdge(int v, int w, long weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public long weight() {
        return weight;
    }

    @Override
    public int either() {
        return v;
    }

    @Override
    public int other(int other) {
        if (other == this.v) {
            return w;
        }
        return v;
    }

    public int other() {
        return w;
    }

    @Override
    public int compareTo(WeightedEdge o) {
        if (this.weight > o.weight) {
            return 1;
        } else if (this.weight < o.weight) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return v +"-"+ w + "  " + weight;
    }
}

/**
 * Edge of a graph
 */
interface Edge {
    /**
     * @return either of the two vertices
     */
    int either();

    /**
     * @param v one of the vertices
     * @return vertex other than v
     */
    int other(int v);
}

/**
 * Graph API
 */
interface Graph<T> {
    /**
     *
     * @param v 1 end of edge
     * @param w other end of edge
     */
    public void addEdge(int v, int w);

    /**
     *
     * @param v the input vertex
     * @return list of vertices adjacent to v
     */
    public Iterable<T> adj(int v);


    /**
     * Number of vertices
     * @return
     */
    public int V();

    /**
     *
     * @return Number of Edges
     */
    public int E();
}

