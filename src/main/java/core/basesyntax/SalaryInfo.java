package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int ZERO_NUMBER = 0;
    private static final int ONE_NUMBER = 1;
    private static final int TWO_NUMBER = 2;
    private static final int THREE_NUMBER = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDateFrom = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder stringBuilder = new StringBuilder("Report for period " + dateFrom
                + " - " + dateTo + "\n");
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i] + " - ");
            int sumSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] dataToArray = data[j].split(" ");
                LocalDate dateFromDate = LocalDate.parse(
                        dataToArray[ZERO_NUMBER], dateTimeFormatter);
                if (dateFromDate.compareTo(localDateFrom) >= ZERO_NUMBER
                        && dateFromDate.compareTo(localDateTo) <= ZERO_NUMBER
                        && dataToArray[1].equals(names[i])) {
                    sumSalary += Integer.parseInt(data[j].split(" ")[TWO_NUMBER])
                            * Integer.parseInt(data[j].split(" ")[THREE_NUMBER]);
                }
                if (j == data.length - ONE_NUMBER && i == names.length - ONE_NUMBER) {
                    stringBuilder.append(sumSalary);
                    continue;
                }
                if (j == data.length - 1) {
                    stringBuilder.append(sumSalary + "\n");
                }
            }
        }
        return stringBuilder.toString();
    }
}
