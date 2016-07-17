
public class Sorter {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] testArray = new int[]{45, 56, 21, 7, 233, 98,3245,56,777,99,21, 34, 0, 233, 876, 122, 17, 177, 34, 67};
        Sorter sorter = new Sorter();
        //sorter.heapSort(testArray);
        //sorter.bubbleSort(testArray);
        sorter.quickSort(testArray);
        sorter.printArray(testArray);
        System.out.println("is Ascend : " + sorter.isAscend(testArray));
        
    }

    public void quickSort(int[] array) {
        qsort(array, 0, array.length - 1);
        //recursiveQuickSort(array, 0, array.length - 1);
    }
    
    
    public int partition(int[] array, int left, int right) {
        int pivot = array[left];
        while (left <= right) {
            while(array[left] < pivot) {
                left ++;
            }
            while(array[right] > pivot) {
                right--;
            }
            
            if(left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        }
        return left;
    }
    
    
    public void recursiveQuickSort(int[] array, int startIdx, int endIdx) { 
        int idx = partition(array, startIdx, endIdx); 
        printArray(array);
        // Recursively call quicksort with left part of the partitioned array
        if (startIdx < idx - 1) { 
            recursiveQuickSort(array, startIdx, idx - 1); 
        } 
        // Recursively call quick sort with right part of the partitioned array 
        if (endIdx > idx) { 
            recursiveQuickSort(array, idx, endIdx); 
        }
    }

    
   /* 
    public int partition(int[] array, int left, int right) { 
        int pivot = array[left]; // taking first element as pivot 
        while (left <= right) { 
            //searching number which is greater than pivot, bottom up 
            while (array[left] < pivot) {
                left++;
            } 
            //searching number which is less than pivot, top down 
            while (array[right] > pivot) {
                right--;
            }
            // swap the values 
            if (left <= right) { 
                swap(array, left, right);
                //increment left index and decrement right index 
                left++; 
                right--; 
            } 
            
        } 
        return left; 
    }
    */
    
    void qsort(int[] array, int start, int end)
    {
        if(end == start) {
            return;
        }

        int beginIdx = start;
        int endIdx = end;
        int pivot = array[start];

        while(beginIdx <= endIdx)
        {
            //find the first one bigger than pivot from beginIdx
            while(pivot > array[beginIdx] && beginIdx < end)
            {
                beginIdx ++;
            }
            //find the first one smaller than pivot from endIdx
            while(pivot < array[endIdx] && endIdx > start)
            {
                endIdx--;
            }

            //swap
            if(beginIdx <= endIdx)
            {
                swap(array, beginIdx, endIdx);
            }
        }

        swap(array, start, beginIdx);
        if(beginIdx - 1 > start){
            qsort(array, start, beginIdx - 1);
        }
        if(end > beginIdx) {
            qsort(array, beginIdx, end);
        }
    }
    
    public void bubbleSort(int[] array) {
        for(int i = array.length - 1; i >= 0; i--) {
            for(int j = 0; j < i; j++) {
                if(array[j] > array[j+1]) {
                    swap(array, j, j+1);
                }
            }
        }
    }
    
    
    public void heapSort(int[] array) {
        buildHeap(array);
        for (int i = array.length - 1; i >= 0; i--) {
            swap(array, 0 , i);
            siftDown(array, 0, i);
        }
    }
    
    protected void buildHeap(int[] array){
        for (int i = (array.length - 1) / 2; i >= 0; i--) {
            siftDown(array, i, array.length);
        }
    }
    
    protected void siftDown(int[] array, int i, int end) {
        int child = i * 2 + 1;
        int start = i;
        while (child < end) {
            if (child + 1 < end
            && array[child] < array[child + 1]) {
                child++;
            }
            
            if(array[start] < array[child]) {
                swap(array, start, child);
                start = child;
                child = child * 2 + 1;
            } else {
                break;
            }
        }
    } 
    
    protected void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
    
    public void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print("  " + array[i]);
        }
        System.out.println("");
    }
    
    public boolean isAscend(int [] array) {
        for(int i = 0; i < array.length - 1; i++){
            if(array[i] > array[i+1]) {
                return false;
            }
        }
        return true;
    }

}
