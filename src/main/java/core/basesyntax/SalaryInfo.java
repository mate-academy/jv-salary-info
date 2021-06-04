package core.basesyntax;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private static final int INDEX_OF_SALARY_PER_HOUR = 2;
    private static final int INDEX_OF_HOURS = 3;
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        String report = "Report for period " + dateFrom + " - " + dateTo + "\n";
        LocalDate dateStart = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate dateEnd = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        int[] salary = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            int sumSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] totalInformation = data[j].split(" ");
                LocalDate currentDate = LocalDate.parse(totalInformation[INDEX_OF_DATE],
                                        DATE_TIME_FORMATTER);
                String name = totalInformation[INDEX_OF_NAME];
                if ((name.equals(names[i])) && (currentDate.compareTo(dateStart) >= 0)
                                            && (currentDate.compareTo(dateEnd) <= 0)) {
                    sumSalary += Integer.parseInt(totalInformation[INDEX_OF_HOURS])
                              * Integer.parseInt(totalInformation[INDEX_OF_SALARY_PER_HOUR]);
                }
            }
            report += names[i] + " - " + sumSalary;
            if (i != (names.length - 1)) {
                report += "\n";
            }
        }
        return report;
    }
}

