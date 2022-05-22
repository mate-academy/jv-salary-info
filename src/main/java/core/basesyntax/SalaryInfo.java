package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] splitDateFrom = dateFrom.split("[.]");
        String[] splitDateTo = dateTo.split("[.]");
        int datFrom = Integer.parseInt(splitDateFrom[0]);
        int monthFrom = Integer.parseInt(splitDateFrom[1]);
        int yearFrom = Integer.parseInt(splitDateFrom[2]);
        int datTo = Integer.parseInt(splitDateTo[0]);
        int monthTo = Integer.parseInt(splitDateTo[1]);
        int yearTo = Integer.parseInt(splitDateTo[2]);
        LocalDate from = LocalDate.of(yearFrom, monthFrom, datFrom);
        LocalDate to = LocalDate.of(yearTo, monthTo, datTo);

        String report = "Report for period " + dateFrom + " - " + dateTo + System.lineSeparator();
        StringBuilder sb = new StringBuilder(report);
        for (String name : names) {
            sb.append(name).append(" - ");
            int salary = 0;
            for (String record : data) {
                String[] splitRecord = record.split(" ");
                if (splitRecord[1].equals(name)) {
                    String[] splitRecordDate = splitRecord[0].split("[.]");
                    int datRecord = Integer.parseInt(splitRecordDate[0]);
                    int monthRecord = Integer.parseInt(splitRecordDate[1]);
                    int yearRecord = Integer.parseInt(splitRecordDate[2]);
                    LocalDate recordDate = LocalDate.of(yearRecord, monthRecord, datRecord);
                    if (recordDate.isEqual(from) || recordDate.isEqual(to)
                            || recordDate.isAfter(from) && recordDate.isBefore(to)) {
                        salary = salary
                                + (Integer.parseInt(splitRecord[2])
                                * Integer.parseInt(splitRecord[3]));
                    }
                }
            }
            sb.append(salary).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
