package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static String HEADER = "Report for period ";
    private static String SEPARATOR = " - ";
    private static String LINE_SEPARATOR = "\n";
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] result = new String[names.length + 1];
        result[0] = HEADER + dateFrom + SEPARATOR + dateTo;

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int salary = 0;
            for (String salaryData : data) {
                String[] parsedData = salaryData.split(" ");
                int hours = Integer.parseInt(parsedData[2]);
                int salaryPerHour = Integer.parseInt(parsedData[3]);

                if (isWorkerShouldGetMoney(salaryData, name, dateFrom, dateTo)) {
                    salary += hours * salaryPerHour;
                }
            }
            result[i + 1] = name + SEPARATOR + salary;
        }
        return String.join(LINE_SEPARATOR, result);
    }

    private boolean isWorkerShouldGetMoney(
            String salaryData, String name, String dateFrom, String dateTo
    ) {
        String[] parsedData = salaryData.split(" ");
        LocalDate date = LocalDate.parse(parsedData[0], DATE_FORMATTER);
        String workerName = parsedData[1];
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        return workerName.equals(name)
                && (date.isEqual(fromDate)
                || date.isEqual(toDate)
                || (date.isAfter(fromDate) && date.isBefore(toDate)));

    }
}
