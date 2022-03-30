public class Main {

    public static final int NUMBER_OF_LINES = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numberOfTrials = Integer.parseInt(in.readLine());
        LemmingsEvolved game = new LemmingsEvolved(numberOfTrials, NUMBER_OF_LINES);

        //get the input for every line of Lemmings
        for(int i = 0; i < numberOfTrials*NUMBER_OF_LINES; i++){
            int numberOfLemmingsInCurrentRow = Integer.parseInt(in.readLine());
            for (int l = 0; l < numberOfLemmingsInCurrentRow; l++) {
                String[] lemming = in.readLine().split(" ");
                Lemming lem = new Lemming(lemming[0], Integer.parseInt(lemming[1]));
                game.fillLemming(lem, i, l);
            }


        }

    }
}
