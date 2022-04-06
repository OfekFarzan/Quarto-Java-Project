public class Main {
    public static void main(String[] args) throws Exception {
        GameLogic qb = new GameLogic();            // the actual board
        AiComputer compy = new AiComputer(qb);
        GameBoardConsole qcon = new GameBoardConsole(qb, compy);

        qcon.go();
    }
}
