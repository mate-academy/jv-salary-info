package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        String infoSalary = null;
        int[] salary = new int[names.length];

        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] splitData = data[j].split(" ");
                LocalDate localSplitDate = LocalDate.parse(splitData[0], FORMATTER);
                if ((localDateFrom.isBefore(localSplitDate)
                        || localDateTo.isEqual(localSplitDate))
                        && (localDateTo.isAfter(localSplitDate)
                        || localDateTo.isEqual(localSplitDate))
                        && names[i].equals(splitData[1])) {
                    salary[i] = salary[i]
                            + Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                }
            }
            infoSalary = builder.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(salary[i]).toString();
        }
        return builder.toString();
    }
}
