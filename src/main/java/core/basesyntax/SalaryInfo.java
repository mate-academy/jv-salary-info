package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private DateTimeFormatter formatter;

    public SalaryInfo() {
        formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);

        StringBuilder reportStringBuilder = new StringBuilder("Report for period ");
        reportStringBuilder.append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] splittedData = data[i].split(" ");
                final String Date_Index = splittedData[0];
                final String Name_Index = splittedData[1];
                final String Day_Index = splittedData[2];
                final String Income_Index = splittedData[3];
                LocalDate date = LocalDate.parse(Date_Index, formatter);
                if ((localDateFrom.isBefore(date) || localDateFrom.equals(date))
                        && (localDateTo.isAfter(date) || localDateTo.equals(date))
                        && name.equals(Name_Index)) {
                    salary += Integer.parseInt(Day_Index) * Integer.parseInt(Income_Index);
                }
            }
            reportStringBuilder.append(System.lineSeparator())
                    .append(name).append(" - ").append(salary);
        }
        return reportStringBuilder.toString();
    }
}
