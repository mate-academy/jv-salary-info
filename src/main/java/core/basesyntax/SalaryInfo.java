package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder reportAboutSalary = new StringBuilder("Report for period "
                + localDateFrom.format(DATE_FORMATTER)
                + " - " + localDateTo.format(DATE_FORMATTER));

        for (int i = 0; i < names.length; i++) {
            int salary = 0;

            for (int j = 0; j < data.length; j++) {
                StringBuilder stringBuilderName = getName(data[j]);
                LocalDate localDate = LocalDate.parse(data[j].substring(0, 10), DATE_FORMATTER);

                if (stringBuilderName.toString().equals(names[i])
                        && (localDate.isAfter(localDateFrom) || localDate.isEqual(localDateFrom))
                        && (localDate.isBefore(localDateTo) || localDate.isEqual(localDateTo))
                        && !localDateFrom.isEqual(localDateTo)) {
                    String[] salaryPerHour = data[j].substring(12
                            + stringBuilderName.length()).split(" ");
                    salary += Integer.parseInt(salaryPerHour[0])
                            * Integer.parseInt(salaryPerHour[1]);
                }
            }
            reportAboutSalary.append("\n").append(names[i]).append(" - ").append(salary);
        }

        return reportAboutSalary.toString();
    }

    private StringBuilder getName(String name) {
        Pattern pattern = Pattern.compile("[A-Za-z]*");
        Matcher matcher = pattern.matcher(name);
        StringBuilder resultOfName = new StringBuilder();

        while (matcher.find()) {
            resultOfName.append(matcher.group());
        }

        return resultOfName;
    }
}
