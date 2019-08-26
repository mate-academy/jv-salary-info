package core.basesyntax;

import org.junit.Assert;
import org.junit.Test;

public class SalaryInfoTest {
    private static final String[] ROLES = {"Сергей", "Андрей", "София"};
    private static final String[] SCRIPT_ARRAY = {
            "25.04.2019 Сергей 60 50",
            "25.04.2019 Андрей 3 200",
            "25.04.2019 София 10 100",

            "26.04.2019 Андрей 3 200",
            "26.04.2019 София 9 100",

            "27.04.2019 Сергей 7 100",
            "27.04.2019 София 3 80",
            "27.04.2019 Андрей 8 100"
    };
    private static final String[] DATES = {
            "24.04.2019",
            "25.04.2019",
            "26.04.2019",
            "27.04.2019"
    };
    private static final String[] EXPECTED_REPORTS = {
            "Отчёт за период 24.04.2019 - 24.04.2019\n" +
                    "Сергей - 0\n" +
                    "Андрей - 0\n" +
                    "София - 0\n",
            "Отчёт за период 24.04.2019 - 25.04.2019\n" +
                    "Сергей - 3000\n" +
                    "Андрей - 600\n" +
                    "София - 1000\n",
            "Отчёт за период 24.04.2019 - 26.04.2019\n" +
                    "Сергей - 3000\n" +
                    "Андрей - 1200\n" +
                    "София - 1900\n",
            "Отчёт за период 24.04.2019 - 27.04.2019\n" +
                    "Сергей - 3700\n" +
                    "Андрей - 2000\n" +
                    "София - 2140\n"
    };

    @Test
    public void getSalaryInfo() {
        SalaryInfo salary = new SalaryInfo();
        for (int i = 0; i < DATES.length; i++) {
            String actualResult = salary.getSalaryInfo(ROLES, SCRIPT_ARRAY, DATES[0], DATES[i]);
            String expectedResult = SalaryInfoTest.EXPECTED_REPORTS[i];

            Assert.assertEquals(
                    "Test failed from date " + DATES[0] + " to " + DATES[i]
                            + "\nactual: \n" + actualResult
                            + "\nexpected: \n" + expectedResult,
                    expectedResult,
                    actualResult);
        }

        String actualResult = salary.getSalaryInfo(ROLES, SCRIPT_ARRAY, DATES[1], DATES[0]);
        String expectedResult = null;
        Assert.assertEquals(
                "Test failed from date " + DATES[1] + " to " + DATES[0]
                        + "\nactual: \n" + actualResult
                        + "\nexpected: \n" + expectedResult,
                expectedResult,
                actualResult);
    }
}
