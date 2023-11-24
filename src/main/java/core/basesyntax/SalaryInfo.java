package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateFormat = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int INDEX_ADDER = 1;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        LocalDate fromDate = LocalDate.parse(dateFrom, dateFormat);
        LocalDate toDate = LocalDate.parse(dateTo, dateFormat);
        report.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            int employeesSalary = 0;
            boolean isLastRecord = i == names.length - 1;

            for (String datum : data) {
                int indNameAndSalaryCut = datum.indexOf(' ', datum.indexOf(' ')
                        + INDEX_ADDER);
                String nameCut = datum.substring(datum.indexOf(' ')
                        + INDEX_ADDER, indNameAndSalaryCut);

                if (names[i].equals(nameCut)) {
                    String dateCut = datum.substring(0, datum.indexOf(' '));

                    try {
                        LocalDate date = LocalDate.parse(dateCut, dateFormat);

                        if (!date.isBefore(fromDate) && !date.isAfter(toDate)) {
                            int indexSalaryCut = datum.indexOf(' ', indNameAndSalaryCut
                                    + INDEX_ADDER);
                            int cutSalaryFrom = Integer.parseInt(datum.substring(indNameAndSalaryCut
                                    + INDEX_ADDER, indexSalaryCut));
                            int cutSalaryTo = Integer.parseInt(datum.substring(indexSalaryCut
                                    + INDEX_ADDER));

                            employeesSalary += cutSalaryFrom * cutSalaryTo;
                        }
                    } catch (Exception e) {
                        System.out.println("Incorrect date format");
                    }
                }
            }
            report.append(names[i]).append(" - ").append(employeesSalary);
            if (!isLastRecord) {
                report.append(System.lineSeparator());
            }
        }

        return report.toString();
    }
}
