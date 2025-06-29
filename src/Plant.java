import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Plant implements Comparable<Plant> {

    private String name;
    private String notes;
    private Integer frequencyOfWatering;
    private LocalDate planted;
    private LocalDate watering;
    private Integer frequencyOfWateringException;
    private LocalDate dateOfLastWatering;


    public Plant(String name, String notes, Integer frequencyOfWatering, LocalDate planted, LocalDate watering)
            throws PlantException {
        if (frequencyOfWatering == null || frequencyOfWatering <= 0) {
            throw new PlantException("Frekvence musí být vyšší než 0.");
        }
        if (watering.isBefore(planted)) {
            throw new PlantException("Datum zalití nemůže být dřív než datum zasazení.");
        }
        this.name = name;
        this.notes = notes;
        this.frequencyOfWatering = frequencyOfWatering;
        this.frequencyOfWateringException = frequencyOfWatering;
        this.planted = planted;
        this.watering = watering;
        this.dateOfLastWatering = watering;
    }


    public Plant(String name, Integer frequencyOfWatering) throws PlantException {
        this(name, "", frequencyOfWatering, LocalDate.now(), LocalDate.now());
    }


    public Plant(String name) throws PlantException {
        this(name, "", 7, LocalDate.now(), LocalDate.now());
    }


    public Plant(String[] textValues, int lineNumber) throws PlantException {
        final int EXPECTED_LENGTH = 5;
        if (textValues.length != EXPECTED_LENGTH) {
            throw new PlantException("Řádek " + lineNumber + " musí mít " + EXPECTED_LENGTH + " hodnot: " + String.join(", ", textValues));
        }

        try {
            this.name = textValues[0].trim();
            this.notes = textValues[1].trim();
            this.frequencyOfWatering = Integer.parseInt(textValues[2].trim());
            this.planted = LocalDate.parse(textValues[3].trim());
            this.watering = LocalDate.parse(textValues[4].trim());

            if (frequencyOfWatering <= 0) {
                throw new PlantException("Frekvence musí být vyšší než 0 na řádku " + lineNumber);
            }
            if (watering.isBefore(planted)) {
                throw new PlantException("Zalití nemůže být před zasazením na řádku " + lineNumber);
            }

            this.frequencyOfWateringException = frequencyOfWatering;
            this.dateOfLastWatering = watering;

        } catch (NumberFormatException e) {
            throw new PlantException("Chyba při převodu čísla na řádku " + lineNumber + ": " + e.getMessage());
        } catch (DateTimeParseException e) {
            throw new PlantException("Chyba při parsování data na řádku " + lineNumber + ": " + e.getMessage());
        }
    }

    public String getWateringInfo() {
        LocalDate nextWatering = watering.plusDays(frequencyOfWatering);
        return "Rostlina " + name + " byla naposledy zalita " + watering + ".\nDoporučené příští zalití je: " + nextWatering + ".";
    }

    public void doWateringNow() {
        this.watering = LocalDate.now();
        this.dateOfLastWatering = this.watering;
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

    public LocalDate getDateOfLastWatering() {
        return dateOfLastWatering;
    }

    public void setDateOfLastWatering(LocalDate dateOfLastWatering) {
        this.dateOfLastWatering = dateOfLastWatering;
    }

    @Override
    public int compareTo(Plant other) {
        return name.compareTo(other.name);
    }

    public String toTextRecord(String delimiter) {
        return name + delimiter
                + notes + delimiter
                + frequencyOfWatering + delimiter
                + planted + delimiter
                + watering;
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
