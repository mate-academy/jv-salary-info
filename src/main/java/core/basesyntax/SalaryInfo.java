package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
            "dd.MM.yyyy");
    private static final int INDEX_HOUR = 2;
    private static final int INDEX_SALARY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        int salary = 0;

        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] infoToday = data[j].split(" ");
                if (infoToday[1].equals(names[i])) {
                    LocalDate currentDate = LocalDate.parse(infoToday[0], DATE_TIME_FORMATTER);
                    if (!currentDate.isBefore(LocalDate.parse(dateFrom, DATE_TIME_FORMATTER))
                            && !currentDate.isAfter(LocalDate.parse(dateTo, DATE_TIME_FORMATTER))) {
                        salary += Integer.parseInt(infoToday[INDEX_HOUR]) * Integer.parseInt(
                                infoToday[INDEX_SALARY]);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(salary);
            salary = 0;
        }
        return stringBuilder.toString();
    }
}
