package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER;

    static {
        FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ");
            int targetSalary = 0;
            for (String line : data) {
                if (line.contains(name)) {
                    String dataStringFormat = line.substring(0, dateFrom.length());
                    LocalDate localDateTarget = LocalDate.parse(dataStringFormat, FORMATTER);
                    if (localDateTarget.compareTo(localDateFrom) >= 0
                            && localDateTarget.compareTo(localDateTo) <= 0) {
                        int substringValue = dateFrom.length() + name.length() + 2;
                        String[] salaryOfTargetDay = line.substring(substringValue).split(" ");
                        int firstValue = Integer.parseInt(salaryOfTargetDay[0]);
                        int secondValue = Integer.parseInt(salaryOfTargetDay[1]);
                        targetSalary += firstValue * secondValue;
                    }
                }
            }
            stringBuilder.append(targetSalary);
        }
        return stringBuilder.toString();
    }
}
