
public class Combination {

    public static void main(String[] args) {
        int[] array = new int[]{2,3,4,5,6,7,8,9,0};
        Combination comb = new Combination();
        comb.combine(array, 8);
        System.out.println("count: " + comb.count);
        
        String str = "abcde";
        //comb.stringCombine(str, 3);
        comb.stringPermutate(str, 3);
    }
    

    char[] arr;
    public void stringCombine (String str, int n) {
        arr = new char[n];
        stringCombine(str, n, 0);
    }
    
    public void stringPermutate (String str, int n) {
        arr = new char[n];
        stringPermutate(str, n, 0);
    }

    public void stringPermutate(String str, int n, int index) {
        if(index >= n) {
            // recursion exit
            String s = new String(arr);
            System.out.println(s);
            return;
        }
        
        for (int i = 0; i< str.length() ; i++) {
            arr[index] = str.charAt(i);
            stringPermutate(str.substring(0, i) + str.substring(i + 1, str.length()) , n, index + 1);
        }
        
    }
    
    
    public void stringCombine(String str, int n, int index) {
        if(index >= n) {
            // recursion exit
            String s = new String(arr);
            System.out.println(s);
            return;
        }
        
        for (int i = 0; i< str.length() - (n - 1 - index); i++) {
            arr[index] = str.charAt(i);
            stringCombine(str.substring(i + 1, str.length()) , n, index + 1);
        }
        
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
