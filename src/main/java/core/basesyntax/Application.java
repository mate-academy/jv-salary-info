package core.basesyntax;

public class Application {
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
        String[] dates = {
                "24.04.2019",
                "25.04.2019",
                "26.04.2019",
                "27.04.2019"
        };
        String[] exceptedReports = {
                "Report for period 24.04.2019 - 24.04.2019"
                        + System.lineSeparator()
                        + "John - 0"
                        + System.lineSeparator()
                        + "Andrew - 0"
                        + System.lineSeparator()
                        + "Kate - 0",
                "Report for period 24.04.2019 - 25.04.2019"
                        + System.lineSeparator()
                        + "John - 3000"
                        + System.lineSeparator()
                        + "Andrew - 600"
                        + System.lineSeparator()
                        + "Kate - 1000",
                "Report for period 24.04.2019 - 26.04.2019"
                        + System.lineSeparator()
                        + "John - 3000"
                        + System.lineSeparator()
                        + "Andrew - 1200"
                        + System.lineSeparator()
                        + "Kate - 1900",
                "Report for period 24.04.2019 - 27.04.2019"
                        + System.lineSeparator()
                        + "John - 3700"
                        + System.lineSeparator()
                        + "Andrew - 2000"
                        + System.lineSeparator()
                        + "Kate - 2140"
        };
        String[] secondScriptArray = {
                "13.07.2019 John 60 50",
                "15.07.2019 Andrew 3 200",
                "15.07.2019 Kate 10 100",

                "16.07.2019 Andrew 3 200",
                "16.07.2019 Kate 9 100",

                "10.08.2019 John 7 100",
                "08.08.2019 Kate 3 80",
                "11.08.2019 Andrew 8 100"
        };
        String[] secondDates = {
                "14.07.2019",
                "10.08.2019",
        };
        String secondExceptedReports =
                "Report for period 14.07.2019 - 10.08.2019"
                        + System.lineSeparator()
                        + "John - 700"
                        + System.lineSeparator()
                        + "Andrew - 1200"
                        + System.lineSeparator()
                        + "Kate - 2140";
        // Demo: scriptArray
        SalaryInfo salary = new SalaryInfo();
        System.out.println(salary.getSalaryInfo(sampleNames, scriptArray, dates[0], dates[1]));
        System.out.println(salary.getSalaryInfo(sampleNames, scriptArray, dates[0], dates[2]));
        System.out.println(salary.getSalaryInfo(sampleNames, scriptArray, dates[0], dates[3]));
        SalaryInfo salary2 = new SalaryInfo();
        // Demo: secondScriptArray
        System.out.println(salary2.getSalaryInfo(sampleNames, secondScriptArray, secondDates[0], secondDates[1]));
    }
}
