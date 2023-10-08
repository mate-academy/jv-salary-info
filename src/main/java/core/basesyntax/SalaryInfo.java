package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String [] dateFromArray = dateFrom.split("\\.");
        LocalDate dataFromFormatted = LocalDate.parse(dateFromArray[2]
                + "-" + dateFromArray [1] + "-" + dateFromArray[0]);
        String [] dateToArray = dateTo.split("\\.");
        LocalDate dataToFormatted = LocalDate.parse(dateToArray[2]
                + "-" + dateToArray [1] + "-" + (Integer.parseInt(dateToArray[0]) + 1));
        int[]totalSalary = new int[names.length];
        StringBuilder stringBuilder = new StringBuilder("Report for period ");
        stringBuilder.append(dateFrom).append(" - ").append(dateTo);

        for (int n = 0; n < names.length; n++) {
            for (String datum : data) {
                String [] dataArray = datum.split(" ");
                String [] workingDateArray = dataArray[0].split("\\.");
                LocalDate workingDate = LocalDate.parse(workingDateArray[2]
                        + "-" + workingDateArray[1] + "-" + workingDateArray[0]);
                if (names[n].equals(dataArray[1]) && workingDate.isBefore(dataToFormatted)
                        && workingDate.isAfter(dataFromFormatted)) {
                    totalSalary[n] += Integer.parseInt(dataArray[2])
                             * Integer.parseInt(dataArray[3]);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(names[n])
                    .append(" - ").append(totalSalary[n]);
        }
        return stringBuilder.toString();
    }
}
