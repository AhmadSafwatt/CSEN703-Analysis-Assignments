import java.util.*;

class Graph {
    private Map<Integer, List<Integer>> graph = new HashMap<>(); // our adjacency list

    public void addEdge(int u, int v) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
    }

    public List<Integer> depthFirst(int start, String order) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited.contains(node)) {
                result.add(node);
                visited.add(node);
                List<Integer> neighbors = graph.getOrDefault(node, new ArrayList<>());
                if (order.equals("left")) {
                    Collections.sort(neighbors, Collections.reverseOrder());
                } else {
                    Collections.sort(neighbors);
                }
                stack.addAll(neighbors);
            }
        }

        return result;
    }

    public List<Integer> breadthFirst(int start, String order) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (!visited.contains(node)) {
                result.add(node);
                visited.add(node);
                List<Integer> neighbors = graph.getOrDefault(node, new ArrayList<>());
                if (order.equals("left")) {
                    Collections.sort(neighbors, Collections.reverseOrder());
                } else {
                    Collections.sort(neighbors);
                }
                queue.addAll(neighbors);
            }
        }

        return result;
    }

    public boolean hasCycle() {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> stack = new HashSet<>();

        for (int node : graph.keySet()) {
            if (!visited.contains(node)) {
                if (dfsVisit(node, visited, stack)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfsVisit(int node, Set<Integer> visited, Set<Integer> stack) {
        visited.add(node);
        stack.add(node);

        List<Integer> neighbors = graph.getOrDefault(node, new ArrayList<>());
        for (int neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                if (dfsVisit(neighbor, visited, stack)) {
                    return true;
                }
            } else if (stack.contains(neighbor)) {
                return true;
            }
        }

        stack.remove(node);
        return false;
    }

    public boolean isBipartite() {
        Map<Integer, Integer> color = new HashMap<>();

        for (int node : graph.keySet()) {
            if (!color.containsKey(node) && !bfsColor(node, color)) {
                return false;
            }
        }

        return true;
    }

    private boolean bfsColor(int start, Map<Integer, Integer> color) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        color.put(start, 0);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            int currentColor = color.get(node);

            List<Integer> neighbors = graph.getOrDefault(node, new ArrayList<>());
            for (int neighbor : neighbors) {
                if (!color.containsKey(neighbor)) {
                    queue.add(neighbor);
                    color.put(neighbor, 1 - currentColor);
                } else if (color.get(neighbor) == currentColor) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Graph testGraph = new Graph();

        int[][] edges = { { 1, 3 }, { 1, 4 }, { 2, 1 }, { 2, 3 }, { 3, 4 }, { 4, 1 }, { 4, 2 } };

        for (int[] edge : edges) {
            testGraph.addEdge(edge[0], edge[1]);
        }

        System.out.println("Depth first: " + testGraph.depthFirst(1, "left"));
        System.out.println("Breadth first: " + testGraph.breadthFirst(1, "left"));

        System.out.print("The graph ");
        if (testGraph.hasCycle()) {
            System.out.println("has cycles.");
        } else {
            System.out.println("has no cycles.");
        }

        System.out.print("The graph is ");
        if (testGraph.isBipartite()) {
            System.out.println("bipartite.");
        } else {
            System.out.println("not bipartite.");
        }
    }
}
