public final class Settings {

    private static final String FILE_PATH = "resources/plantList.txt";
    private static final String DELIMITER = "\\t";

    private Settings() {

    }

    public static String getFilePath() {
        return FILE_PATH;   }
    public static String getDelimiter() {
        return DELIMITER;

        }
}
