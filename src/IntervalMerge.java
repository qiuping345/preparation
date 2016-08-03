import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


public class IntervalMerge {

    public static class Interval {
        public int start;
        public int end;
        
        public Interval(int s, int e) {
            start = s;
            end = e;
        }
        
        public String toString(){
            return "[" + start + ", " + end + "]";
        }
    }
    
    public boolean intersect(Interval i1, Interval i2) {
        return ! (i2.start > i1.end || i2.end < i1.start);
    }
    
    public List<Interval> merge(List<Interval> arr) {
        ArrayList<Interval> result = new ArrayList<Interval>();
        if (arr == null || arr.size() == 0) {
            return result;
        }
        
        Collections.sort(arr, new Comparator(){
            public int compare(Object obj0, Object obj2) {
                return ((Interval)obj0).start - ((Interval)obj2).start;
            }
        });
        
        printListInterval(arr);
        
        Interval curr = null;
        Iterator<Interval> iter = arr.iterator();
        while(iter.hasNext()) {
            Interval i = iter.next();
            if(curr == null) {
                curr = i;
                continue;
            }
            
            if(intersect(curr, i)) {
                curr.start = Math.min(curr.start, i.start);
                curr.end = Math.max(curr.end, i.end);
            } else {
                result.add(curr);
                curr = i;
            }
        }
        result.add(curr);
        return result;
    }
    
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        IntervalMerge inst = new IntervalMerge();
        List<Interval> arr = Arrays.asList(
                new Interval(3, 6),
                new Interval(4, 7),
                new Interval(2, 4),
                new Interval(9, 11),
                new Interval(10, 18),
                new Interval(13, 16),
                new Interval(1, 3),
                new Interval(17, 19),
                new Interval(21, 28),
                new Interval(40, 71)
        );
        
        List<Interval> merged = inst.merge(arr);
        inst.printListInterval(merged);
    }

    public void printListInterval(List<Interval> list){
        Iterator<Interval> iter = list.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next().toString());
        }
    }
}
