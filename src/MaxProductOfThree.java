
public class MaxProductOfThree {

    public static void main(String[] args) {
        MaxProductOfThree inst = new MaxProductOfThree();
        int[] array = new int[]{23, 35,55,78, -100, -50 , 100 , -300, 235};
        int N = 3;
        inst.combine(array, N);
        System.out.println("max: " + inst.max);
    }
    
    public int max = 0;
    public int[] data;
    
    
    public void combine(int[] array, int n) {
        data = new int[n];
        combine(array, n, 0, array.length, 0);
    }
    
    public void combine(int[] array, int n, int start, int end, int index) {
        if(index == n) {
            int product = 1;
            for(int i = 0; i < n; i++) {
                product *= data[i];
            }
            if(product > max) {
                max = product;
                printArray(data);
            }
            return;
        }
        
        for (int i = start; i < end && end - i > n - index -1 ; i++) {
            data[index] = array[i];
            combine(array, n, i+1, end, index + 1);            
        }
    }
    
    public void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print("  " + array[i]);
        }
        System.out.println("");
    }

}
