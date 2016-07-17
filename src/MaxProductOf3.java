import java.util.List;
import java.util.Stack;
import java.lang.Integer;
import java.util.ArrayList;

public class MaxProductOf3 {
    protected static int N = 3;
    protected long tempMax = 0;
    protected Object[] result = new Object[3];
    public long getMax(){
        return tempMax;
    }
    
    public Object[] getNums () {
        return result;
    }
    
    public static void main(String[] args) {
        int[] integers = new int[]{23, 4, 98, -1, -2, -7, 55, 87, -5, 0, -100, -101};
        boolean[] flags = new boolean[integers.length];
        
        MaxProductOf3 instance = new MaxProductOf3();
        instance.combination(integers, 0 , N);
        System.out.println(" max: " + instance.getMax());
        
        Object[] result = instance.getNums();
        for (int i = 0; i < result.length; i++) {
            System.out.print( " " + (Integer)result[i]);
        }
    }
    
    Stack<Integer> stack = new Stack<Integer>();
    
    public void combination(int[] array, int startIndex, int n) {
        if(n == 0) {
            return;
        } else if(n == 1){
            for(int i = startIndex; i < array.length; i++) {
                stack.push(array[i]);
                check();
                stack.pop();
            }
        }
        
        
        if(check()) {
            return;
        }
        
        if(startIndex < array.length - 1) {
            combination(array, startIndex + 1, n);
            stack.push(array[startIndex]);
            combination(array, startIndex + 1, n - 1);
            stack.pop();
        }
    }
    
    public boolean check(){
        boolean r = false;
        if(stack.size() == N) {
            r = true;
            Object[] arr = stack.toArray();
            long product = 1;
            for (int i = 0; i < arr.length; i++) {
                product *= (Integer)arr[i];
            }
            
            if(product > tempMax) {
                tempMax = product;
                result = arr;
            }
        }
        return r;
    }
}
