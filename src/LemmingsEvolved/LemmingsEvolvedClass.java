package LemmingsEvolved;

/**
 * ADA First Project - Lemmings Evolved
 * @author Joao Tiago Duarte dos Santos 57957
 * @author Joao Pedro Araujo dos Reis 58175
 */

public class LemmingsEvolvedClass implements LemmingsEvolved {

    
    /*
    * Both rows of lemmings have the same data structures and logic.
    *
    * THE FIRST LEMMING IS STORED AT POSITION 1 (position 0 of both arrays is not used).
    * The information relative to each lemming is stored in a position of both arrays. One array for the tribe,
    * one for the power.
    */
    private char[] line1Tribes;
    private long[] line1Powers;

    private char[] line2Tribes;
    private long[] line2Powers;

    //Size of each line
    private int firstLineSize;
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



    public LemmingsEvolvedClass(){}

    /**
     * Initializes the arrays for one line of Lemmings
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
     * @param lineNumber: line for the Lemming to be added (==0 if first line, ==1 if second line)
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

    /**
     * @return array with the maximum score[0] and the minimum amount of pairs of Lemmings[1]
     * needed to achieve the maximum score
     */
    public long[] answer(){
        return new long[]{maxScores[firstLineSize][secondLineSize], minPairs[firstLineSize][secondLineSize]};
    }

}
