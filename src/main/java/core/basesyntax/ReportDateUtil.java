package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class ReportDateUtil {
    private static final String REPORT_DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern(REPORT_DATE_PATTERN);

    public static LocalDate parseReportStringDate(String date) {
        return LocalDate.parse(date, dateTimeFormatter);
    }
}
