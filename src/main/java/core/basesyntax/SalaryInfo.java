package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaries = new int[names.length];
        LocalDate dateFromLocal = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToLocal = LocalDate.parse(dateTo, formatter);
        for (String line: data) {
            String[] parsedData = line.split(" ");
            for (int i = 0; i < names.length; i++) {
                if (parsedData[1].equals(names[i])) {
                    LocalDate dateOfWork = LocalDate.parse(parsedData[0], formatter);
                    if ((dateOfWork.isAfter(dateFromLocal) | dateOfWork.equals(dateFromLocal))
                            & (dateOfWork.equals(dateToLocal)
                            | dateOfWork.isBefore(dateToLocal))) {
                        int salaryForThatDay = Integer.parseInt(parsedData[2])
                                    * Integer.parseInt(parsedData[3]);
                        salaries[i] += salaryForThatDay;
                    }
                }
            }
        }
        StringBuilder bufferResult = new StringBuilder("Report for period ").append(dateFrom)
                        .append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            bufferResult.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(salaries[i]);
        }
        return bufferResult.toString();
    }
}
