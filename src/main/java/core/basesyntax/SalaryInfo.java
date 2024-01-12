package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final int SALARY_DATE_INDEX = 0;
    static final int NAME_INDEX = 1;
    static final int HOURS_WORKED_INDEX = 2;
    static final int HOURLY_WAGE_INDEX = 3;
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder().append("Report for period ");
        result.append(dateFrom).append(" - ").append(dateTo);
        LocalDate salaryDate;
        for (String name : names) {
            int salarySum = 0;
            for (String dataElements : data) {
                String[] dataElementsArr = dataElements.split(" ");
                salaryDate = LocalDate.parse(dataElementsArr[SALARY_DATE_INDEX], FORMATTER);
                if (name.equals(dataElementsArr[NAME_INDEX])
                        && (salaryDate.isEqual(dateFromFormatted)
                        || salaryDate.isAfter(dateFromFormatted))
                        && (salaryDate.isEqual(dateToFormatted)
                        || salaryDate.isBefore(dateToFormatted))) {
                    salarySum += Integer.parseInt(dataElementsArr[HOURS_WORKED_INDEX])
                            * Integer.parseInt(dataElementsArr[HOURLY_WAGE_INDEX]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salarySum);
        }
        return result.toString();
    }
}
