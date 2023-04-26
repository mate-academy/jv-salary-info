package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final String SPACE_SEPARATOR = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Report for period %s - %s%s", dateFrom, dateTo,
                System.lineSeparator()));

        for (String name : names) {
            int salaryForPeriod = getSalaryForPeriodByName(name, data, dateFrom, dateTo);
            stringBuilder.append(String.format("%s - %s%s", name, salaryForPeriod,
                    System.lineSeparator()));
        }
        return stringBuilder.toString().trim();
    }

    private int getSalaryForPeriodByName(String name, String[] data,
                                         String dateFromString, String dateToString) {
        int salaryForPeriod = 0;
        LocalDate dateFrom = parseStringDate(dateFromString);
        LocalDate dateTo = parseStringDate(dateToString);

        for (int i = 0; i < data.length; i++) {
            String[] dataOfCurrentEmployee = data[i].split(SPACE_SEPARATOR);
            LocalDate currentDate = parseStringDate(dataOfCurrentEmployee[0]);
            String nameOfCurrentEmployee = dataOfCurrentEmployee[1];
            int workingHoursOfCurrentEmployee = Integer.parseInt(dataOfCurrentEmployee[2]);
            int incomePerHourOfCurrentEmployee = Integer.parseInt(dataOfCurrentEmployee[3]);

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
        return new DateParser().parseDate(dateToString);
    }
}
