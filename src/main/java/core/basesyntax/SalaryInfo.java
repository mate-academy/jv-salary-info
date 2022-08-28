package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SPACE_DASH_SPACE = " - ";
    private static final String SPACE_SAPERATOR = " ";
    private static final int DATE_INDEX = 0;
    private static final int DAY_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom).append(SPACE_DASH_SPACE).append(dateTo);
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int amountSalary = 0;
            for (String currentData : data) {
                if (currentData.indexOf(name) >= 0) {
                    String[] splitCurrentData = currentData
                            .split(SPACE_SAPERATOR);
                    LocalDate currentDate = LocalDate
                            .parse(splitCurrentData[DATE_INDEX], FORMATTER);
                    int amountDays = Integer.parseInt(splitCurrentData[DAY_INDEX]);
                    int salaryPerHour = Integer.parseInt(splitCurrentData[SALARY_INDEX]);
                    if (currentDate.equals(startDate) || currentDate.isAfter(startDate)
                            && (currentDate.equals(endDate) || currentDate.isBefore(endDate))) {
                        amountSalary += amountDays * salaryPerHour;
                    }
                }
            }
            result.append(System.lineSeparator()).append(name)
                    .append(SPACE_DASH_SPACE)
                    .append(amountSalary);
        }
        return result.toString();
    }
}
