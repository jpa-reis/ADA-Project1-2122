public class LemmingsEvolved {

    /*
    This two arrays save the information related to the Lemmings in the first line of the trial.
    The tribe and the power of a Lemming are saved on different arrays for better performance.
    The first Lemming is stored at position 1 (position 0 of both arrays is not used).
    */
    private char[] line1Tribes;
    private long[] line1Powers;
    //Amount of Lemmings in the first line
    private int firstLineSize;

    /*
    This two arrays save the information related to the Lemmings in the second line of the trial.
    The tribe and the power of a Lemming are saved on different arrays for better performance.
    The first Lemming is stored at position 1 (position 0 of both arrays is not used).
    */
    private char[] line2Tribes;
    private long[] line2Powers;
    //Amount of Lemmings in the second line
    private int secondLineSize;


    /*
    Matrix that saves the maximum score possible for the first x Lemmings in the first line and the first y Lemmings in
    the second line
    0 <= x <= firstLineSize
    0 <= y <= secondLineSize
    */
    private long[][] maxScores;

    /*
    Matrix that saves the minimum amount of pairs of Lemmings needed to achieve the maximum score possible for the first
    x Lemmings in the first line and the first y Lemmings in the second line
    0 <= x <= firstLineSize
    0 <= y <= secondLineSize
    */
    private int[][] minPairs;



    public LemmingsEvolved(){}

    /**
     * initializes the arrays for one line of Lemmings
     * @param lineNumber: line to be initialized (==0 if first line, ==1 if second line)
     * @param lineSize: size of the line to be initialized
     */
    public void initializeNRow(int lineNumber, int lineSize){
        if(lineNumber == 0){
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

    /**
     * Adds one Lemming to a line
     * @param lineNumber: line to be initialized (==0 if first line, ==1 if second line)
     * @param positionInLine: position of the Lemming in the line
     * @param tribe: tribe of the Lemming
     * @param power: power of the Lemming
     */
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

    /**
     * Fills maxScores and minPairs
     */
    public void solveProblem(){
        maxScores = new long[firstLineSize+1][secondLineSize+1];
        minPairs = new int[firstLineSize+1][secondLineSize+1];

        /*
        ----------------------------------------------------------
        For case x = 0 and y = 0 the result is 0 for both matrices
        The matrices are filled with 0 by default
        ----------------------------------------------------------
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
                if(maxScores[l-1][c] == maxScores[l][c] && maxScores[l][c-1] == maxScores[l][c]){
                    minPairs[l][c] = Math.min(minPairs[l-1][c], minPairs[l][c-1]);
                }
                else if(maxScores[l-1][c] != maxScores[l][c] && maxScores[l][c-1] != maxScores[l][c]){
                    minPairs[l][c] = minPairs[l-1][c-1] + 1;
                }
                else if(maxScores[l-1][c] != maxScores[l][c] && maxScores[l][c-1] == maxScores[l][c]){
                    minPairs[l][c] = minPairs[l][c-1];
                }
                else{
                    minPairs[l][c] = minPairs[l-1][c];
                }

            }
        }

    }

    /**
     * @return maximum score for the given problem
     */
    public long maxScore(){
        return maxScores[firstLineSize][secondLineSize];
    }

    /**
     * @return minimum amount of pairs of Lemmings needed to achieve the maximum score for the given problem
     */
    public long minPairs(){
        return minPairs[firstLineSize][secondLineSize];
    }

}
