/**
 * ADA First Project - Lemmings Evolved
 * @author Joao Tiago Duarte dos Santos 57957
 * @author Joao Pedro Araujo dos Reis 58175
 */

import LemmingsEvolved.LemmingsEvolved;
import LemmingsEvolved.LemmingsEvolvedClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int NUMBER_OF_LINES = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numberOfTrials = Integer.parseInt(in.readLine());

        //Each trial is computed one at a time
        for(int i = 0; i < numberOfTrials; i++){

            LemmingsEvolved game = new LemmingsEvolvedClass();

            //Receive input and initialize rows in the game
            for(int l = 0; l <NUMBER_OF_LINES; l++) {
                int numberOfLemmingsInCurrentRow = Integer.parseInt(in.readLine());
                game.initializeNRow(l, numberOfLemmingsInCurrentRow);
                for (int p = 1; p <= numberOfLemmingsInCurrentRow; p++) {
                    String[] lemming = in.readLine().split(" ");
                    game.addLemming(l,p,lemming[0].charAt(0),Integer.parseInt(lemming[1]));
                }
            }

            //Compute and print the results
            game.solveProblem();
            long[] ans = game.answer();
            System.out.println(ans[0] + " " + ans[1]);
        }
    }
}
