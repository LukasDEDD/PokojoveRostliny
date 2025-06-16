import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class FlowerListManagement {

    private List<Plant> plants = new ArrayList<>();

    public Plant getPlant(int index) {
        return plants.get(index);
    }

    public void addPlant(Plant plant) {
        plants.add(plant);

    }

    public void removePlant(int index) throws  PlantException {
        if (index < 0 || index > plants.size()) {
            throw new PlantException("Neplatný index: " + index);
        }
        plants.remove(index);
        System.out.println("Rostlina číslo " + (index + 1) + " byla prodána");
    }

    public void addAllPlants(List<Plant> plants) {
        this.plants.addAll(plants);
    }

    public List<Plant> getAllPlants() {
        return new ArrayList<>(plants);
    }


    public List<Plant> getPlants() {
        return new ArrayList<>(plants);

    }

    public void readFromTextFile(String filePath, String delimiter) throws PlantException {
        int lineNumber = 0;
        String[] parts = null;
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filePath)))) {
            plants.clear();
            while (scanner.hasNextLine()) {
                lineNumber++;
                String record = scanner.nextLine();
                if (!record.isEmpty()) {
                    parts = record.split(delimiter);
                    Plant plant = new Plant(parts, lineNumber);
                    plants.add(plant);
                }
            }
        } catch (FileNotFoundException e) {
            throw new PlantException("Soubor '" + filePath + "' nelze otevřít: " + e.getLocalizedMessage());
        }
        System.out.println("Line " + lineNumber + ": " + Arrays.toString(parts));
    }

    public void writeToTextFile(String filePath, String delimiter) throws PlantException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(
                new FileWriter(filePath)))) {
            for (Plant plant : plants) {
                writer.println(plant.toTextRecord(delimiter));
            }
        } catch (IOException e) {
            throw new PlantException("Chyba při zápisu do souboru: "
                    + e.getMessage());
            }
        }


    //  Metoda, která vrací seznam rostlin, které je třeba zalít
    public List<Plant> getFlowersToWater() {
        List<Plant> flowersToWater = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (Plant flower : plants) {
            LocalDate nextWatering = flower.getWatering().plusDays(flower.getFrequencyOfWatering());
            if (!nextWatering.isAfter(today)) { // pokud je datum zalévání dnes nebo dřív
                flowersToWater.add(flower);
            }
        }

        return flowersToWater;
    }

    // Pomocná metoda: vypíše info o tom, zda je čas na zalití jedné rostliny
    public void whenToWaterFlower(Plant flower) {
        LocalDate nextWatering = flower.getWatering().plusDays(flower.getFrequencyOfWatering());
        if (!nextWatering.isAfter(LocalDate.now())) {
            System.out.println("Je čas zalít rostlinu: " + flower.getName());
        } else {
            System.out.println("Není třeba ještě zalévat: " + flower.getName());
        }
    }

    public void sortFlowers() {
        plants.sort(Comparator.comparing(Plant::getName).thenComparing(Plant::getWatering));
    }

}



