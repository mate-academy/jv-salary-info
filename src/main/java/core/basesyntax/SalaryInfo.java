package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String report = "Report for period " + dateFrom + " - " + dateTo;
        for (int i = 0;i < names.length;i++) {
            report += "\n" + names[i] + " - ";
            int totalSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String [] oneEntry = data[j].split(" ");
                if (names[i].equals(oneEntry[1]) && (parseDate(oneEntry[0])
                        .compareTo(parseDate(dateTo))) <= 0 && (parseDate(oneEntry[0])
                        .compareTo(parseDate(dateFrom))) > 0) {
                    totalSalary += parseInt(oneEntry[2]) * parseInt(oneEntry[3]);
                }
            }
            report += totalSalary;
        }
        return report;
    }

    public LocalDate parseDate(String dateInString) {
        String [] splittedDate = dateInString.split("\\.");
        LocalDate date = LocalDate.of(parseInt(splittedDate[2]),
                parseInt(splittedDate[1]),parseInt(splittedDate[0]));
        return date;
    }
}
