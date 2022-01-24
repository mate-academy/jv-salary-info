package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int DATE = 0;
    public static final int NAME = 1;
    public static final int WORKING_HOUR = 2;
    public static final int INCOME_PER_OUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateBegin = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateEnd = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        String[] dataForDay = null;
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                dataForDay = datum.split(" ");
                LocalDate currentDate = LocalDate.parse(dataForDay[DATE], FORMATTER);
                if ((currentDate.isAfter(dateBegin) && (currentDate.isBefore(dateEnd))
                        || currentDate.isEqual(dateEnd)) && name.equals(dataForDay[NAME])) {
                    salary += Integer.parseInt(dataForDay[INCOME_PER_OUR])
                            * Integer.parseInt(dataForDay[WORKING_HOUR]);
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return report.toString();
    }
}
