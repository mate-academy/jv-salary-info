package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        LocalDate localDateFrom = getDateFrom(dateFrom);
        LocalDate localDateTo = getDateFrom(dateTo);

        for (String name : names) {
            int salarySum = 0;
            for (String dayData : data) {
                String[] dayDataArray = dayData.split(" ");
                LocalDate localDate = getDateFrom(dayDataArray[0]);
                if (localDateFrom.minusDays(1).isBefore(localDate)
                        && localDateTo.plusDays(1).isAfter(localDate)
                        && name.equals(dayDataArray[1])) {
                    try {
                        salarySum += Integer.parseInt(dayDataArray[2])
                                * Integer.parseInt(dayDataArray[3]);
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong input data");
                    }
                }
            }
            stringBuilder.append("\r\n")
                    .append(name)
                    .append(" - ")
                    .append(salarySum);
        }

        return stringBuilder.toString();
    }

    private LocalDate getDateFrom(String input) {
        String[] inputArray = input.split("\\.");
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(inputArray[2].strip())
                .append("-")
                .append(inputArray[1].strip())
                .append("-")
                .append(inputArray[0].strip());

        return LocalDate.parse(stringBuilder.toString());
    }
}
