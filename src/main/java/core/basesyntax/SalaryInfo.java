package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    /*public static void main(String[] args) {
        String[] names = {"John", "Andrew", "Kate"};
        String[] data = {
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
        System.out.println(getSalaryInfo(names, data, dateFrom, dateTo));
    }*/

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        String[] splitDataToArray;
        int[] salaryInfo = new int[names.length];
        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        final LocalDate fromDate = LocalDate.parse(dateFrom, dateFormatter);
        final LocalDate toDate = LocalDate.parse(dateTo, dateFormatter);

        for (int j = 0; j < names.length; j++) {
            for (String datum : data) {
                splitDataToArray = datum.split(" ");
                LocalDate currentDate = LocalDate.parse(splitDataToArray[0], dateFormatter);
                if (names[j].equals(splitDataToArray[1])
                        && (currentDate.isAfter(fromDate) && currentDate.isBefore(toDate)
                        && names[j].equals(splitDataToArray[1])
                        || currentDate.equals(fromDate)
                        || currentDate.equals(toDate))) {
                    salaryInfo[j] = salaryInfo[j] + (Integer.parseInt(splitDataToArray[2])
                            * Integer.parseInt(splitDataToArray[3]));
                }
            }
        }
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(salaryInfo[i]);
        }
        return builder.toString();
    }
}
