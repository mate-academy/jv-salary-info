package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {

        StringBuilder builder = new StringBuilder();

        LocalDate fromDate = parseDate(dateFrom);
        LocalDate toDate = parseDate(dateTo);

        int salary = 0;
        builder.append("Report for period ").append(dateFrom);
        builder.append(" - ").append(dateTo);

        for (String name : names) {

            for (String line : data) {

                String[] parsedLine = line.split(" ");

                if (!name.equals(parsedLine[1])) {
                    continue;
                }

                LocalDate bufferDate = parseDate(parsedLine[0]);

                if ((bufferDate.isAfter(fromDate) || bufferDate.equals(fromDate))
                        && (bufferDate.isBefore(toDate) || bufferDate.equals(toDate))) {
                    salary += Integer.parseInt(parsedLine[3]) * Integer.parseInt(parsedLine[2]);
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
            salary = 0;
        }

        return builder.toString();
    }

    private LocalDate parseDate(String date) {
        String[] splitDate = date.split("\\.");
        return LocalDate.parse(splitDate[2] + "-"
                + splitDate[1] + "-" + splitDate[0]);
    }
}
