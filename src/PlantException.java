public class PlantException extends RuntimeException {
    public PlantException(String Number) {
        super("Cislo nemuze byt negativni nebo rovne nule");
    }
}
