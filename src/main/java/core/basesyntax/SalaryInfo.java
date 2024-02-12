package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder info = new StringBuilder("Report for period ");
        SalaryInfo salaryInfo = new SalaryInfo();
        info.append(dateFrom).append(" - ").append(dateTo);
        LocalDate fromDate = salaryInfo.toDate(dateFrom);
        LocalDate toDate = salaryInfo.toDate(dateTo);
        String[] splitData;
        LocalDate workDay;
        int salary;
        for (String name : names) {
            salary = 0;
            for (String datum : data) {
                splitData = datum.split(" ");
                if (!name.equals(splitData[1])) {
                    continue;
                }
                workDay = salaryInfo.toDate(splitData[0]);
                if ((toDate.isAfter(workDay) && fromDate.isBefore(workDay))
                        || workDay.isEqual(fromDate)
                        || workDay.isEqual(toDate)) {
                    salary += Integer.parseInt(splitData[2])
                            * Integer.parseInt(splitData[3]);
                }
            }
            info.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return info.toString();
    }

    private LocalDate toDate(String date) {
        String[] splittedDate = date.split("\\.");
        if (splittedDate[0].charAt(0) == '0') {
            splittedDate[0] = String.valueOf(splittedDate[0].charAt(1));
        }
        if (splittedDate[1].charAt(0) == '0') {
            splittedDate[1] = String.valueOf(splittedDate[1].charAt(1));
        }
        return LocalDate.of(Integer.parseInt(splittedDate[2]),
                Integer.parseInt(splittedDate[1]),
                Integer.parseInt(splittedDate[0]));
    }
}
