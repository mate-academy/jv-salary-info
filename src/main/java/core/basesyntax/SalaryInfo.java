package core.basesyntax;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            int employeesSalary = 0;
            boolean isLastRecord = i == names.length - 1;

            for (String datum : data) {
                int indexNameAndSalaryCut = datum.indexOf(' ', datum.indexOf(' ') + 1);
                String nameCut = datum.substring(datum.indexOf(' ') + 1, indexNameAndSalaryCut);

                if (names[i].equals(nameCut)) {
                    String dateCut = datum.substring(0, datum.indexOf(' '));
                    Date date1;
                    Date date2;
                    Date date3;

                    try {
                        date1 = dateFormat.parse(dateCut);
                        date2 = dateFormat.parse(dateFrom);
                        date3 = dateFormat.parse(dateTo);

                        if (date1.before(date3) && date1.after(date2) || date1.equals(date2)
                                || date1.equals(date3)) {
                            int indexSalaryCut = datum.indexOf(' ', indexNameAndSalaryCut + 1);
                            int cutSalary1 = Integer.parseInt(datum.substring(indexNameAndSalaryCut
                                            + 1, indexSalaryCut));
                            int cutSalary2 = Integer.parseInt(datum.substring(indexSalaryCut + 1));

                            employeesSalary += cutSalary1 * cutSalary2;
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
