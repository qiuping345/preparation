import java.util.HashSet;
import java.util.Set;


public class GraphArrayImpl {

    int numVertex;
    int[][] edges;
    public GraphArrayImpl(int numVertex){
        this.numVertex = numVertex;
        edges = new int[numVertex][numVertex];
        for(int i = 0; i < numVertex; i++) {
            for(int j = 0; j < numVertex; j++) {
                edges[i][j] = i == j ? 0 : Integer.MAX_VALUE;
            }
        }
    }

    public void addEdge(int start, int end, int weight) {
        if(start < 0 || start >= numVertex || end < 0 || end >= numVertex) {
            return;
        }
        
        edges[start][end] = weight;
    }
    
    public void dijkstra(int v) {
        int[] path = new int[numVertex];
        int[] distance = new int[numVertex];
        System.arraycopy(edges[v], 0, distance, 0, numVertex);
        Set<Integer> chosen = new HashSet<Integer>();
        chosen.add(new Integer(v));
        for (int i = 0; i < numVertex; i++) {
            if (i != v && distance[i] != Integer.MAX_VALUE) {
                path[i] = v;
            }
        }
        
        for (int i = 0; i < numVertex; i++) {
            int min = Integer.MAX_VALUE;
            int temp = v;
            for (int j = 0; j < numVertex; j++) {
                if (!chosen.contains(new Integer(j)) && distance[j] < min) {
                    temp = j;
                    min = distance[j];
                }
            }
            
            chosen.add(new Integer(temp));   //newly added vertex.
            
            System.out.println("seq : " + i + ", chosen add: " + temp);
            printArray(path);
            printArray(distance);
            
            for (int j = 0; j < numVertex; j++) {
                if(!chosen.contains(new Integer(j)) 
                && edges[temp][j] < Integer.MAX_VALUE
                && distance[temp] + edges[temp][j] < distance[j]) {  // if the newly added node made some difference? 
                    distance[j] = distance[temp] + edges[temp][j];
                    path[j] = temp;
                }
            }
        }
        
        printArray(path);
        printArray(distance);
        
    }
    
    public void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print("  " + array[i]);
        }
        System.out.println("");
    }
    
    public static void main(String[] args) {
        GraphArrayImpl graph = new GraphArrayImpl(5);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 3, 30);
        graph.addEdge(0, 4, 100);
        graph.addEdge(1, 2, 50);
        graph.addEdge(2, 4, 10);
        graph.addEdge(3, 2, 20);
        graph.addEdge(3, 4, 60);

        graph.dijkstra(1);

    }

}
