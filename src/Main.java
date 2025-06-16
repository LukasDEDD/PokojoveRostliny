import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        FlowerListManagement manager = new FlowerListManagement();

        try {
            manager.readFromTextFile(Settings.getOriginalFilePath(), Settings.getDelimiter());

            for (Plant plant : manager.getAllPlants()) {
                System.out.println(plant.getWateringInfo());
            }
        } catch (PlantException e) {
            System.err.println("Chyba: " + e.getMessage());
        }

        try {
            manager.addPlant(new Plant("Ruze", "cervena", 7, LocalDate.of(2025, 8, 14), LocalDate.of(2025, 8, 14)));

            for (int i = 1; i <= 10; i++) {
                manager.addPlant(new Plant("Tulipan", "Tulipán na prodej " + i, 14, LocalDate.now(), LocalDate.now()));
            }

            manager.removePlant(2);

            manager.writeToTextFile(Settings.getOriginalFilePath(), Settings.getDelimiter());

            System.out.println("Zápis byl úspěšný.");

        } catch (PlantException e) {
            System.err.println("Chyba při zápisu nebo úpravě: " + e.getMessage());
        }

        try {
            manager.writeToTextFile(Settings.getOriginalFilePath(), Settings.getDelimiter());
            System.out.println("Zápis do souboru proběhl úspěšně: " + Settings.getOriginalFilePath());
        } catch (PlantException e) {
            System.err.println("Chyba při zápisu: " + e.getMessage());
        }
    }
}


