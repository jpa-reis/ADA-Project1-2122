package LemmingsEvolved;

/**
 * ADA First Project - Lemmings Evolved
 * @author Joao Tiago Duarte dos Santos 57957
 * @author Joao Pedro Araujo dos Reis 58175
 */

public interface LemmingsEvolved {
    void initializeNRow(int lineNumber, int lineSize);
    void addLemming(int lineNumber,int positionInLine, char tribe, long power);
    void solveProblem();
    long[] answer();
}
