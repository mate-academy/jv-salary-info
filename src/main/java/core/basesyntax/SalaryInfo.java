package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, FORMATTER);
        int[] salaryArr = new int [names.length];

        StringBuilder header = new StringBuilder("Report for period ");
        header.append(parsedDateFrom.format(FORMATTER))
                .append(" - ")
                .append(parsedDateTo.format(FORMATTER));

        for (int j = 0; j < names.length; j++) {
            for (int i = 0; i < data.length; i++) {
                String[] splitData = data[i].split(" ");
                LocalDate dataDate = LocalDate.parse(splitData[0], FORMATTER);
                int parsedHours = Integer.parseInt(splitData[2]);
                int parsedMoney = Integer.parseInt(splitData[3]);

                if ((dataDate.isAfter(parsedDateFrom) || dataDate.isEqual(parsedDateFrom))
                        && (dataDate.isBefore(parsedDateTo) || dataDate.isEqual(parsedDateTo))) {
                    if (names[j].equals(splitData[1])) {
                        salaryArr[j] = salaryArr[j] + parsedHours * parsedMoney;
                    }
                }
            }
            header.append(System.lineSeparator())
                    .append(names[j])
                    .append(" - ")
                    .append(salaryArr[j]);
        }

        return header.toString();
    }

    public static void main(String[] args) {
        String[] names = new String[] {"John", "Andrew", "Kate"};
        String[] data = new String[] {
                "13.07.2019 John 60 50",
                "15.07.2019 Andrew 3 200",
                "15.07.2019 Kate 10 100",

                "16.07.2019 Andrew 3 200",
                "16.07.2019 Kate 9 100",

                "10.08.2019 John 7 100",
                "08.08.2019 Kate 3 80",
                "11.08.2019 Andrew 8 100"
        };
        String dateFrom = "14.07.2019";
        String dateTo = "10.08.2019";

        SalaryInfo salaryInfo = new SalaryInfo();
        System.out.println(salaryInfo.getSalaryInfo(names, data, dateFrom, dateTo));
    }
}
