import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FlowerListManagement {

    private List<Plant> flowers = new ArrayList<>();

    public void addFlower(Plant flower) {
        flowers.add(flower);
    }

    public Plant getFlower(int index) {
        return flowers.get(index);
    }

    public void removeFlower(int index) {
        flowers.remove(index);
    }

    public List<Plant> getFlowers() {
        return new ArrayList<>(flowers);
    }

    //  Metoda, která vrací seznam rostlin, které je třeba zalít
    public List<Plant> getFlowersToWater() {
        List<Plant> flowersToWater = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (Plant flower : flowers) {
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
        flowers.sort(Comparator.comparing(Plant::getName).thenComparing(Plant::getWatering));
    }
}



