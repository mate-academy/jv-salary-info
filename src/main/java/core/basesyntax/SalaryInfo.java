package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final int DISTANCE_TO_SCORE = 12;
    private static final int START_DATE_SUBSTRING = 0;
    private static final int END_DATE_SUBSTRING = 10;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder buildResult = new StringBuilder("Report for period ");
        buildResult.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            buildResult.append(names[i]).append(" - ");
            buildResult.append(getSalaryFromPerson(names[i], data, dateFrom, dateTo));
            if (i + 1 != names.length) {
                buildResult.append(System.lineSeparator());
            }
        }
        return buildResult.toString();
    }

    public int getSalaryFromPerson(String name, String[] data, String dateFrom, String dateTo) {
        int totalScoreOfPerson = 0;

        for (int i = 0; i < data.length; i++) {
            if (data[i].contains(" " + name + " ")) {
                SalaryInfo salaryInfo = new SalaryInfo();
                LocalDate currDate = salaryInfo.getDate(data[i].substring(START_DATE_SUBSTRING,
                                                                          END_DATE_SUBSTRING));
                LocalDate workFrom = salaryInfo.getDate(dateFrom);
                LocalDate workTo = salaryInfo.getDate(dateTo);

                if ((currDate.isBefore(workTo) || currDate.isEqual(workTo))
                        && (currDate.isAfter(workFrom)) || currDate.isEqual(workFrom)) {
                    String stringWIthScore = data[i].substring(DISTANCE_TO_SCORE + name.length());
                    String[] partsOfStringWIthScore = stringWIthScore.split(" ");
                    totalScoreOfPerson += (Integer.parseInt(partsOfStringWIthScore[0])
                                        * Integer.parseInt(partsOfStringWIthScore[1]));
                }
            }
        }

        return totalScoreOfPerson;
    }

    public LocalDate getDate(String date) {
        String day = date.substring(START_DATE_SUBSTRING, START_DATE_SUBSTRING + 2);
        String month = date.substring(START_DATE_SUBSTRING + 3, START_DATE_SUBSTRING + 5);
        String year = date.substring(START_DATE_SUBSTRING + 6, END_DATE_SUBSTRING);
        return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
    }
}
