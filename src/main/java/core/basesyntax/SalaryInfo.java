package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String europeanDatePattern = "dd.MM.yyyy";
    private static final DateTimeFormatter europeanDateFormatter = DateTimeFormatter
            .ofPattern(europeanDatePattern);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder namesData = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo)
                .append(System.lineSeparator());
        LocalDate from = LocalDate.parse(dateFrom,europeanDateFormatter);
        LocalDate to = LocalDate.parse(dateTo,europeanDateFormatter);
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (String workRecord : data) {
                String[] parts = workRecord.split(" ");
                LocalDate date = LocalDate.parse(parts[0], europeanDateFormatter);
                if (names[i].equals(parts[1])
                        && !date.isBefore(from)
                        && !date.isAfter(to)) {
                    salary += Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]);
                }

            }

            namesData.append(names[i])
                    .append(" - ")
                    .append(salary);

            if (i < names.length - 1) {
                namesData.append(System.lineSeparator());
            }

        }
        return namesData.toString();
    }
}
