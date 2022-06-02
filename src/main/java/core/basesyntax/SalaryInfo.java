package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom);
        LocalDate toDate = LocalDate.parse(dateTo);
        int i = 0;
        StringBuilder[] namesHoursMoneyData = new StringBuilder[0];
        for (String dateElement : data) {
            String[] dataInfo = data[i].split(" ");
            LocalDate workDate = LocalDate.parse(dataInfo[0]);

            if (workDate.isBefore(toDate) && workDate.isAfter(fromDate)
                    || workDate.equals(toDate) || workDate.equals(fromDate)) {
                namesHoursMoneyData[i].append(dataInfo[1]).append(dataInfo[2]).append(dataInfo[3]);
                i++;
            }
        }

        return namesHoursMoneyData.toString();
    }
}
