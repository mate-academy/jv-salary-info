package core.basesyntax;

import exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException {
        LocalDate fromDate = parse(dateFrom, FORMATTER);
        LocalDate toDate = parse(dateTo, FORMATTER);
        if (fromDate.isAfter(toDate)) {
            throw new IllegalDateParametersException("Wrong message!");
        }

        StringBuilder containerResult = new StringBuilder("Отчёт за период " + dateFrom + " - " + dateTo + "\n");

        for (String n : names) {
            int salary = 0;
            for (String d : data) {
                String[] dataArray = d.split(" ");
                LocalDate dataDate = LocalDate.parse(dataArray[0], FORMATTER);
                if (n.equals(dataArray[1])
                        && dataDate.isAfter(fromDate)
                        && dataDate.isEqual(fromDate)
                        && dataDate.isBefore(toDate)
                        && dataDate.isEqual(toDate)) {
                    salary += Integer.parseInt(dataArray[2]) * Integer.parseInt(dataArray[3]);
                }
            }
            containerResult.append(n).append(" - ").append(salary).append("\n");
        }
        return containerResult.toString().trim();
    }
}
