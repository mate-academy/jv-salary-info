package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ");
        result.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            result.append(name).append(" - ");
            int sum = 0;
            for (String info : data) {
                if (info.contains(name) && dateValid(dateFrom, info.substring(0, 10), dateTo)) {
                    sum += Integer.parseInt(info.split(" ")[2])
                            * Integer.parseInt(info.split(" ")[3]);
                }
            }
            result.append(sum).append(System.lineSeparator());
        }
        return result.toString().trim();
    }

    public static boolean dateValid(String dateFrom, String date, String dateTo) {
        LocalDate dateNew = LocalDate.of(Integer.parseInt(date.substring(6)),
                Integer.parseInt(date.substring(3, 5)),
                Integer.parseInt(date.substring(0, 2)));
        LocalDate dateFromNew = LocalDate.of(Integer.parseInt(dateFrom.substring(6)),
                Integer.parseInt(dateFrom.substring(3, 5)),
                Integer.parseInt(dateFrom.substring(0, 2)));
        LocalDate dateToNew = LocalDate.of(Integer.parseInt(dateTo.substring(6)),
                Integer.parseInt(dateTo.substring(3, 5)),
                Integer.parseInt(dateTo.substring(0, 2)));
        return (dateNew.isAfter(dateFromNew) && dateNew.isBefore(dateToNew))
                || dateNew.isEqual(dateFromNew) || dateNew.isEqual(dateToNew);
    }
}
