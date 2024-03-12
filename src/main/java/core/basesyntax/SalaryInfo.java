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
                String[] oneDataSplit = oneData.split(" ");
                LocalDate localOneData = LocalDate.of(
                        Integer.parseInt(oneDataSplit[0].substring(6, 10)),
                        Integer.parseInt(oneDataSplit[0].substring(3, 5)),
                        Integer.parseInt(oneDataSplit[0].substring(0, 2))
                );
                String trimmedName = oneDataSplit[1];

                if (localOneData.isAfter(localDateFrom) && localOneData.isBefore(localDateTo)
                        || localOneData.equals(localDateTo) || localOneData.equals(localDateFrom)) {

                    if (trimmedName.equals(name)) {
                        int hoursInData = Integer.parseInt(oneDataSplit[2]);
                        int salaryPerHour = Integer.parseInt(oneDataSplit[3]);

                        salary += hoursInData * salaryPerHour;
                    }
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
