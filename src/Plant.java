import java.math.BigDecimal;
import java.time.LocalDate;

public class Plant {

    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private Integer frequencyOfWatering;
    private BigDecimal frequencyOfWateringException = BigDecimal.ZERO;

    public Plant(String name, String notes, LocalDate planted, LocalDate watering, Integer frequencyOfWatering) throws PlantException {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.watering = watering;
        this.frequencyOfWatering = frequencyOfWatering;
        setFrequencyOfWateringException(frequencyOfWateringException);
    }

    public String getWateringInfo() {
        LocalDate nextWatering = watering.plusDays(frequencyOfWatering);
        return ("Plant" + name + "had the last watering of the day." + watering + "/n " +
                " the next recommended watering must be: " + nextWatering + "Days");

    }

    public void doWateringNow() {
        this.watering = LocalDate.now();
    }

    public Plant() {
        this.notes = "";planted = LocalDate.now();watering = LocalDate.now();

}

    public Plant( String name) {
        this.name = name;frequencyOfWatering = 7;

    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) {
        this.watering = watering;
    }

    public Integer getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(Integer frequencyOfWatering) {
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public void setFrequencyOfWateringException(BigDecimal frequencyOfWateringException) throws PlantException {
        if(frequencyOfWateringException.compareTo(BigDecimal.ZERO)<=0)
            throw new PlantException(" Frequency cannot  negativ or Zero");
             this.frequencyOfWatering = frequencyOfWatering;
            }
}