
public class Combination {

    public static void main(String[] args) {
        int[] array = new int[]{2,3,4,5,6,7,8,9,0};
        Combination comb = new Combination();
        comb.combine(array, 8);
        System.out.println("count: " + comb.count);
    }

    protected int[] data;
    public int count = 0;
    public void combine(int[] array, int n) {
        data = new int[n];
        combine(array, 0, array.length, 0, n);
    }
    
    public void combine(int[] array, int start, int end, int index, int n) {
        if(index == n){
            count++;
            printArray(data);
            return;
        }
        
        for(int i = start; i < end &&  end - i > n - index - 1; i++) {
            data[index] = array[i];
            combine(array, i+1, end, index+1, n);
        }        
    }

    public void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print("  " + array[i]);
        }
        System.out.println("");
    }
    
}
