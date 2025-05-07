package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOUR_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom,
                                String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        LocalDate periodFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate periodTo = LocalDate.parse(dateTo, FORMATTER);
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int y = 0; y < data.length; y++) {
                String[] partsOfData = data[y].split(" ");
                LocalDate workingDayOfEmployee = LocalDate.parse(partsOfData[0], FORMATTER);
                boolean isDayInThePeriod = workingDayOfEmployee.compareTo(periodFrom) >= 0
                        && workingDayOfEmployee.compareTo(periodTo) <= 0;
                if (isDayInThePeriod && names[i].equals(partsOfData[NAME_INDEX])) {
                    int workingHours = Integer.parseInt(partsOfData[WORKING_HOUR_INDEX]);
                    int incomePerHour = Integer.parseInt(partsOfData[INCOME_PER_HOUR_INDEX]);
                    salary += workingHours * incomePerHour;
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
