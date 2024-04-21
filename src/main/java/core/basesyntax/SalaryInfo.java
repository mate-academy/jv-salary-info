package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {
        String[] sampleNames = {"John", "Andrew", "Kate"};
        String[] scriptArray = {
                "25.04.2019 John 60 50",
                "25.04.2019 Andrew 3 200",
                "25.04.2019 Kate 10 100",

                "26.04.2019 Andrew 3 200",
                "26.04.2019 Kate 9 100",

                "27.04.2019 John 7 100",
                "27.04.2019 Kate 3 80",
                "27.04.2019 Andrew 8 100"
        };

        String res = getSalaryInfo(sampleNames, scriptArray, "24.04.2019", "26.04.2019");
    }

    public static String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate parsedDateFrom;
        LocalDate parsedDateTo;

        try {
            parsedDateFrom = LocalDate.parse(dateFrom.trim(), FORMATTER);
            parsedDateTo = LocalDate.parse(dateTo.trim(), FORMATTER);
        } catch (DateTimeParseException e) {
            return "The date format is incorrect or the date is invalid.";
        }

        for (String name : names) {
            for (int i = 0; i < data.length; i++) {
                String[] dataParts = data[i].split(" ");
                String namePart = dataParts[1];

                if (!namePart.equals(name)) {
                    continue;
                }

                String datePart = dataParts[0];
                String workedHoursPart = dataParts[2];
                String ratePart = dataParts[3];
                LocalDate parsedDatePart = LocalDate.parse(datePart, FORMATTER);

                if (data[i].contains(name)) {

                }
            }
        }

        return null;
    }

    private int calculatePersonIncomePerDay() {
        return 0;
    }

    private String createHeader(String dateFrom, String dateTo) {
        return "Report for period" + dateFrom + " - " + dateTo;
    }
}
