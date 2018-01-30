public class Solution {
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int lis(final List<Integer> A) {
        // if the input is null or if size is zero, then return
        if (A == null || A.size() == 0)
          return 0;
        
        ArrayList<Integer> listOfIncSeq = new ArrayList<Integer>(); 
        
        for (int currentValue: A) {
            if (listOfIncSeq.size() == 0 || currentValue > listOfIncSeq.get(listOfIncSeq.size() - 1)) {
                listOfIncSeq.add(currentValue);
            } else {
                int i = 0; 
                int j = listOfIncSeq.size() - 1;
                while (i < j) {
                    int mid = (i + j) / 2;
                    if (listOfIncSeq.get(mid) < currentValue) {
                        i = mid + 1;
                    } else {
                        j = mid;
                    }
                }
                listOfIncSeq.set(j, currentValue);
            }
        }
     return listOfIncSeq.size();
    }
}
