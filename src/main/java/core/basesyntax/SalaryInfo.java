package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder(
                "Report for period " + dateFrom + " - " + dateTo);
        int space = 1;
        LocalDate localDateFrom = LocalDate.of(
                Integer.parseInt(dateFrom.substring(6, 10)),
                Integer.parseInt(dateFrom.substring(3, 5)),
                Integer.parseInt(dateFrom.substring(0, 2))
        );
        LocalDate localDateTo = LocalDate.of(
                Integer.parseInt(dateTo.substring(6, 10)),
                Integer.parseInt(dateTo.substring(3, 5)),
                Integer.parseInt(dateTo.substring(0, 2))
        );
        for (String name : names) {
            int salary = 0;
            for (String oneData : data) {
                LocalDate localOneData = LocalDate.of(
                        Integer.parseInt(oneData.substring(6, 10)),
                        Integer.parseInt(oneData.substring(3, 5)),
                        Integer.parseInt(oneData.substring(0, 2))
                );
                String trimmedName = oneData.substring(
                        dateFrom.length() + space,dateFrom.length() + space + name.length());
                if (localOneData.isAfter(localDateFrom) && localOneData.isBefore(localDateTo)
                        || localOneData.equals(localDateTo) || localOneData.equals(localDateFrom)) {
                    if (trimmedName.equals(name)) {
                        salary += Integer.parseInt(oneData.substring(
                                dateFrom.length() + space + name.length() + space,
                                dateFrom.length() + space + name.length() + space + 2
                        ).trim()) * Integer.parseInt(oneData.substring(
                                dateFrom.length() + space + name.length() + space + 2).trim());
                    }
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
