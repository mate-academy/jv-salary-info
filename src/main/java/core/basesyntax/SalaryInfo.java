package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom);
        LocalDate toDate = LocalDate.parse(dateTo);
        for (int i = 0; i < data.length; i++) {
            String[] dataInfo = data[i].split(" ");
            LocalDate workDate = LocalDate.parse(dataInfo[0]);

            if (workDate.isBefore(toDate) && workDate.isAfter(fromDate)
                    || workDate.equals(toDate) || workDate.equals(fromDate)) {

            }


            return null;
        }
    }
