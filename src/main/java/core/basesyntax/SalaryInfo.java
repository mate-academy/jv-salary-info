package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final int DATE_INDEX = 0;
    static final int WORKING_HOURS_INDEX = 2;
    static final int PAYMENT_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            int calculatedSalary = 0;
            for (int j = 0; j < data.length; j++) {
                if (data[j].contains(names[i])) {
                    String[] splitData = data[j].split(" ");
                    LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMAT);
                    LocalDate extractedDate = LocalDate.parse(splitData[DATE_INDEX], DATE_FORMAT);
                    LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMAT);
                    if (extractedDate.isAfter(fromDate) && extractedDate.isBefore(toDate)
                            || extractedDate.isEqual(fromDate) || extractedDate.isEqual(toDate)) {
                        int workingHours = Integer.parseInt(splitData[WORKING_HOURS_INDEX]);
                        int payment = Integer.parseInt(splitData[PAYMENT_INDEX]);
                        calculatedSalary += workingHours * payment;
                    }
                }
            }
            report.append(System.lineSeparator()).append(names[i]).append(" - ").append(calculatedSalary);
        }
        return report.toString();
    }
}
