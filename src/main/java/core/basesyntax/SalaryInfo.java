package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String PATTERN_OF_DATE = "dd.MM.yyyy";
    private static final String REGEX_OF_DATA = " ";
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME_EMPLOYEE = 1;
    private static final int INDEX_SALARY = 2;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_OF_DATE);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String dataOfEmployee : data) {
                if (getNameFromData(dataOfEmployee).equals(name)
                        && getDateFromData(dataOfEmployee).compareTo(startDate) >= 0
                        && getDateFromData(dataOfEmployee).compareTo(endDate) <= 0) {
                    salary += getSalaryFromDay(dataOfEmployee);
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return builder.toString();
    }

    public String getNameFromData(String dataOfEmployee) {
        String[] arrayOfData = dataOfEmployee.split(REGEX_OF_DATA);
        return arrayOfData[INDEX_NAME_EMPLOYEE];

    }

    public LocalDate getDateFromData(String dataOfEmployee) {
        String[] arrayOfData = dataOfEmployee.split(REGEX_OF_DATA);
        return LocalDate.parse(arrayOfData[INDEX_DATE], formatter);

    }

    public int getSalaryFromDay(String dataOfEmployee) {
        String[] arrayOfData = dataOfEmployee.split(REGEX_OF_DATA);
        return Integer.valueOf(arrayOfData[INDEX_SALARY]) * Integer.valueOf(arrayOfData[3]);
    }
}
