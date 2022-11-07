package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom,
                                String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        LocalDate periodFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate periodTo = LocalDate.parse(dateTo, formatter);
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int y = 0; y < data.length; y++) {
                String[] partsOfData = data[y].split(" ");
                LocalDate workingDayOfEmployee = LocalDate.parse(partsOfData[0],formatter);
                boolean isDayInThePeriod = workingDayOfEmployee.compareTo(periodFrom) >= 0
                        && workingDayOfEmployee.compareTo(periodTo) <= 0;
                if (isDayInThePeriod && names[i].equals(partsOfData[1])) {
                    int workingHours = Integer.parseInt(partsOfData[2]);
                    int incomePerHour = Integer.parseInt(partsOfData[3]);
                    salary += workingHours * incomePerHour;
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
