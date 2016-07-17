
public class Permutation {

    public static void main(String[] args) {
        int[] array = new int[]{2,3,4,5,6,7,8,9,0};
        int N = 3;
        
        Permutation perm = new Permutation();
        perm.permutate(array, N);
        System.out.println("perm count: " + perm.count);
    }
    
    boolean[] flags;
    int[] data;
    int count = 0;
    public void permutate(int[] array, int n){
        flags = new boolean[array.length];
        data = new int[n];
        permutate(array, n, 0);
    }
    
    public void permutate(int[] array, int n, int index){
        if(index == n) {
            count++;
            printArray(data);
            return;
        }
        
        for(int i =0; i < array.length; i++) {
            if(!flags[i]) {
                data[index] = array[i];
                flags[i] = true;
                permutate(array, n, index+1);
                flags[i] = false;
            }
        }
    }
    
    public void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print("  " + array[i]);
        }
        System.out.println("");
    }
}
