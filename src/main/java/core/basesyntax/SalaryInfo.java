package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int HOUR_INDEX = 2;
    public static final int INCOME_INDEX = 3;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        StringBuilder salaryInfo = new StringBuilder("Report for period ")
                                                    .append(dateFrom)
                                                    .append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String info : data) {
                String[] elementsOfInfo = info.split(" ");
                if (name.equals(elementsOfInfo[NAME_INDEX])) {
                    LocalDate workingDay = LocalDate.parse(elementsOfInfo[DATE_INDEX], DATE_FORMATTER);
                    if (fromDate.isBefore(workingDay) && toDate.isAfter(workingDay)
                            || fromDate.isEqual(workingDay) || toDate.equals(workingDay)) {
                        salary = salary + Integer.parseInt(elementsOfInfo[HOUR_INDEX])
                                * Integer.parseInt(elementsOfInfo[INCOME_INDEX]);
                    }
                }
            }
            salaryInfo.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return salaryInfo.toString();
    }
}
