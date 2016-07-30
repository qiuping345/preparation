import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class Graph {
    
    protected HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();
    protected boolean hasDirection = true;
    
    public Graph() {
        // empty
    }
    
    public Graph(boolean isDirected) {
        hasDirection = isDirected;
    }
    
    public boolean empty() {
        return vertices.size() == 0;
    }
    
    public int vertexSize() {
        return vertices.size();
    }
    
    public int edgeSize() {
        int size = 0;
        for(String key : vertices.keySet()) {
            Vertex v = vertices.get(key);
            if (v != null) {
                size += v.edges.size();
            }
        }
        
        return hasDirection ? size : size / 2;
    }
    
    
    public boolean addVertex(Vertex v) {
        if(v == null) {
            return false;
        }
        if(!vertices.containsKey(v.getLabel())) {
            vertices.put(v.getLabel(), v);
            return true;
        }
        return false;
    }
    
    public Vertex getVertex(String label) {
        return vertices.get(label);
    }
    
    public void addEdge(Vertex start, Vertex end, int weight) {
        if (start == null || end == null) {
            return;
        }
        
        Vertex v = getVertex(start.getLabel());
        v.addEdge(end, weight);
        
        if (!hasDirection) {
            Vertex vEnd = getVertex(end.getLabel());
            vEnd.addEdge(start, weight);
        }
    }
    
    public void DFS(){
        if(empty()) {
            return;
        }
        
        Map.Entry<String, Vertex> entry = vertices.entrySet().iterator().next();
        Vertex v = entry.getValue();
        Set<Vertex> visited = new HashSet<Vertex>();
        DFS(v, visited);
        System.out.println(" ");
    }
    
    private void DFS(Vertex v, Set<Vertex> visited) {
        if (empty() || v == null || visited == null) {
            return;
        }
        
        if(visited.contains(v)) {
             return;
        } else {
            visited.add(v);
            System.out.print(" " + v.getLabel());
        }
        
        for (Edge edge : v.getEdges()) {
            Vertex dest = edge.getDestination();
            if (!visited.contains(dest)) {
                DFS(dest, visited);
            }
        }
    }
    
    public void BFS(){
        Queue queue = new LinkedList();
        Set<Vertex> visited = new HashSet<Vertex>();
        Map.Entry<String, Vertex> entry = vertices.entrySet().iterator().next();
        Vertex v = entry.getValue();
        queue.add(v);
        while (!queue.isEmpty()) {
            Vertex curr = (Vertex) queue.remove();
            System.out.print(" " + curr.getLabel());
            visited.add(curr);

            for (Vertex vertex : curr.getNeightbors()){
                if (!visited.contains(vertex) && !queue.contains(vertex)) {
                    queue.add(vertex);
                }
            }
        }
        
        System.out.println(" ");
    }
    
    public static class Vertex{
        private int value;
        private String label;
        protected ArrayList<Edge> edges = new ArrayList<Edge>();
        
        public Vertex(int val, String label){
            value = val;
            this.label = label;
        }
        
        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
        
        public ArrayList<Edge> getEdges() {
            return edges;
        }
        
        public void addEdge(Vertex destination, int weight) {
            Edge e = edgeWith(destination);
            
            if (null == e) {
                Edge edge = new Edge(destination, weight);
                edges.add(edge);
            } else {
                e.setWeight(weight);
            }
        }
        
        public boolean linkedWith(Vertex destination) {
            for(Edge edge : edges) {
                if (edge.getDestination() == destination) {
                    return true;
                }
            }
            return false;
        }
        
        public Edge edgeWith(Vertex destination) {
            for(Edge edge : edges) {
                if (edge.getDestination() == destination) {
                    return edge;
                }
            }
            return null;
        }
        
        public List<Vertex> getNeightbors(){
            List<Vertex> result = new ArrayList<Vertex>();
            for(Edge edge : edges) {
                result.add(edge.getDestination());
            }
            return result;
        }
        
        @Override
        public String toString() {
            return "Vertex, label: " + label + ", value: " + value;
        }
    }


    
    public static class Edge {
        private Vertex destination;
        private int weight;
        
        public Edge() {
            //
        }

        public Edge(Vertex destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
        
        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Vertex getDestination() {
            return destination;
        }
    }
    

    public static void main(String[] args) {
        Graph graph = new Graph(false);
        Vertex va = new Vertex(1, "A");
        graph.addVertex(va);
        Vertex vb = new Vertex(2, "B");
        graph.addVertex(vb);
        Vertex vc = new Vertex(3, "C");
        graph.addVertex(vc);
        Vertex vd = new Vertex(4, "D");
        graph.addVertex(vd);
        Vertex ve = new Vertex(5, "E");
        graph.addVertex(ve);
        Vertex vf = new Vertex(6, "F");
        graph.addVertex(vf);
        Vertex vg = new Vertex(7, "G");
        graph.addVertex(vg);
        Vertex vh = new Vertex(8, "H");
        graph.addVertex(vh);
        Vertex vi = new Vertex(9, "I");
        graph.addVertex(vi);

        graph.addEdge(va, vb, 0);
        graph.addEdge(va, vc, 0);
        graph.addEdge(va, vd, 0);
        graph.addEdge(vb, vc, 0);
        graph.addEdge(vb, ve, 0);
        graph.addEdge(vc, vf, 0);
        graph.addEdge(vd, vf, 0);
        graph.addEdge(ve, vg, 0);
        graph.addEdge(vf, vh, 0);
        graph.addEdge(vh, vi, 0);

        graph.DFS();

        graph.BFS();



        
    }

}
