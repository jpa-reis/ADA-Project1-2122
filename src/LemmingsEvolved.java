public class LemmingsEvolved {
    String[] line1Tribes;
    long[] line1Powers;

    String[] line2Tribes;
    long[] line2Powers;

    long[][] tab;

    int firstLineSize;
    int secondLineSize;



    public LemmingsEvolved(int firstLineSize,int secondLineSize){
        line1Tribes = new String[firstLineSize];
        line1Powers = new long[firstLineSize];
        line2Tribes = new String[secondLineSize];
        line2Powers = new long[secondLineSize];
        tab = new long[firstLineSize][secondLineSize];
    }

    public void addLemming(int lineNumber,int positionInLine, String tribe, long power){
        if(lineNumber == 0){
            line1Tribes[positionInLine] = tribe;
            line1Powers[positionInLine] = power;
        }
        else{
            line2Tribes[positionInLine] = tribe;
            line2Powers[positionInLine] = power;
        }
    }

    private void fillMatrix(){
        /*
        -----------------------------------------
        For case x = 0 and y = 0 the result is 0
        The matrix is filled with 0 by default
        -----------------------------------------
        */


        for(int l = 1; l < firstLineSize; l++){
            for(int c = 1; c < secondLineSize; c++) {
                long maxPower = Math.max(tab[l - 1][c], tab[l][c - 1]);
                if(line1Tribes[l].equals(line2Tribes[c])){
                    maxPower = Math.max(maxPower, line1Powers[l]+line2Powers[c]+tab[l-1][c-1]);
                }
                tab[l][c]=maxPower;
            }
        }

    }

    public long total(){
        fillMatrix();
        return tab[firstLineSize-1][secondLineSize];
    }

}
