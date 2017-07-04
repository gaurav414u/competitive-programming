import java.util.*;

public class PAndC {
    public static void main(String[] args) {

    }

    /**
     * @return a lexicographically (but preference to size) sorted list of all the combinations' entire permutations for n
     */
    public static List<List> superSet(int n) {
        List<List> list = new ArrayList<>();
        for (int i = n; i >= 1; i--) {
            List<ArrayList<Integer>> c = combinations(n, i);
            for (ArrayList<Integer> eachComb : c) {
                list.addAll(permutations(eachComb, i));
            }
        }

        Collections.sort(list, new Comparator<List>() {
            public int compare(List o1, List o2) {
                if (o1.size() != o2.size()) {
                    return Integer.compare(o2.size(), o1.size());
                }
                for (int i = 0 ; i < o1.size() ; i++) {
                    if (o1.get(i) != o2.get(i)) {
                        return ((Integer)o1.get(i)).compareTo((Integer)o2.get(i));
                    }
                }
                return 0;
            }
        });

        return list;
    }

    /**
     * @param input
     * @param n
     * @return It has to be ArrayList because while sorting, there might be direct accesses from this list
     */
    public static ArrayList<ArrayList<Integer>> permutations(ArrayList<Integer> input, int n) {
        ArrayList list = new ArrayList<ArrayList<Integer>>();
        permute(0, input, n, list);
        return list;
    }

    public static void permute(int start, ArrayList<Integer> input, int n, ArrayList<ArrayList<Integer>> list) {
        if (start == n) {
            //System.out.println(input);
            list.add(input);
            return;
        }
        for (int i = start; i < n; i++) {
            ArrayList<Integer> newinput = (ArrayList<Integer>) input.clone();
            // swapping
            int temp = newinput.get(i);
            newinput.set(i, newinput.get(start));
            newinput.set(start,temp);

            permute(start + 1, newinput, n, list);
            //no need to re-swap, because we have created a new array
        }
    }

    public static List<ArrayList<Integer>> combinations(int n, int r) {
        ArrayList combs = new ArrayList<ArrayList<Integer>>();
        comb(n, 1, r, 0, new ArrayList<Integer>(), combs);
        return combs;
    }

    public static void comb(int n, int s, int r, int c, ArrayList<Integer> arr, List<List<Integer>> combs) {
        if(c ==r) {
            combs.add(arr);
            return;
        }
        for (int i = s ; i < n+1 ; i++) {
            ArrayList<Integer> newList = (ArrayList<Integer>) arr.clone();
            newList.add(i);
            comb(n, i+1, r, c+1, newList, combs);
        }
    }
}
