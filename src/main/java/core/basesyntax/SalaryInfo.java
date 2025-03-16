package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder sb = new StringBuilder("Report for period ")
                .append(dateFrom.trim()).append(" - ")
                .append(dateTo.trim());

        String[] dateFromArr = dateFrom.split("\\.");
        int dayFrom = Integer.parseInt(dateFromArr[0]);
        int monthFrom = Integer.parseInt(dateFromArr[1]);
        int yearFrom = Integer.parseInt(dateFromArr[2]);
        LocalDate date1 = LocalDate.of(yearFrom, monthFrom, dayFrom);

        String[] dateToArr = dateTo.split("\\.");
        int dayTo = Integer.parseInt(dateToArr[0]);
        int monthTo = Integer.parseInt(dateToArr[1]);
        int yearTo = Integer.parseInt(dateToArr[2]);
        LocalDate date2 = LocalDate.of(yearTo, monthTo, dayTo);

        for (String n : names) {
            int pay = 0;

            for (String d : data) {
                String[] dataArr = d.split(" ");
                String[] dayArr = dataArr[0].split("\\.");

                int day = Integer.parseInt(dayArr[0]);
                int month = Integer.parseInt(dayArr[1]);
                int year = Integer.parseInt(dayArr[2]);
                String name = dataArr[1];
                int hours = Integer.parseInt(dataArr[2]);
                int wage = Integer.parseInt(dataArr[3]);

                if (name.equals(n)) {
                    LocalDate date = LocalDate.of(year, month, day);

                    if (!date.isBefore(date1) && !date.isAfter(date2)) {
                        pay += hours * wage;
                    }
                }
            }

            sb.append(System.lineSeparator()).append(n).append(" - ").append(pay);
        }

        return sb.toString();
    }
}
