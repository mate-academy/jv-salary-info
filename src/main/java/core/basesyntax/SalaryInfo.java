package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private static final String SEPARATOR = " - ";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStart;
        LocalDate dateEnd;
        try {
            dateStart = LocalDate.parse(dateFrom, formatter);
            dateEnd = LocalDate.parse(dateTo, formatter);
        } catch (DateTimeParseException e) {
            System.out.printf("%s is not parsable!%n", dateFrom, dateTo);
            throw e;
        }

        StringBuilder totalReport = new StringBuilder("Report for period "
                + dateFrom
                + SEPARATOR
                + dateTo);
        for (String name: names) {
            int totalSalaryPerWorker = 0;
            for (String dataLine : data) {
                String[] splitDataArray = dataLine.split(" ");
                LocalDate workDate;
                try {
                    workDate = LocalDate.parse(splitDataArray[DATE_INDEX], formatter);
                } catch (DateTimeParseException e) {
                    System.out.printf("%s is not parsable!%n", splitDataArray[DATE_INDEX]);
                    throw e;
                }
                String workerName = splitDataArray[NAME_INDEX];
                int workerTime = Integer.parseInt(splitDataArray[HOUR_INDEX]);
                int workerSalary = Integer.parseInt(splitDataArray[SALARY_INDEX]);
                if (!workDate.isBefore(dateStart)
                        && !workDate.isAfter(dateEnd)
                        && workerName.equals(name)) {
                    totalSalaryPerWorker += workerTime * workerSalary;
                }
            }
            totalReport.append(System.lineSeparator())
                    .append(name).append(SEPARATOR)
                    .append(totalSalaryPerWorker);
        }

        return totalReport.toString();
    }
}
