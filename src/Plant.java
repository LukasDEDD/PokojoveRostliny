import java.time.LocalDate;

public class Plant {

    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private Integer frequencyOfWatering;

    public Plant(String name, String notes, LocalDate planted, LocalDate watering, Integer frequencyOfWatering) {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.watering = watering;
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public Plant() {
        this.notes = "";
        this.planted = LocalDate.now();
        this.watering = LocalDate.now();

}

    public Plant( String name) {
        this.name = name;
        this.frequencyOfWatering = 7;

    }
}