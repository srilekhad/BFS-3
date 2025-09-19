// Time Complexity: O(V + E) – each node is visited once and each edge is traversed once when wiring neighbors.
// Space Complexity: O(V) – hashmap for original→clone and recursion stack (excluding output; including the cloned graph it’s O(V + E)).

// Create/lookup a clone for each node in a hashmap; if unseen, allocate and store it.
// DFS through neighbors: recursively process unseen neighbors, then add the neighbor’s clone (from the map) to the current clone’s neighbors.
// Start DFS from the input node and finally return its clone from the hashmap.

class Solution {
    HashMap<Node, Node> map;

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        this.map = new HashMap<>();

        dfs(node);
        return map.get(node);
    }

    private void dfs(Node curr) {
        Node copyCurr = clone(curr);

        for (Node ne : curr.neighbors) {
            if (!map.containsKey(ne)) {
                dfs(ne);
            }
            copyCurr.neighbors.add(clone(ne));
        }
    }

    private Node clone(Node node) {
        if (node == null) return null;
        if (map.containsKey(node)) return map.get(node);
        map.put(node, new Node(node.val));
        return map.get(node);
    }
}