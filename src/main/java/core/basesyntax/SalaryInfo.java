package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    private static final String DATA_FORMAT = "dd.MM.yyyy";
    private static final int NUM_OF_DATE = 0;
    private static final int NUM_OF_NAME = 1;
    private static final int NUM_OF_SALARY = 2;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATA_FORMAT, Locale.US);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);

        StringBuilder information = new StringBuilder();
        for (String name : names) {
            int salary = 0;
            for (String fullData : data) {
                String[] info = fullData.split(" ");
                LocalDate currentDate = LocalDate.parse(info[NUM_OF_DATE], formatter);
                String nameOfEmploy = info[NUM_OF_NAME];

                if (nameOfEmploy.equals(name)
                        && (currentDate.isAfter(localDateFrom)
                        || currentDate.isEqual(localDateFrom))
                        && (currentDate.isBefore(localDateTo)
                        || currentDate.isEqual(localDateTo))) {
                    salary += Integer.parseInt(info[NUM_OF_SALARY]) * Integer.parseInt(info[3]);
                }
            }
            information.append("\n").append(name).append(" - ").append(salary);
        }

        return "Report for period " + dateFrom + " - "
                + dateTo + information;
    }

}
