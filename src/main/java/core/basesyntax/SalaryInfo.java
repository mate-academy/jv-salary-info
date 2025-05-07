package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    //set global formatter for date
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        //result string initiated by  head "Report for period dd.mm.yyyy  - dd.mm.yyyy  "
        StringBuilder reportString = new StringBuilder("Report for period ");
        reportString.append(dateFrom).append(" - ").append(dateTo);

        LocalDate startDate = LocalDate.parse(dateFrom,formatter);
        LocalDate endDate = LocalDate.parse(dateTo,formatter);
        LocalDate currDate;

        //salary array same as manes relation index to index
        int[] salarySum = new int[names.length];

        for (String record : data) {
            String[] recordString = record.split(" ");

            currDate = LocalDate.parse(recordString[0],formatter);

            if ((currDate.equals(startDate) || currDate.equals(endDate))
                    || (currDate.isAfter(startDate) && currDate.isBefore(endDate))) {
                for (int i = 0; i < names.length; i++) {
                    if (recordString[1].equals(names[i])) {
                        salarySum[i] = salarySum[i]
                                + Integer.parseInt(recordString[2])
                                * Integer.parseInt(recordString[3]);
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            reportString.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(salarySum[i]);
        }

        return reportString.toString();
    }
}
