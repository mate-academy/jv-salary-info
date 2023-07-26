package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String DELIMITER = " - ";
    private static final int DATA_INDEX = 0;
    private static final int HOURS_INDEX = 2;
    private static final int HOUR_SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom)
                .append(DELIMITER).append(dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            stringBuilder.append(System.lineSeparator()).append(name).append(DELIMITER);
            int targetSalary = 0;
            for (String line : data) {
                if (line.contains(name)) {
                    String[] dataOfTargetDay = line.split(" ");
                    String dataStringFormat = dataOfTargetDay[DATA_INDEX];
                    LocalDate localDateTarget = LocalDate.parse(dataStringFormat, FORMATTER);
                    if (localDateTarget.compareTo(localDateFrom) >= 0
                            && localDateTarget.compareTo(localDateTo) <= 0) {
                        int hours = Integer.parseInt(dataOfTargetDay[HOURS_INDEX]);
                        int salaryPerHour = Integer.parseInt(dataOfTargetDay[HOUR_SALARY_INDEX]);
                        targetSalary += hours * salaryPerHour;
                    }
                }
            }
            stringBuilder.append(targetSalary);
        }
        return stringBuilder.toString();
    }
}
