package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_TIMES = 2;
    private static final int INDEX_SALARY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);

        int salary = 0;
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] splittedData = data[j].split(" ");
                LocalDate testDate = LocalDate.parse(splittedData[INDEX_DATE], FORMATTER);
                if (isWithinRange(testDate, localFrom, localTo)
                        && names[i].equals(splittedData[INDEX_NAME])) {
                    salary += Integer.parseInt(splittedData[INDEX_SALARY])
                            * Integer.parseInt(splittedData[INDEX_TIMES]);
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(names[i]).append(" - ")
                    .append(salary);
            salary = 0;
        }

        return stringBuilder.toString();
    }

    private static boolean isWithinRange(LocalDate testDate, LocalDate from, LocalDate to) {
        return !(testDate.isBefore(from) || testDate.isAfter(to));
    }
}
