import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Locale;


public class Plant implements Comparable<Plant> {

    private String name;
    private String notes;
    private LocalDate planted;
    public LocalDate watering;
    private Integer frequencyOfWatering;
    private BigDecimal frequencyOfWateringException = BigDecimal.ZERO;
    private LocalDate dateOfLastWatering = planted;


    public Plant(String name, String notes, LocalDate planted, LocalDate watering, Integer frequencyOfWatering)
            throws PlantException {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.watering = watering;
        this.frequencyOfWatering = frequencyOfWatering;
        setFrequencyOfWateringException(frequencyOfWateringException);
        setDateOfLastWatering(dateOfLastWatering);
    }

    public Plant(String name) {
        this.name = name;
        frequencyOfWatering = 7;

    }

    public Plant(String[] textValues, int lineNumber) throws PlantException {
        final int EXPECTED_LENGTH = 3;
        if (textValues.length != EXPECTED_LENGTH) {
            throw new PlantException(
                    "Řádek" + lineNumber + " musí mit " + EXPECTED_LENGTH + " hodnot: " + textValues);

        }

        try {
            this.name = textValues[0].trim();
            this.notes = textValues[1].trim();
            this.planted = LocalDate.parse(textValues[2].trim());
            this.watering = LocalDate.parse(textValues[3].trim());
            this.frequencyOfWatering = Integer.parseInt(textValues[4].trim());
            setFrequencyOfWateringException(new BigDecimal(textValues[5].trim()));
            this.dateOfLastWatering = LocalDate.parse(textValues[6].trim());

        }
        catch (NumberFormatException e) {
            throw new PlantException("Chyba pri převodu textoveho řetězce na číslo "
                    + lineNumber + ": " + e.getMessage());
        } catch (DateTimeParseException e) {
            throw new PlantException("Chyba při parsování dat na řádku "
                    + lineNumber + ": " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new PlantException("Chyba při parsování kategorie na řádku "
                    + lineNumber + ": " + e.getMessage());

        }
    }

        public String getWateringInfo () {
            LocalDate nextWatering = watering.plusDays(frequencyOfWatering);
            return ("Rostlina" + name + "Byla napsledy zalevana" + watering + " \n " +
                    " Doporucene pristi zalivani je: " + nextWatering + ".");

        }

        public void doWateringNow () {
            this.watering = LocalDate.now();
        }

    public Plant() {
            this.notes = "";
            planted = LocalDate.now();
            watering = LocalDate.now();

        }

        public String getNotes () {
            return notes;
        }

        public void setNotes (String notes){
            this.notes = notes;
        }

        public String getName () {
            return name;
        }

        public void setName (String name){
            this.name = name;
        }

        public LocalDate getPlanted () {
            return planted;
        }

        public void setPlanted (LocalDate planted){
            this.planted = planted;
        }

        public LocalDate getWatering () {
            return watering;
        }

        public void setWatering (LocalDate watering){
            this.watering = watering;
        }

        public Integer getFrequencyOfWatering () {
            return frequencyOfWatering;
        }

        public void setFrequencyOfWatering (Integer frequencyOfWatering){
            this.frequencyOfWatering = frequencyOfWatering;
        }

        public void setFrequencyOfWateringException (BigDecimal frequencyOfWateringException) throws PlantException {
            if (frequencyOfWateringException.compareTo(BigDecimal.ZERO) <= 0)
                throw new PlantException(" Frekvence nemuze byt negativni nebo rovne nule");
            this.frequencyOfWateringException = frequencyOfWateringException;
        }

        public void setDateOfLastWatering (LocalDate dateOfLastWatering) throws PlantException {
            if (dateOfLastWatering.isBefore(planted))
                throw new PlantException("Datum nemuze byt pred zasazenim rostliny");
            this.dateOfLastWatering = dateOfLastWatering;
        }


    @Override
    public int compareTo(Plant other) {
        return - name.compareTo(other.name);
    }

    public String toTextRecord(String delimiter) {
        String result = ""
                + name + delimiter
                + notes + delimiter
                + planted + delimiter
                + watering + delimiter
                + frequencyOfWatering + delimiter
                + frequencyOfWateringException + delimiter
                + dateOfLastWatering + delimiter ;
        return result;
    }

    @Override
        public String toString () {
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
