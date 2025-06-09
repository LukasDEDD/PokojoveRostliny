import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlowerListManagement {

    private List<FlowerListManagement> flowers = new ArrayList<>();


    public void addFlower(FlowerListManagement flower) {
        flowers.add(flower);
    }

    public void getFlower(FlowerListManagement flower) { flowers.get(0); }

    public void removeFlower(FlowerListManagement flower) { flowers.remove(0); }

    public List<FlowerListManagement> getflowers() {
        return new ArrayList<>(flowers); }

    public void whenToWaterPlant (Plant water) {
        LocalDate nextWatering = water.getWatering().plusDays(water.getFrequencyOfWatering());
        if (water.getWatering().isBefore(nextWatering)) {
            System.out.println("Je čas zalít rostlinu: " + water.getName());
        }
    }

    };



