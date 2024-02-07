package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder info = new StringBuilder("Report for period ");
        info.append(dateFrom).append(" - ").append(dateTo);
        SalaryInfo salaryInfo = new SalaryInfo();
        LocalDate fromDate = salaryInfo.toDate(dateFrom);
        LocalDate toDate = salaryInfo.toDate(dateTo);
        String[] splittedData;
        LocalDate workDay;
        int sallary;
        for (String name : names) {
            sallary = 0;
            for (String datum : data) {
                splittedData = datum.split(" ");
                if (!name.equals(splittedData[1])) {
                    continue;
                }
                workDay = salaryInfo.toDate(splittedData[0]);
                if ((toDate.isAfter(workDay) && fromDate.isBefore(workDay))
                        || workDay.isEqual(fromDate)
                        || workDay.isEqual(toDate)) {
                    sallary += Integer.parseInt(splittedData[2])
                            * Integer.parseInt(splittedData[3]);
                }
            }
            info.append(System.lineSeparator()).append(name).append(" - ").append(sallary);
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
