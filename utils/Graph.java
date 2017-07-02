
import java.util.*;

public class Main {
    public static void main(String[] args) {
        start();
    }

    public static void processTestCase(Scanner s) {
        int N = s.nextInt();
        int K = s.nextInt();
        int X = s.nextInt();
        int M = s.nextInt();
        int S = s.nextInt();

        EdgeWeightedDiGraph graph = new EdgeWeightedDiGraph(N+ 2);

        int z = N + 1;
        for (int i = 1 ; i <= K ; i++) {
            graph.addEdge(new DirectedEdge(i, z, X));
            graph.addEdge(new DirectedEdge(z, i, 0));
        }

        for (int i = 0 ; i < M ; i++) {
            int v = s.nextInt();
            int w = s.nextInt();
            long dist = s.nextInt();
            graph.addEdge(new DirectedEdge(v,w, dist ));
            graph.addEdge(new DirectedEdge(w,v, dist));
        }
        DijkstraSP sp = new DijkstraSP(graph, S);

        StringBuilder sb = new StringBuilder("");
        for (int i = 1 ; i < sp.distTo.length - 1; i++) {
            sb.append(sp.distTo[i]+ " ");
        }
        System.out.println(sb);
    }

    public static void start() {
        Scanner s = new Scanner(System.in);
        int ts = s.nextInt();
        for (int t = 0 ; t < ts ; t++) {
            processTestCase(s);
        }
    }
}

class QuickUnion {
    int[] id;

    QuickUnion(int V) {
        id = new int[V];
        for (int i = 0 ; i < V ; i++) {
            id[i] = i;
        }
    }

    private int root(int v) {
        while (v != id[v]) {
            v = id[v];
        }
        return v;
    }

    public boolean connected(int v, int w) {
        return root(v) == root(w);

    }

    public void union(int v, int w) {
        id[root(v)] = root(w);
    }
}

class MST {
    List<WeightedEdge> mst;

    MST(EdgeWeightedGraph g) {
        mst = new ArrayList<>();
        PriorityQueue<WeightedEdge> minpq = new PriorityQueue<>(new Comparator<WeightedEdge>() {
            @Override
            public int compare(WeightedEdge o1, WeightedEdge o2) {
                return Long.compare(o1.weight, o2.weight);
            }
        });
        for (WeightedEdge e: g.edges()) {
            minpq.add(e);
        }
        QuickUnion uf = new QuickUnion(g.V());

        while(!minpq.isEmpty()) {
            WeightedEdge e = minpq.poll();
            int v = e.either();
            int w = e.other();
            if (!uf.connected(v, w)) {
                uf.union(v, w);
                mst.add(e);
            }
        }
    }

    public int weight()  {
        int w = 0;
        for (WeightedEdge e : mst) {
            w += e.weight();
        }
        return w;
    }

}


class DijkstraSP {
    public long[] distTo;
    private boolean[] relaxed;
    PriorityQueue<Integer> pq;

    DijkstraSP(EdgeWeightedDiGraph graph, int source) {
        distTo = new long[graph.V()];
        Arrays.fill(distTo, Long.MAX_VALUE);

        relaxed = new boolean[graph.V()];
        pq =  new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Long.compare(distTo[o1], distTo[o2]);
            }
        });


        distTo[source] = 0;
        pq.add(source);
        while (!pq.isEmpty()) {
            int v= pq.poll();
            if (relaxed[v]){
                continue;
            }
            relaxed[v] = true;
            for (DirectedEdge e : graph.adj(v)) {
                this.relax(e);
            }
        }
    }

    private void relax(DirectedEdge e) {
        int from = e.from;
        int to = e.to;
        if (distTo[from] + e.weight() < distTo[to]) {
            distTo[to] = distTo[from] + e.weight;
            this.pq.add(to);
            relaxed[to] = false;
        }
    }
}


class EdgeWeightedDiGraph implements Graph<DirectedEdge> {
    private int mV;
    private List<DirectedEdge>[] mAdjList;

    /**
     * Create a graph with V vertices
     * @param V
     */
    public EdgeWeightedDiGraph(int V) {
        this.mV = V;
        mAdjList = new List[V];
        for (int i = 0 ; i < V ; i ++) {
            mAdjList[i] = new ArrayList<>();
        }
    }

    public void addEdge(DirectedEdge e) {
        mAdjList[e.from].add(e);
    }

    @Override
    public void addEdge(int v, int w) {
        throw new RuntimeException("Cannot call on this type of graph");
    }

    @Override
    public Iterable<DirectedEdge> adj(int v) {
        return mAdjList[v];
    }

    @Override
    public int V() {
        return mV;
    }

    @Override
    public int E() {
        return 0;
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
        return 0;
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


class AdjListGraph implements Graph<Integer> {
    private int mV;
    private List<Integer>[] mAdjList;

    /**
     * Create a graph with V vertices
     * @param V
     */
    public AdjListGraph(int V) {
        this.mV = V;
        mAdjList = (List<Integer>[]) new List[V];
        for (int i = 0 ; i < V ; i ++) {
            mAdjList[i] = new ArrayList<>();
        }
    }

    @Override
    public void addEdge(int v, int w) {
        mAdjList[v].add(w);
        mAdjList[w].add(v);
    }

    @Override
    public Iterable<Integer> adj(int v) {
        return mAdjList[v];
    }

    @Override
    public int V() {
        return mV;
    }

    @Override
    public int E() {
        return 0;
    }
}

class DirectedEdge extends WeightedEdge {
    public int from;
    public int to;

    DirectedEdge(int from, int to, long weight) {
        super(from, to, weight);
        this.from = from;
        this.to = to;
    }
}


class WeightedEdge implements Edge, Comparable<WeightedEdge> {
    public long weight;
    protected int v;
    protected int w;

    WeightedEdge(int v, int w, long weight) {
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

