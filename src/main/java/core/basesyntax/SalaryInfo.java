package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String reportForPeriod = "Report for period ";
        String separatorInReport = " - ";
        String separatorInData = " ";
        String formatLocalData = "dd.MM.yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatLocalData);
        LocalDate parseDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate parsDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder builder = new StringBuilder(reportForPeriod
                + dateFrom + separatorInReport + dateTo + System.lineSeparator());
        int salary = 0;
        for (String name : names) {
            for (String element : data) {
                String[] splitData = element.split(separatorInData);
                LocalDate parseSplitData = LocalDate.parse(splitData[0], formatter);
                if (name.equals(splitData[1])
                        && (parseSplitData.isAfter(parseDateFrom)
                        || parseSplitData.equals(parseDateFrom))
                        && (parseSplitData.isBefore(parsDateTo)
                        || parseSplitData.equals(parsDateTo))) {
                    int partOfSalary = Integer.parseInt(splitData[2])
                            * Integer.parseInt(splitData[3]);
                    salary += partOfSalary;
                }
            }
            builder.append(name).append(separatorInReport).append(salary)
                    .append(System.lineSeparator());
            salary = 0;
        }
        return builder.toString().trim();
    }
}
