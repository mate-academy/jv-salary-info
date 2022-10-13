package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromDate = LocalDate.of(Integer.parseInt(dateFrom.substring(6, 10)),
                Integer.parseInt(dateFrom.substring(3, 5)),
                Integer.parseInt(dateFrom.substring(0, 2)));

        LocalDate dateToDate = LocalDate.of(Integer.parseInt(dateTo.substring(6, 10)),
                Integer.parseInt(dateTo.substring(3, 5)),
                Integer.parseInt(dateTo.substring(0, 2)));

        StringBuilder result = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] dataFromFile = datum.split(" ");
                if (name.equals(dataFromFile[1])) {
                    LocalDate dateCurrent = LocalDate.of(Integer.parseInt(dataFromFile[0]
                                    .substring(6, 10)),
                            Integer.parseInt(dataFromFile[0].substring(3, 5)),
                            Integer.parseInt(dataFromFile[0].substring(0, 2)));
                    if ((dateFromDate.equals(dateCurrent) || dateFromDate.isBefore(dateCurrent))
                            && (dateToDate.equals(dateCurrent)
                            || dateToDate.isAfter(dateCurrent))) {
                        salary = salary + (Integer.parseInt(dataFromFile[2])
                                * Integer.parseInt(dataFromFile[3]));
                    }
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
