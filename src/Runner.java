public class Runner {
    public static void main(String[] args) {
        Game game = new Game();
        game.deal();
        game.printHands();
        game.deal();
        game.printHands();
        game.deal();
        game.printHands();
        game.deal();
        game.printHands();
    }
}

