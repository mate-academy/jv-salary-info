package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final byte DATE_POS = 0;
    private static final byte NAME_POS = 1;
    private static final byte HOURS_POS = 2;
    private static final byte SALARY_POS = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();

        result.append("Report for period ")
                .append(LocalDate.parse(dateFrom, DATE_FORMAT).format(DATE_FORMAT))
                .append(" - ").append(LocalDate.parse(dateTo, DATE_FORMAT).format(DATE_FORMAT));

        for (String name: names) {
            int periodSalary = 0;
            for (String record: data) {
                String[] splitRecord = record.split(" ");
                LocalDate date = LocalDate.parse(splitRecord[DATE_POS],DATE_FORMAT);
                if (splitRecord[NAME_POS].equals(name)
                        && (date.isAfter(LocalDate.parse(dateFrom, DATE_FORMAT))
                        || date.isEqual(LocalDate.parse(dateFrom, DATE_FORMAT)))
                        && (date.isBefore(LocalDate.parse(dateTo, DATE_FORMAT))
                        || date.isEqual(LocalDate.parse(dateTo, DATE_FORMAT)))) {
                    periodSalary += Integer.parseInt(splitRecord[HOURS_POS])
                            * Integer.parseInt(splitRecord[SALARY_POS]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(periodSalary);
        }

        return result.toString();
    }
}
