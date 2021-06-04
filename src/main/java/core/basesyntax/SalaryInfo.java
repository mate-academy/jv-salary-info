package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_SALARY_PER_HOUR = 3;

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        LocalDate firstDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate secondDate = LocalDate.parse(dateTo, FORMATTER);
        builder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo);

        for (String name : names) {
            int eachSalary = 0;
            for (String information : data) {
                if (information.contains(name)) {
                    String currentDate = information.split(" ")[INDEX_OF_DATE];
                    LocalDate date = LocalDate.parse(currentDate, FORMATTER);
                    if ((date.isAfter(firstDate) || date.isEqual(firstDate))
                            && (date.isBefore(secondDate) || date.isEqual(secondDate))) {
                        eachSalary += Integer.parseInt(information.split(" ")[INDEX_OF_HOURS])
                                * Integer.parseInt(information.split(" ")
                                [INDEX_OF_SALARY_PER_HOUR]);
                    }
                }
            }
            builder.append("\n").append(name).append(" - ").append(eachSalary);
        }
        return builder.toString();
    }
}
