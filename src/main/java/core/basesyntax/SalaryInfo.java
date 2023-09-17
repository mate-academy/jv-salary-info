package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public static final String SEPARATOR = " - ";
    public static final String SPACE_SEPARATOR = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate toDate = LocalDate.parse(dateTo, dateFormatter);

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom)
                .append(SEPARATOR).append(dateTo).append(System.lineSeparator());

        for (String name : names) {
            int totalSalary = 0;
            for (String record : data) {
                String[] parts = record.split(SPACE_SEPARATOR);
                if (parts.length == 4) {
                    String recordDateStr = parts[0];
                    String employeeName = parts[1];
                    int hoursWorked = Integer.parseInt(parts[2]);
                    int hourlyRate = Integer.parseInt(parts[3]);

                    if (employeeName.equals(name)) {
                        LocalDate recordDate = LocalDate.parse(recordDateStr, dateFormatter);
                        if (!recordDate.isBefore(fromDate) && !recordDate.isAfter(toDate)) {
                            totalSalary += hoursWorked * hourlyRate;
                        }
                    }
                }
            }
            report.append(name).append(SEPARATOR).append(totalSalary).append(System.lineSeparator());
        }

        return report.toString().trim();
    }
}
// Data:
//   "26.04.2019 John 4 50"
//   05.04.2019 Andrew 3 200
//   10.04.2019 John 7 100
//   22.04.2019 Kate 9 100
//   25.06.2019 John 11 50
//   26.04.2019 Andrew 3 150
//   13.02.2019 John 7 100
//   26.04.2019 Kate 9 100
// Names:
//     John
//     Andrew
//     Kate
//  Method execution result:
//       Report for period 01.04.2019  - 30.04.2019
//       John - 900

