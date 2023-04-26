package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SPACE_SEPARATOR = " ";
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_WORKING_HOURS = 2;
    private static final int INDEX_OF_INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Report for period %s - %s", dateFrom, dateTo));
        LocalDate dateFromLD = parseStringDate(dateFrom);
        LocalDate dateToLD = parseStringDate(dateTo);
        for (String name : names) {
            int salaryForPeriod = getSalaryForPeriodByName(name, data, dateFromLD, dateToLD);
            stringBuilder.append(String.format("%s%s - %s", System.lineSeparator(),
                    name, salaryForPeriod));
        }
        return stringBuilder.toString();
    }

    private int getSalaryForPeriodByName(String name, String[] data,
                                         LocalDate dateFrom, LocalDate dateTo) {
        int salaryForPeriod = 0;
        for (int i = 0; i < data.length; i++) {
            String[] dataOfCurrentEmployee =
                    data[i].split(SPACE_SEPARATOR);
            LocalDate currentDate =
                    LocalDate.parse(dataOfCurrentEmployee[INDEX_OF_DATE], DATE_FORMATTER);
            String nameOfCurrentEmployee = dataOfCurrentEmployee[INDEX_OF_NAME];
            int workingHoursOfCurrentEmployee =
                    Integer.parseInt(dataOfCurrentEmployee[INDEX_OF_WORKING_HOURS]);
            int incomePerHourOfCurrentEmployee =
                    Integer.parseInt(dataOfCurrentEmployee[INDEX_OF_INCOME_PER_HOUR]);
            if (nameOfCurrentEmployee.equals(name)
                    && checkIfDateIsInRange(currentDate, dateFrom, dateTo)) {
                salaryForPeriod += workingHoursOfCurrentEmployee * incomePerHourOfCurrentEmployee;
            }
        }
        return salaryForPeriod;
    }

    private boolean checkIfDateIsInRange(LocalDate currentDate,
                                         LocalDate dateFrom, LocalDate dateTo) {
        return currentDate.isAfter(dateFrom.minusDays(1))
                && currentDate.isBefore(dateTo.plusDays(1));
    }

    private LocalDate parseStringDate(String dateToString) {
        return LocalDate.parse(dateToString, DATE_FORMATTER);
    }
}
