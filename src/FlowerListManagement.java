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


}





