package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class SalaryInfo {
    private static final String LINE_SEPARATOR = "\n";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)  {
        StringBuilder builder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo).append(LINE_SEPARATOR);

        LocalDate from = LocalDate.now();
        LocalDate to = LocalDate.now();

        try {
            from = LocalDate.parse(dateFrom, formatter);
            to = LocalDate.parse(dateTo, formatter);

        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date " + e);
        }

        List<String> employeeNameByKey = new ArrayList<>(Arrays.stream(names).toList());
        List<Integer> employeeSumByKey = new ArrayList<Integer>(Collections.nCopies(names.length, 0));

        for (int i = 0; i < data.length; i++) {
            String[] splittedString = data[i].split(" ");
            LocalDate paymentDate = LocalDate.now();

            try {
                paymentDate = LocalDate.parse(splittedString[0], formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Error parsing date in names" + e);
            }

            boolean isWithinRange = !(paymentDate.isBefore(from) || paymentDate.isAfter(to));

            if (isWithinRange) {
                String paymentRecipient = splittedString[1];
                int position = employeeNameByKey.indexOf(paymentRecipient);
                if (position != -1) {
                    int paymentSum = Integer.parseInt(splittedString[2]) * Integer.parseInt(splittedString[3]);
                    int oldSum = employeeSumByKey.get(position);
                    int newSum = oldSum + paymentSum;
                    employeeSumByKey.set(position, newSum);
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int salary = employeeSumByKey.get(i);
            builder.append(name).append(" - ").append(salary).append(i == names.length - 1 ? "" : LINE_SEPARATOR);
        }

        return builder.toString();
    }
}
