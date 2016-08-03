import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.awt.Point;

public class Test {
    
    public static class Solution {
        public int CombinationSum4(int[] nums, int target)  {
            int[] res = new int[target+1];
            res[0] = 1;
            for(int cur=1; cur<=target; cur++) {
                int total = 0;
                for(int i=0; i<nums.length; i++) {
                    if(cur - nums[i] >= 0) {
                        total += res[cur-nums[i]];
                    }
                }
                res[cur] = total;
            }
            
            return res[target];
        }
        public int combinationSum3(int[] nums, int target) {
            if(target == 0) {
                return 1;
            }
            int result = 0;
            for(int i = 0; i < nums.length ; i++) {
                if(target - nums[i] >= 0) {
                    result +=  combinationSum3(nums, target - nums[i]);
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        int[] arr2 = new int[]{1,2,3};
        Solution s = new Solution();
        System.out.println(s.CombinationSum4(arr2, 32));
        System.out.println(s.combinationSum3(arr2, 32));

        return;
        /*
        // TODO Auto-generated method stub
        Test inst = new Test();
        
        inst.printList(inst.primesum(1616161616));
        
        List<String> list = new ArrayList<String> ();
        list.add("oooxxx");
        list.add("xoooxx");
        list.add("xoxxoo");
        list.add("oxoxoo");
        list.add("oxoooo");
        list.add("oooxxx");
        System.out.println("count black shape: " + inst.black(list));
        
        
        
        int[] arr = new int[]{1,3,7,9,11,25,78,93,100,122,147,157,168,189};
        System.out.println(inst.binarySearch(arr, 78));
        System.out.println(inst.binarySearch(arr, 79));
        
        System.out.println(inst.reverseString("hello world"));
        System.out.println(inst.reverseString("Do or do not, there is no try."));
        System.out.println("is Palindrome : " + inst.isPalindrome("abdba"));
        System.out.println("is Palindrome : " + inst.isPalindrome("abdbad"));
        */

    }
    
    public ArrayList<Integer> primesum(int a) {
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i = 3 ; i <= a ; i++) {
            set.add(new Integer(i));
        }
        
        for(int i = 2; i < a / 2; i++) {
            for (int j = 2; j <= a / i; j++) {
                set.remove(new Integer(i*j));
            }
        }
        
        ArrayList<Integer> list = new ArrayList<Integer>(set);
        printList(list);
        Collections.sort(list);
        for(int i = 0; i < list.size(); i++) {
            Integer diff = new Integer(a - list.get(i));
            if(list.contains(diff)) {
                ArrayList<Integer> result = new ArrayList<Integer>();
                result.add(list.get(i));
                result.add(diff);
                return result;
            }
            
        }
        return null;
    }

    public int black(List<String> a) {
        if(a == null || a.size() == 0) {
            return 0;
        }
        HashSet<Point> blackDots = new HashSet<Point>();
        for(int i = 0; i < a.size(); i++) {
            String s = a.get(i);
            for(int j = 0; j < s.length(); j++) {
                if(s.charAt(j) == 'x') {
                    blackDots.add(new Point(i,j));
                }
            }
        }
        
        int count = 0;
        while(!blackDots.isEmpty()) {
            Iterator<Point> iter = blackDots.iterator();
            Point p = iter.next();
            
            Stack<Point> stack = new Stack<Point>();
            stack.push(p);
            while(!stack.empty()) {
                Point dot = stack.pop();
                blackDots.remove(dot);
                for(int i = dot.x - 1 ; i <= dot.x + 1; i++){
                    for(int j = dot.y - 1; j <= dot.y + 1; j++) {
                        Point tempPoint = new Point(i, j);
                        if(i >= 0 && i < a.size() && j >= 0 && j < a.get(0).length()
                        && (i == dot.x || j == dot.y)
                        && blackDots.contains(tempPoint)){
                            stack.push(tempPoint);
                        }
                    }
                }
            }
            
            
            count++;
        }
        return count;
    }
    
    public int binarySearch(int[] arr, int key) {
        if(arr == null || arr.length == 0) {
            return -1;
        }
        
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            System.out.println("start: " + start + ", end: " + end);
            int mid = (start + end) / 2;
            if(key > arr[mid]) {
                start = mid + 1;
            } else if (key < arr[mid]) {
                end = mid - 1;
            } else {
                return mid;
            }
        }
        System.out.println("final start: " + start + ", end: " + end);
        return -1;
    }
    
    public boolean isPalindrome(String s) {
        char[] array = s.toCharArray();
        
        for(int i = 0; i < array.length / 2; i++) {
            if(array[i] != array[array.length - 1 -i]) {
                return false;
            }
        }
        return true;
    }

    public String reverseString(String s){
        if(s == null || s.length() == 0) {
            return null;
        }
        char[] array = s.toCharArray();
        reverse(array, 0, array.length);
        System.out.println("rs : " + new String(array));
        
        int start = 0;
        int posSpace = start;
        while(start < array.length) {
            while(posSpace < array.length && array[posSpace] != ' ') {
                posSpace++;
            }
            
            reverse(array, start, posSpace);
            start = posSpace + 1;
            posSpace = start;
        }
        
        return new String(array);
    }
    
    public void reverse(char[] array, int start, int end) {
        for(int i = start; i < (end + start) / 2; i++) {
            swap(array, i,  start + end - 1 - i);
        }
    }
    
    public void swap(char[] array, int index1, int index2) {
        char temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }    
    
    public void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print("  " + array[i]);
        }
        System.out.println("");
    }
    
    public void printIntegerArray(Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print("  " + array[i]);
        }
        System.out.println("");
    }
    
    public <T> void printList(List<T> list){
        Iterator<T> iter = list.iterator();
        while(iter.hasNext()) {
            T t = iter.next();
            System.out.print(" " + t);
        }
        System.out.println(" ");
        
    }
}
