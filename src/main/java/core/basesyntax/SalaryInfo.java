package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    static final String HEADER = "Report for period ";
    static final String DELIMITER = " ";
    static final String SEPARATOR = " - ";
    static final String DOT = "\\.";
    static final String DASH = "-";
    static final int ZERO = 0;
    static final int ONE = 1;
    static final int TWO = 2;
    static final int THREE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String [] dateFromArray = dateFrom.split(DOT);
        LocalDate dataFromFormatted = LocalDate.parse(dateFromArray[TWO]
                + DASH + dateFromArray [ONE] + DASH + dateFromArray[ZERO]);
        String [] dateToArray = dateTo.split(DOT);
        LocalDate dataToFormatted = LocalDate.parse(dateToArray[TWO]
                + DASH + dateToArray [ONE] + DASH + (Integer.parseInt(dateToArray[ZERO]) + 1));
        int[]totalSalary = new int[names.length];
        StringBuilder stringBuilder = new StringBuilder(HEADER);
        stringBuilder.append(dateFrom).append(SEPARATOR).append(dateTo);

        for (int n = 0; n < names.length; n++) {
            for (String datum : data) {
                String [] dataArray = datum.split(DELIMITER);
                String [] workingDateArray = dataArray[ZERO].split(DOT);
                LocalDate workingDate = LocalDate.parse(workingDateArray[TWO]
                        + DASH + workingDateArray[ONE] + DASH + workingDateArray[ZERO]);
                if (names[n].equals(dataArray[ONE]) && workingDate.isBefore(dataToFormatted)
                        && workingDate.isAfter(dataFromFormatted)) {
                    totalSalary[n] += Integer.parseInt(dataArray[TWO])
                             * Integer.parseInt(dataArray[THREE]);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(names[n])
                    .append(SEPARATOR).append(totalSalary[n]);
        }
        return stringBuilder.toString();
    }
}
