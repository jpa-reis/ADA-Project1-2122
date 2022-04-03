import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static final int NUMBER_OF_LINES = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numberOfTrials = Integer.parseInt(in.readLine());
        String[] totals = new String[numberOfTrials];

        //get the input for every line of Lemmings
        for(int i = 0; i < numberOfTrials; i++){
            LemmingsEvolved game = new LemmingsEvolved(numberOfTrials, NUMBER_OF_LINES);
            for(int l = 0; l <NUMBER_OF_LINES; l++) {
                int numberOfLemmingsInCurrentRow = Integer.parseInt(in.readLine());
                for (int p = 0; p < numberOfLemmingsInCurrentRow; p++) {
                    String[] lemming = in.readLine().split(" ");
                    game.addLemming(l,p,lemming[0],Integer.parseInt(lemming[1]));
                }
            }
            System.out.println(game.total());
        }
    }
}
