package core.basesyntax.services;

public class PrintService {
    public static String printHead(String dateFrom, String dateTo) {
        return "Report for period " + dateFrom + " - " + dateTo;
    }

    public static String printRow(String name, String sum) {
        return name + " - " + sum;
    }
}
