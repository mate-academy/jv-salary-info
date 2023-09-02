package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final String SEPARATOR = " ";
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int HOUR_INDEX = 2;
    public static final int M_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int salary;
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder stringBuilder = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name : names) {
            salary = 0;
            for (String info : data) {
                String[] elementsOfInfo = info.split(SEPARATOR);
                if (name.equals(elementsOfInfo[NAME_INDEX])) {
                    LocalDate workingDay = LocalDate.parse(elementsOfInfo[DATE_INDEX], FORMATTER);
                    if (fromDate.isBefore(workingDay) && toDate.isAfter(workingDay)
                            || fromDate.isEqual(workingDay) || toDate.equals(workingDay)) {
                        salary += Integer.parseInt(elementsOfInfo[HOUR_INDEX])
                                * Integer.parseInt(elementsOfInfo[M_PER_HOUR_INDEX]);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
