import java.time.LocalDate;

public class Plant implements Comparable<Plant> {

    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private Integer frequencyOfWatering;
    private Integer frequencyOfWateringException;
    private LocalDate dateOfLastWatering;


    public Plant(String name, String notes, LocalDate planted, LocalDate watering, Integer frequencyOfWatering) throws PlantException {
        setName(name);
        setNotes(notes);
        setPlanted(planted);
        setWatering(watering);
        setFrequencyOfWatering(frequencyOfWatering);

        if (watering.isBefore(planted)) {
            throw new PlantException("Datum zalití nemůže nastat dřív než datum zasazení.");
        }

        setFrequencyOfWateringException(frequencyOfWatering);
        setDateOfLastWatering(watering);
    }


    public Plant(String name, Integer frequencyOfWatering) throws PlantException {
        this(name, "", LocalDate.now(), LocalDate.now(), frequencyOfWatering);
    }


    public Plant(String name) throws PlantException {
        this(name, "", LocalDate.now(), LocalDate.now(), 7);
    }


    public String getWateringInfo() {
        LocalDate nextWatering = watering.plusDays(frequencyOfWatering);
        return "Rostlina " + name + " byla naposledy zalita " + watering +
                ".\nDoporučené příští zalití je: " + nextWatering + ".";
    }

    public void doWateringNow() {
        LocalDate today = LocalDate.now();
        setWatering(today);
        setDateOfLastWatering(today);
    }

    public void whenToWater() {
        LocalDate nextWatering = this.watering.plusDays(this.frequencyOfWatering);
        if (!nextWatering.isAfter(LocalDate.now())) {
            System.out.println("Je čas zalít rostlinu: " + this.name);
        } else {
            System.out.println("Není třeba ještě zalévat: " + this.name);
        }
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Název nesmí být prázdný.");
        }
        this.name = name.trim();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = (notes == null) ? "" : notes.trim();
    }

    public Integer getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(Integer frequencyOfWatering) {
        if (frequencyOfWatering == null || frequencyOfWatering <= 0) {
            throw new IllegalArgumentException("Frekvence musí být větší než 0.");
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public Integer getFrequencyOfWateringException() {
        return frequencyOfWateringException;
    }

    public void setFrequencyOfWateringException(Integer frequencyOfWateringException) {
        if (frequencyOfWateringException == null || frequencyOfWateringException <= 0) {
            throw new IllegalArgumentException("Výjimková frekvence musí být větší než 0.");
        }
        this.frequencyOfWateringException = frequencyOfWateringException;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        if (planted == null) {
            throw new IllegalArgumentException("Datum zasazení nesmí být null.");
        }
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) {
        if (watering == null) {
            throw new IllegalArgumentException("Datum zalití nesmí být null.");
        }
        this.watering = watering;
    }

    public LocalDate getDateOfLastWatering() {
        return dateOfLastWatering;
    }

    public void setDateOfLastWatering(LocalDate dateOfLastWatering) {
        if (dateOfLastWatering == null) {
            throw new IllegalArgumentException("Datum poslední zálivky nesmí být null.");
        }
        this.dateOfLastWatering = dateOfLastWatering;
    }

    @Override
    public int compareTo(Plant other) {
        return name.compareToIgnoreCase(other.name);
    }


    public String toTextRecord(String delimiter) {
        return String.join(delimiter,
                name,
                notes,
                frequencyOfWatering.toString(),
                watering.toString(),
                planted.toString());
    }

    @Override
    public String toString() {
        return "Plant{" +
                "name='" + name + '\'' +
                ", notes='" + notes + '\'' +
                ", planted=" + planted +
                ", watering=" + watering +
                ", frequencyOfWatering=" + frequencyOfWatering +
                ", frequencyOfWateringException=" + frequencyOfWateringException +
                ", dateOfLastWatering=" + dateOfLastWatering +
                '}';
    }
}
