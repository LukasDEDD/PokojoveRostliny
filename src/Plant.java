import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Plant implements Comparable<Plant> {


    private String name;
    private String notes;
    private Integer frequencyOfWatering;
    private LocalDate planted;
    private LocalDate watering;
    private BigDecimal frequencyOfWateringException;
    private LocalDate dateOfLastWatering;

    public Plant(String name, String notes, Integer frequencyOfWatering, LocalDate planted, LocalDate watering)
            throws PlantException {
        if (frequencyOfWatering == null || frequencyOfWatering <= 0) {
            throw new PlantException("Frekvence musí být vyšší než 0.");
        }
        if (watering.isBefore(planted)) {
            throw new PlantException("Datum zalití nemůže nastat dřív než datum zasazení.");
        }
        this.name = name;
        this.notes = notes;
        this.frequencyOfWatering = frequencyOfWatering;
        setFrequencyOfWateringException(BigDecimal.valueOf(this.frequencyOfWatering));
        this.planted = planted;
        this.watering = watering;
        setDateOfLastWatering(this.watering);
    }

    public Plant(String name, String notes, Integer frequencyOfWatering, LocalDate watering, LocalDate planted, BigDecimal frequencyOfWateringException, LocalDate dateOfLastWatering) {
        this.name = name;
        this.notes = "";
        this.frequencyOfWatering = 7;
        this.planted = LocalDate.now();
        this.watering = LocalDate.now();
        this.frequencyOfWateringException = frequencyOfWateringException;
        setDateOfLastWatering(this.watering);
    }


    public Plant(String[] textValues, int lineNumber) throws PlantException {
        final int EXPECTED_LENGTH = 5;
        if (textValues.length != EXPECTED_LENGTH) {
            throw new PlantException(
                    "Řádek " + lineNumber + " musí mít " + EXPECTED_LENGTH + " hodnot: " + textValues);
        }

        try {
            this.name = textValues[0].trim();
            this.notes = textValues[1].trim();
            this.frequencyOfWatering = Integer.parseInt(textValues[2].trim());
            this.planted = LocalDate.parse(textValues[3].trim());
            this.watering = LocalDate.parse(textValues[4].trim());
            setFrequencyOfWateringException(BigDecimal.valueOf(this.frequencyOfWatering));

            setDateOfLastWatering(this.watering);
        } catch (NumberFormatException e) {
            throw new PlantException("Chyba pri převodu textoveho řetězce na číslo " + lineNumber + ": " + e.getMessage());
        } catch (DateTimeParseException e) {
            throw new PlantException("Chyba při parsování dat na řádku " + lineNumber + ": " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new PlantException("Chyba při parsování kategorie na řádku " + lineNumber + ": " + e.getMessage());
        }
    }

    public String getWateringInfo() {
        LocalDate nextWatering = watering.plusDays(frequencyOfWatering);
        return ("Rostlina " + name + " byla naposledy zalita " + watering + ".\nDoporučené příští zalití je: " + nextWatering + ".");
    }

    public void doWateringNow() {
        this.watering = LocalDate.now();
        setDateOfLastWatering(this.watering);
    }



    // Gettery a settery
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
        if (frequencyOfWateringException.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PlantException("Frekvence nemůže být záporná nebo nulová.");
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
        return "Plant{" + "name='" + name + '\'' + ", notes='" + notes + '\'' + ", planted=" + planted + ", watering=" + watering + ", frequencyOfWatering=" + frequencyOfWatering + ", frequencyOfWateringException=" + frequencyOfWateringException + ", dateOfLastWatering=" + dateOfLastWatering + '}';
    }
}