package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_SALARY_PER_HOUR = 2;
    private static final int INDEX_OF_WORKING_HOURS = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder information = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        String[] info;
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                info = line.split(" ");
                LocalDate currentDate = LocalDate.parse(info[INDEX_OF_DATE], FORMATTER);
                if (localDateFrom.compareTo(currentDate) <= 0
                        && localDateTo.compareTo(currentDate) >= 0
                        && info[INDEX_OF_NAME].equals(name)) {
                    salary += (Integer.parseInt(info[INDEX_OF_SALARY_PER_HOUR])
                            * Integer.parseInt(info[INDEX_OF_WORKING_HOURS]));
                }
            }
            information.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return information.toString();
    }
}
