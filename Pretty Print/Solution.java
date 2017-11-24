public class Solution {
  public ArrayList<ArrayList<Integer>> prettyPrint(int a) {
    int start = a;
    int numRowAndCol = start * 2 - 1;
    ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();

    int currentNum = start;
    int thisRowNum = start;
    int countInThisRow = 0;
    int maxInThisRow = numRowAndCol;

    for (int i = 0; i <= numRowAndCol/2; i++) {
      ArrayList<Integer> list = new ArrayList<Integer>();
      lists.add(list);
      for (int j = 0; j <= numRowAndCol/2; j++) {
        list.add(currentNum);
        if (currentNum == thisRowNum) {
          countInThisRow++;
        }
        if (thisRowNum < currentNum) {
          currentNum--;
        }
      }

      for (int j = numRowAndCol/2 + 1; j < numRowAndCol; j++) {
        if (countInThisRow == maxInThisRow) {
          currentNum++;
        }
        list.add(currentNum);
        if (currentNum == thisRowNum) {
          countInThisRow++;
        }
      }

      currentNum = a;
      thisRowNum = --start;
      countInThisRow = 0;
      maxInThisRow = numRowAndCol - (i + 1) * 2;
    }

    start = 1;
    currentNum = a;
    thisRowNum = ++start;
    countInThisRow = 0;
    maxInThisRow = thisRowNum * 2 - 1;

    for (int i = numRowAndCol/2 + 1; i < numRowAndCol; i++) {
      ArrayList<Integer> list = new ArrayList<Integer>();
      lists.add(list);
      for (int j = 0; j <= numRowAndCol/2; j++) {
        list.add(currentNum);
        if (currentNum == thisRowNum) {
          countInThisRow++;
        }
        if (thisRowNum < currentNum) {
          currentNum--;
        }
      }

      for (int j = numRowAndCol/2 + 1; j < numRowAndCol; j++) {
        if (countInThisRow == maxInThisRow) {
          currentNum++;
        }
        list.add(currentNum);
        if (currentNum == thisRowNum) {
          countInThisRow++;
        }
      }

      currentNum = a;
      thisRowNum = ++start;
      countInThisRow = 0;
      maxInThisRow = thisRowNum * 2 - 1;
    }

    return lists;
  }
}
