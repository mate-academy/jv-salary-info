package core.basesyntax;

public class SalaryInfo {
    private final static int COUNT_DATA_VARIABLES = 4;
    private final static int INDEX_YEAR_BEGIN = 6;
    private final static int INDEX_MONTH_BEGIN = 3;
    private final static int INDEX_MONTH_END = 5;
    private final static int INDEX_DAY_BEGIN = 0;
    private final static int INDEX_DAY_END = 2;

    public Integer stringToIntDate(String string) {
        return Integer.parseInt(new StringBuilder()
                .append(string.substring(INDEX_YEAR_BEGIN))
                .append(string.substring(INDEX_MONTH_BEGIN, INDEX_MONTH_END))
                .append(string.substring(INDEX_DAY_BEGIN, INDEX_DAY_END)).toString());
    }

    public String result(String result[][], String dateFrom, String dateTo) {
        return new StringBuilder("Report for period ").append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator())
                .append(result[0][0]).append(" - ").append(result[1][0]).append(System.lineSeparator())
                .append(result[0][1]).append(" - ").append(result[1][1]).append(System.lineSeparator())
                .append(result[0][2]).append(" - ").append(result[1][2]).toString();
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[][] table = new String[data.length][COUNT_DATA_VARIABLES];
        int dateFromInteger = stringToIntDate(dateFrom);
        int dateToInteger = stringToIntDate(dateTo);
        String[][] outData = new String[2][3];
        for (int i = 0; i < outData[0].length; i++) {
            outData[0][i] = names[i];
            outData[1][i] = "0";
        }
        for (int i = 0; i < data.length; i++) {
            table[i] = data[i].split(" ");
            table[i][INDEX_DAY_BEGIN] = stringToIntDate(table[i][INDEX_DAY_BEGIN]).toString();
            int tempDate = Integer.parseInt(table[i][INDEX_DAY_BEGIN]);
            if (tempDate >= dateFromInteger && tempDate <= dateToInteger) {
               for (int j = 0; j < outData[0].length; j++) {
                   if (table[i][1].equals(names[j])) {
                       outData[1][j] = String.valueOf(Integer.parseInt(outData[1][j])
                               + Integer.parseInt(table[i][2]) * Integer.parseInt(table[i][3]));
                   }
                }
            }
        }
        return result(outData, dateFrom, dateTo);
    }
}
