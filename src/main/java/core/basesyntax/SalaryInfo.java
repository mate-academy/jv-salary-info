package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder employeeSalaryOfPeriod = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate dateBegin = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateEnd = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int totalSalary = 0;
            for (String lineOfData : data) {
                String[] splittedLineOfData = lineOfData.split(" ");
                if (((LocalDate.parse(splittedLineOfData[DATE_INDEX], FORMATTER).isAfter(dateBegin))
                        || (LocalDate.parse(splittedLineOfData[DATE_INDEX], FORMATTER)
                        .equals(dateBegin)))
                        & ((LocalDate.parse(splittedLineOfData[DATE_INDEX], FORMATTER)
                        .isBefore(dateEnd) || LocalDate.parse(splittedLineOfData[DATE_INDEX],
                                FORMATTER).equals(dateEnd)))
                        & (name.equals(splittedLineOfData[NAME_INDEX]))) {
                    int hours = Integer.parseInt(splittedLineOfData[HOURS_INDEX]);
                    int hourSalary = Integer.parseInt(splittedLineOfData[INCOME_PER_HOUR_INDEX]);
                    totalSalary = totalSalary + hourSalary * hours;
                }
            }
            employeeSalaryOfPeriod
                    .append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
        }
        return employeeSalaryOfPeriod.toString();
    }
}
