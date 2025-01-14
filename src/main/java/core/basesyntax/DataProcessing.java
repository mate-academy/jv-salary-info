package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataProcessing {

    // Formatter do parsowania dat
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    // Metoda do konwersji String na LocalDate
    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    public List<Entry> processData(String[] data, LocalDate dateFrom, LocalDate dateTo) {
        List<Entry> entriesList = new ArrayList<>();

        for (String record : data) {
            String[] parts = record.trim().split("\\s+");
            LocalDate date = parseDate(parts[0]);
            String name = parts[1];
            int hours = Integer.parseInt(parts[2]);
            double rate = Double.parseDouble(parts[3]);

            if (!date.isBefore(dateFrom) && !date.isAfter(dateTo)) {
                // Tworzymy nowy obiekt Entry i dodajemy go do listy
                entriesList.add(new Entry(date, name, hours, rate));
            }
        }

        return entriesList;
    }
}
