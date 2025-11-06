package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int SHIFTS = 2;
    private static final int SHIFT_RATE = 3;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        StringBuilder finalReport = new StringBuilder("Report for period "
                + dateFrom
                + " - "
                + dateTo
                + System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int totalSalary = 0;

            for (String record : data) {
                String[] currentDataSplit = record.split(" ");
                LocalDate currentDate = LocalDate.parse(currentDataSplit[DATE], formatter);

                if (currentDataSplit[NAME].equals(name)
                        && !currentDate.isBefore(from)
                        && !currentDate.isAfter(to)) {

                    int currentSalary = Integer.parseInt(currentDataSplit[SHIFTS])
                            * Integer.parseInt(currentDataSplit[SHIFT_RATE]);
                    totalSalary += currentSalary;
                }
            }
            finalReport.append(name)
                    .append(" - ")
                    .append(totalSalary);
            if (i < names.length - 1) {
                finalReport.append(System.lineSeparator());
            }
        }
        return finalReport.toString();
    }
}
