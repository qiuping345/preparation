import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class Graph {
    
    protected HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();
    protected boolean isDirected = true;
    
    public Graph() {
        // empty
    }
    
    public Graph(boolean isDirected) {
        this.isDirected = isDirected;
    }
    
    public boolean empty() {
        return vertices.size() == 0;
    }
    
    public Collection<Vertex> getVertices() {
        return vertices.values();
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
        
        return isDirected ? size : size / 2;
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
    
    public void removeVertex(Vertex v) {
        vertices.remove(v);
        
        for(Vertex vertex : vertices.values()){
            vertex.removeEdge(v);
        }
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
        
        if (!isDirected) {
            Vertex vEnd = getVertex(end.getLabel());
            vEnd.addEdge(start, weight);
        }
    }
    
    
    public void removeEdge(Vertex start, Vertex end) {
        if (start == null || end == null) {
            return;
        }
        
        Vertex v = getVertex(start.getLabel());
        v.removeEdge(end);
        
        if (!isDirected) {
            Vertex vEnd = getVertex(end.getLabel());
            vEnd.removeEdge(start);
        }
    }
    
    public boolean isConnected(){
        if(empty()) {
            return false;
        }
        
        Map.Entry<String, Vertex> entry = vertices.entrySet().iterator().next();
        Vertex v = entry.getValue();
        Set<Vertex> visited = new HashSet<Vertex>();
        DFS(v, visited);
        return visited.size() == vertexSize();
    }
    
    public void prim() {
        Set<Vertex> chosenVertices = new HashSet<Vertex>();
        List<Edge> chosenEdges = new ArrayList<Edge>();
        Edge e = getShortestEdge();
        System.out.println("first edge : " + e.toString());
        chosenEdges.add(e);
        chosenVertices.add(e.getStart());
        chosenVertices.add(e.getDestination());
        
        while (chosenVertices.size() != getVertices().size()) {
            Edge edge = getShortestEdge(chosenVertices, chosenEdges);
            chosenVertices.add(edge.getDestination());
            chosenEdges.add(edge);
        }
        
        for(Edge edge : chosenEdges) {
            System.out.println(edge.toString());
        }
    }
    
    public Edge getShortestEdge(Collection<Vertex> chosenVertex, Collection<Edge> chosenEdges) {
        if(chosenVertex == null) {
            return null;
        }
        
        Edge result = null;
        int minWeight = Integer.MAX_VALUE;
        for (Vertex v : chosenVertex) {
            for (Edge e : v.getEdges()) {
                if(chosenEdges != null 
                && !chosenEdges.contains(e)
                && !chosenVertex.contains(e.getDestination())
                && e.getWeight() < minWeight) {
                    minWeight = e.getWeight();
                    result = e;
                }
            }
        }
        return result;
    }
    
    public Edge getShortestEdge() {
        Edge result = null;
        int minWeight = Integer.MAX_VALUE;
        for (Vertex v : getVertices()) {
            for (Edge e : v.getEdges()) {
                if(e.getWeight() < minWeight) {
                    minWeight = e.getWeight();
                    result = e;
                }
            }
        }
        return result;
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
    
    public static class PathTuple {
        public Vertex last;
        public int weight;
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
        
        public Edge getEdge(Vertex destination) {
            for(Edge edge : edges) {
                if (edge.getDestination() == destination) {
                    return edge;
                }
            }
            return null;
        } 
        
        public void addEdge(Vertex destination, int weight) {
            Edge e = edgeWith(destination);
            
            if (null == e) {
                Edge edge = new Edge(this, destination, weight);
                edges.add(edge);
            } else {
                e.setWeight(weight);
            }
        }
        
        public Edge removeEdge(Vertex destination) {
            if(destination == null) {
                return null;
            }
            
            Iterator<Edge> iter = getEdges().iterator();
            while (iter.hasNext()) {
                Edge e = iter.next();
                if (e.getDestination() == destination) {
                    iter.remove();
                    return e;
                }
            }
            return null;
        }
        
        public Edge getShortestEdge() {
            int currMin = Integer.MAX_VALUE;
            Edge result = null;
            for(Edge edge : edges) {
                if (edge.getWeight() < currMin) {
                    currMin = edge.getWeight();
                    result = edge;
                }
            }
            return result;
        }
        
        public void removelAllEdges() {
            getEdges().clear();
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
        private Vertex start;
        private Vertex destination;
        private int weight;
        
        public Edge() {
            //
        }

        public Edge(Vertex start, Vertex destination, int weight) {
            this.start = start;
            this.destination = destination;
            this.weight = weight;
        }
        
        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Vertex getStart() {
            return start;
        }
        
        
        public Vertex getDestination() {
            return destination;
        }
        
        public String toString(){
            return "start: " + getStart().getLabel() + ", end : " + getDestination().getLabel() + ", weight: " + getWeight();
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

        
        graph.addEdge(va, vb, 10);
        graph.addEdge(va, vc, 14);
        graph.addEdge(va, vd, 17);
        graph.addEdge(vb, vc, 13);
        graph.addEdge(vb, ve, 12);
        graph.addEdge(vc, vf, 19);
        graph.addEdge(vd, vc, 13);
        graph.addEdge(vc, ve, 15);
        graph.addEdge(vc, vh, 28);
        graph.addEdge(vd, vf, 18);
        graph.addEdge(ve, vg, 27);
        graph.addEdge(vf, vh, 21);
        graph.addEdge(vh, vi, 22);
        
        graph.prim();

//        graph.DFS();
//        graph.BFS();
//        System.out.println("is connected: " + graph.isConnected());
//        Vertex vj = new Vertex(10, "J");
//        graph.addVertex(vj);
//        System.out.println("is connected: " + graph.isConnected());
        
        
        
        
        
    }

}
