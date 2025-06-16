import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {


        FlowerListManagement manager = new FlowerListManagement();

        try {
            manager.readFromTextFile("resources/PlantList.txt", "\t");
            for (Plant plant : manager.getAllPlants()) {
                System.out.println(plant.getWateringInfo());
            }
        } catch (PlantException e) {
            System.err.println("Chyba: " + e.getMessage());
        }


        try {

            manager.addPlant (

                            new Plant("Ruze", "cervena", 7, LocalDate.of(2025, 8, 14), LocalDate.of(2025, 8, 14))
                    );


            manager.writeToTextFile(Settings.getNewFilePath(),
                    Settings.getDelimiter());
        } catch (PlantException e) {
            System.err.println("Chyba: " + e.getMessage());
            System.err.println("Chyba při zápisu do souboru: "
                    + e.getMessage());   }

        try {
        for (int i = 1; i < 11; i++) {
            manager.addPlant(
                    new Plant("Tulipan",
                            "Tulipán na prodej " + i,
                            14,
                            LocalDate.now(), LocalDate.now()
                    )
            );
        }

        } catch (PlantException e) {
            System.err.println("Chyba při zápisu do souboru: " + e.getMessage());
        }

        try {
            manager.removePlant(2);
        }
        catch (PlantException e) {
            System.err.println("Chyba při odstraňování rostliny: " + e.getMessage());
        }


    }
        }


