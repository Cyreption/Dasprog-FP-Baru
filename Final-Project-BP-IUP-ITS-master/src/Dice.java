public class Dice {
    private int numberOfSides;

    public Dice (int newNumberOfSides) {
        numberOfSides = newNumberOfSides;
    }

    public int roll () {
        return (int) (Math.random() * numberOfSides) + 1;
    }
}
