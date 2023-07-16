package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int FIRST_DATE_INDEX = 0;
    private static final int LAST_DATE_INDEX = 10;
    private static final int HOURS_INDEX = 2;
    private static final int HOUR_SALARY_INDEX = 3;

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
                    String dataStringFormat = line.substring(FIRST_DATE_INDEX, LAST_DATE_INDEX);
                    LocalDate localDateTarget = LocalDate.parse(dataStringFormat, FORMATTER);
                    if (localDateTarget.compareTo(localDateFrom) >= 0
                            && localDateTarget.compareTo(localDateTo) <= 0) {
                        String[] salaryOfTargetDay = line.split(" ");
                        int hours = Integer.parseInt(salaryOfTargetDay[HOURS_INDEX]);
                        int salaryPerHour = Integer.parseInt(salaryOfTargetDay[HOUR_SALARY_INDEX]);
                        targetSalary += hours * salaryPerHour;
                    }
                }
            }
            stringBuilder.append(targetSalary);
        }
        return stringBuilder.toString();
    }
}
