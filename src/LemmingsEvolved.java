public class LemmingsEvolved {

    private char[] line1Tribes;
    private long[] line1Powers;

    private char[] line2Tribes;
    private long[] line2Powers;

    private long[][] maxScores;
    private int[][] minPairs;

    private int firstLineSize;
    private int secondLineSize;



    public LemmingsEvolved(){

    }

    public void initializeNRow(int line, int lineSize){
        if(line == 0){
            line1Tribes = new char[lineSize+1];
            line1Powers = new long[lineSize+1];
            firstLineSize = lineSize;
        }
        else{
            line2Tribes = new char[lineSize+1];
            line2Powers = new long[lineSize+1];
            secondLineSize = lineSize;
        }
    }

    public void addLemming(int lineNumber,int positionInLine, char tribe, long power){
        if(lineNumber == 0){
            line1Tribes[positionInLine] = tribe;
            line1Powers[positionInLine] = power;
        }
        else{
            line2Tribes[positionInLine] = tribe;
            line2Powers[positionInLine] = power;
        }
    }

    public void solveProblem(){
        maxScores = new long[firstLineSize+1][secondLineSize+1];
        minPairs = new int[firstLineSize+1][secondLineSize+1];
        /*
        -----------------------------------------
        For case x = 0 and y = 0 the result is 0
        The matrix is filled with 0 by default
        -----------------------------------------
        */


        for(int l = 1; l <= firstLineSize; l++){
            for(int c = 1; c <= secondLineSize; c++) {

                //fill score matrix
                long maxPower = Math.max(maxScores[l - 1][c], maxScores[l][c - 1]);
                if(line1Tribes[l] == line2Tribes[c]){
                    maxPower = Math.max(maxPower, line1Powers[l]+line2Powers[c]+ maxScores[l-1][c-1]);
                }
                maxScores[l][c]=maxPower;


                //fill min pair matrix
                if(maxScores[l-1][c] == maxScores[l][c]){
                    if(maxScores[l][c-1] == maxScores[l][c]){
                        minPairs[l][c] = Math.min(minPairs[l-1][c], minPairs[l][c-1]);
                    }
                    else{
                        minPairs[l][c] = minPairs[l-1][c];
                    }
                }
                else{
                    if(maxScores[l][c-1] == maxScores[l][c]){
                        minPairs[l][c] = minPairs[l][c-1];
                    }
                    else{
                        minPairs[l][c] = minPairs[l-1][c-1] + 1;
                    }
                }

            }
        }

    }

    public long maxScore(){
        return maxScores[firstLineSize][secondLineSize];
    }
    public long minPairs(){
        return minPairs[firstLineSize][secondLineSize];
    }

}
