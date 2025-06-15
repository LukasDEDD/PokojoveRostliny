import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        FlowerListManagement manager = new FlowerListManagement();

        try {
            manager.readFromTextFile("resources/plantList.txt", "\t");
            for (Plant plant : manager.getAllPlants()) {
                System.out.println(plant.getWateringInfo());
            }
        } catch (PlantException e) {
            System.err.println("Chyba: " + e.getMessage());
        }

        Plant plant = new Plant ();
        try {

            manager.addAllPlants (
                    List.of(
                            new Plant("Ruze", "cervena", LocalDate.of(2025, 8, 14), LocalDate.of(2025, 8, 14), 7)
                    ));


            manager.writeToTextFile(Settings.getFilePath(),
                    Settings.getDelimiter());
        } catch (PlantException e) {
            System.err.println("Chyba: " + e.getMessage());
            System.err.println("Chyba při zápisu do souboru: "
                    + e.getMessage());   }



             }

    }
