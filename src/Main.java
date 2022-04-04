import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int NUMBER_OF_LINES = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numberOfTrials = Integer.parseInt(in.readLine());

        //get the input for every trial
        for(int i = 0; i < numberOfTrials; i++){
            LemmingsEvolved game = new LemmingsEvolved();
            for(int l = 0; l <NUMBER_OF_LINES; l++) {
                int numberOfLemmingsInCurrentRow = Integer.parseInt(in.readLine());
                game.initializeNRow(l, numberOfLemmingsInCurrentRow);
                for (int p = 1; p <= numberOfLemmingsInCurrentRow; p++) {
                    String[] lemming = in.readLine().split(" ");
                    game.addLemming(l,p,lemming[0].charAt(0),Integer.parseInt(lemming[1]));
                }
            }
            game.solveProblem();
            System.out.println(game.maxScore() + " " + game.minPairs());
        }
    }
}
