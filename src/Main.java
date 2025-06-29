import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        FlowerListManagement manager = new FlowerListManagement();

        try {
            manager.readFromTextFile(Settings.getOriginalFilePath(), Settings.getDelimiter());
            manager.sortFlowers();
            for (Plant plant : manager.getAllPlants()) {
                System.out.println(plant.getWateringInfo());
            }
        } catch (PlantException e) {
            System.err.println("Chyba: " + e.getMessage());
        }

        try {

            manager.addPlant(new Plant("Ruze", "cervena", LocalDate.of(2025, 8, 14), LocalDate.of(2025, 8, 14), 7));

            for (int i = 1; i <= 10; i++) {
                manager.addPlant(new Plant(
                        "Tulipan",
                        "Tulipán na prodej " + i,
                        LocalDate.now(),
                        LocalDate.now(),
                        14
                ));
            }

            manager.removePlant(2);
            manager.sortFlowers();
            manager.writeToTextFile(Settings.getNewFilePath(), Settings.getDelimiter());

            System.out.println("Zápis do nového souboru také proběhl.");

        } catch (PlantException e) {
            System.err.println("Chyba při zápisu nebo úpravě: " + e.getMessage());
        }
    }
}
