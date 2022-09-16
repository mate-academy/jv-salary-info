package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORK_HOUR_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, FORMATTER);
        String[] employeeInfo;
        for (String name : names) {
            int income = 0;
            for (String value : data) {
                employeeInfo = value.split(" ");
                if (name.equals(employeeInfo[NAME_INDEX])) {
                    LocalDate currentDate = LocalDate.parse(employeeInfo[DATE_INDEX], FORMATTER);
                    if ((currentDate.isBefore(dateToFormatted)
                            || currentDate.isEqual(dateToFormatted))
                            && (currentDate.isAfter(dateFromFormatted)
                                || currentDate.isEqual(dateFromFormatted))) {
                        income += Integer.parseInt(employeeInfo[WORK_HOUR_INDEX])
                                * Integer.parseInt(employeeInfo[INCOME_PER_HOUR_INDEX]);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(income);
        }
        return stringBuilder.toString();
        /* I've forgotten to see COMMON MISTAKES, this solution also works
        int income;
        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, FORMATTER);
        Map<String, Integer> resultMap = new LinkedHashMap<>();
        for (String s : data) {
            LocalDate currentDate = LocalDate.parse(s.substring(0, s.indexOf(" ")), FORMATTER);
            String employee = s.split(" ")[NAME_INDEX];
            String workHour = s.split(" ")[WORK_HOUR_INDEX];
            String incomePerHour = s.split(" ")[INCOME_PER_HOUR_INDEX];
            income = resultMap.get(employee) == null ? 0 : resultMap.get(employee);
            if ((currentDate.isBefore(dateToFormatted) || currentDate.isEqual(dateToFormatted))
                    && (currentDate.isAfter(dateFromFormatted)
                    || currentDate.isEqual(dateFromFormatted))) {
                income += Integer.parseInt(workHour) * Integer.parseInt(incomePerHour);
                resultMap.put(employee, income);
            } else {
                resultMap.put(employee, income);
            }
        }
        String result = resultMap.keySet()
                .stream()
                .map(k -> k + " - " + resultMap.get(k))
                .collect(Collectors.joining(System.lineSeparator()));
        return stringBuilder.append(System.lineSeparator())
                .append(result).toString();*/
    }

    public static void main(String[] args) {
        String[] names = {"John", "Andrew", "Kate"};
        String[] data = {"13.07.2019 John 60 50",
                        "15.07.2019 Andrew 3 200",
                        "15.07.2019 Kate 10 100",

                        "16.07.2019 Andrew 3 200",
                        "16.07.2019 Kate 9 100",

                        "10.08.2019 John 7 100",
                        "08.08.2019 Kate 3 80",
                        "11.08.2019 Andrew 8 100"};

        String dateFrom = "14.07.2019";
        String dateTo = "10.08.2019";
        /*Expected result:
        Report for period 01.04.2019  - 30.04.2019
        John - 700
        Andrew - 1200
        Kate - 2140*/
        System.out.println(getSalaryInfo(names, data, dateFrom, dateTo));
    }
}
