package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SalaryInfo {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        // Konwersja zakresu dat na LocalDate
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);

        // Przetworzenie danych do listy Entry
        DataProcessing dataProcessing = new DataProcessing();
        List<Entry> entries = dataProcessing.processData(data, from, to);

        // Obliczanie wynagrodzenia dla każdego pracownika
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());

        for (String name : names) {
            double totalSalary = 0;

            for (Entry entry : entries) {
                if (entry.getName().equals(name)) {
                    totalSalary += entry.calculateSalary();
                }
            }
            report.append(name).append(" - ").append((int) totalSalary)
                    .append(System.lineSeparator());
        }

        return report.toString().trim(); // Usunięcie zbędnego entera na końcu
    }
}
