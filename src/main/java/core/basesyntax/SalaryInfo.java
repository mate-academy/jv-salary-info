package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws DateTimeParseException {

        try {
            int[] salarySums = new int[names.length];
            LocalDate dateStart = LocalDate.parse(dateFrom, DATE_FORMATTER);
            LocalDate dateFinish = LocalDate.parse(dateTo, DATE_FORMATTER);

            for (String value : data) {
                String[] separateValues = value.split(" ");
                LocalDate currentDate = LocalDate.parse(separateValues[0], DATE_FORMATTER);
                if (dateStart.compareTo(currentDate) <= 0
                        && dateFinish.compareTo(currentDate) >= 0) {
                    for (int j = 0; j < names.length; j++) {
                        if (names[j].equals(separateValues[1])) {
                            salarySums[j] += Integer.parseInt(separateValues[2])
                                    * Integer.parseInt(separateValues[3]);
                        }
                    }
                }
            }
            StringBuilder salarySummary = new StringBuilder(
                    String.format("Report for period %s - %s\n", dateFrom, dateTo));

            for (int i = 0; i < names.length; i++) {
                salarySummary.append(String.format("%s - %d\n", names[i], salarySums[i]));
            }
            return salarySummary.toString().trim();
        } catch (DateTimeParseException ex) {
            throw new RuntimeException("Date doesn't match format dd.mm.yyyy " + ex);
        }
    }
}
