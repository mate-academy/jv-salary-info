package core.basesyntax;

public class SalaryInfo {
    private static final String SEPARATOR_DATA = " ";
    private static final String REPORT_INFO = "Report for period ";
    private static final String ERROR_DATA = "Wrong date format";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if ((names.length == 0) || (data.length == 0) || (dateFrom.length() == 0)
                || (dateTo.length() == 0)) {
            return "";
        }
        int dateFromFormat = getDataToIntFormat(dateFrom);
        int dateToFormat = getDataToIntFormat(dateTo);
        if (dateFromFormat > dateToFormat) {
            return "";
        }

        StringBuilder result = new StringBuilder(REPORT_INFO + dateFrom + " - " + dateTo);
        for (String wordNameAr : names) {
            int summaName = 0;
            for (String datum : data) {
                StringBuilder builder = new StringBuilder(datum);
                if (builder.length() == 0) {
                    continue;
                }
                int indexSeparator1 = builder.indexOf(SEPARATOR_DATA);
                if (indexSeparator1 < 0) {
                    continue;
                }
                int indexSeparator2 = builder.indexOf(SEPARATOR_DATA, indexSeparator1 + 1);
                if (indexSeparator2 < 0) {
                    continue;
                }
                int wordData = getDataToIntFormat(builder.substring(0, indexSeparator1));
                String wordName = builder.substring(indexSeparator1 + 1, indexSeparator2);
                if ((wordNameAr.equals(wordName)) && (wordData >= dateFromFormat)
                        && (wordData <= dateToFormat)) {
                    int indexSeparator3 = builder.indexOf(SEPARATOR_DATA, indexSeparator2 + 1);
                    if (indexSeparator3 < 0) {
                        continue;
                    }
                    String wordTime = builder.substring(indexSeparator2 + 1, indexSeparator3);
                    int tekTime = getStringToInt(wordTime);
                    String wordRate = builder.substring(indexSeparator3 + 1);
                    int tekRate = getStringToInt(wordRate);
                    int tekSumma = tekTime * tekRate;
                    summaName = summaName + tekSumma;
                }
            }
            result.append("\n").append(wordNameAr).append(" - ").append(summaName);
        }
        return result.toString();
    }

    public int getDataToIntFormat(String inputData) {
        if (inputData.length() < 10) {
            throw new DataValidationException(ERROR_DATA);
        }
        String result = inputData.substring(6, 10) + inputData.substring(3, 5)
                + inputData.substring(0, 2);
        return getStringToInt(result);
    }

    public int getStringToInt(String inputStr) {
        int result;
        try {
            result = Integer.parseInt(inputStr);
        } catch (Exception e) {
            throw new DataValidationException(ERROR_DATA);
        }
        return result;
    }
}
