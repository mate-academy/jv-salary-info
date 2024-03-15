package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        String[][] report = new String[names.length][2];

        for (int i = 0; i < names.length; i++) {
            report[i][0] = names[i];
            report[i][1] = "0";
        }

        for (String datas : data) {
            String[] info = datas.split(" ");

            for (int i = 0; i < names.length; i++) {
                if (report[i][0].equals(info[1]) && report[i][1] != null
                        && isDateFit(info[0], dateFrom, dateTo)) {
                    report[i][1] = String.valueOf((Integer.parseInt(info[2])
                            * Integer.parseInt(info[3])) + Integer.parseInt(report[i][1]));
                } else if (report[i][0].equals(info[1]) && report[i][1] == null
                        && isDateFit(info[0], dateFrom, dateTo)) {
                    report[i][1] = String.valueOf((Integer.parseInt(info[2])
                            * Integer.parseInt(info[3])));
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            salaryInfo.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(report[i][1]);
        }

        return salaryInfo.toString();
    }

    private boolean isDateFit(String localDate, String dateFrom, String dateTo) {
        LocalDate parsedDate = LocalDate.parse(localDate,
                DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom,
                DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate parsedDateTo = LocalDate.parse(dateTo,
                DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        return ((parsedDate.isAfter(parsedDateFrom) || parsedDate.isEqual(parsedDateFrom))
                && (parsedDate.isBefore(parsedDateTo) || parsedDate.isEqual(parsedDateTo)));
    }
}
