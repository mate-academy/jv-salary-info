package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SPACE_REGEX = "\\s";
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_SALARY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder reportResult = new StringBuilder();
        reportResult.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int sumOfSalaryPerHour = 0;

            for (String stringFromData : data) {
                String[] splitArray = stringFromData.split(SPACE_REGEX);
                boolean existInDateRange =
                        existInDateRange(splitArray[INDEX_OF_DATE], startDate, endDate);
                boolean existName = stringFromData.contains(name);

                if (existName && existInDateRange) {
                    sumOfSalaryPerHour +=
                            calcSalary(splitArray[INDEX_OF_HOURS],
                                        splitArray[INDEX_OF_SALARY_PER_HOUR]);
                }
            }
            reportResult.append(System.lineSeparator()).append(name).append(" - ")
                    .append(sumOfSalaryPerHour);
        }
        return reportResult.toString();
    }

    public boolean existInDateRange(String date, LocalDate startDate, LocalDate endDate) {
        LocalDate localDate = LocalDate.parse(date, FORMATTER);
        return localDate.isAfter(startDate) && localDate.isBefore(endDate)
            || localDate.isEqual(startDate) || localDate.isEqual(endDate);
    }

    public int calcSalary(String numOfHoursString, String salaryPerHourString) {
        int numOfHours = Integer.parseInt(numOfHoursString);
        int salaryPerHour = Integer.parseInt(salaryPerHourString);
        return numOfHours * salaryPerHour;
    }

}
