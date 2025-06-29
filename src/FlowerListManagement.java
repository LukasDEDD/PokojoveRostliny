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

    public void removePlant(int index) throws PlantException {
        if (index < 0 || index >= plants.size()) {
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
                    try {
                        // Nové pořadí: name, notes, frequency, watering, planted
                        String name = parts[0].trim();
                        String notes = parts[1].trim();
                        Integer frequency = Integer.parseInt(parts[2].trim());
                        LocalDate watering = LocalDate.parse(parts[3].trim());
                        LocalDate planted = LocalDate.parse(parts[4].trim());

                        Plant plant = new Plant(name, notes, planted, watering, frequency);
                        plants.add(plant);
                    } catch (Exception e) {
                        throw new PlantException("Chyba na řádku " + lineNumber + ": " + e.getMessage(), e);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new PlantException("Soubor '" + filePath + "' nelze otevřít: " + e.getLocalizedMessage());
        }
        System.out.println("Line " + lineNumber + ": " + Arrays.toString(parts));
    }

    public void writeToTextFile(String filePath, String delimiter) throws PlantException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
            for (Plant plant : plants) {
                writer.println(plant.toTextRecord(delimiter));
            }
        } catch (IOException e) {
            throw new PlantException("Chyba při zápisu do souboru: " + e.getMessage());
        }
    }

    public List<Plant> getFlowersToWater() {
        List<Plant> flowersToWater = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (Plant flower : plants) {
            LocalDate nextWatering = flower.getWatering().plusDays(flower.getFrequencyOfWatering());
            if (!nextWatering.isAfter(today)) {
                flowersToWater.add(flower);
            }
        }

        return flowersToWater;
    }

    public void sortFlowers() {
        plants.sort(Comparator.comparing(Plant::getName).thenComparing(Plant::getWatering));
    }
}
