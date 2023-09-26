package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, formatter);
        for (String name : names) {
            int sum = 0;
            for (String note : data) {
                try {
                    String[] dataSeparated = note.split(" ");
                    LocalDate parsedNoteDate = LocalDate.parse(dataSeparated[0], formatter);
                    if (!parsedNoteDate.isBefore(parsedDateFrom)
                            && !parsedNoteDate.isAfter(parsedDateTo)) {
                        if (dataSeparated[1].equals(name)) {
                            sum += Integer.parseInt(dataSeparated[2])
                                    * Integer.parseInt(dataSeparated[3]);
                        }
                    }
                } catch (EmptyStringException e) {
                    System.out.println("Data in array cannot be empty.");
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(sum);
        }
        return builder.toString();
    }
}
