package core.basesyntax;

public class Demo {
    private static final String[] sampleNames = {"John", "Andrew", "Kate"};
    private static final String[] scriptArray = {
            "25.04.2019 John 60 50",
            "25.04.2019 Andrew 3 200",
            "25.04.2019 Kate 10 100",

            "26.04.2019 Andrew 3 200",
            "26.04.2019 Kate 9 100",

            "27.04.2019 John 7 100",
            "27.04.2019 Kate 3 80",
            "27.04.2019 Andrew 8 100"
    };
    private static final String[] dates = {
            "24.04.2019",
            "25.04.2019",
            "26.04.2019",
            "27.04.2019"
    };
    private static final String[] exceptedReports = {
            "Report for period 24.04.2019 - 24.04.2019\n"
                    + "John - 0\n"
                    + "Andrew - 0\n"
                    + "Kate - 0",
            "Report for period 24.04.2019 - 25.04.2019\n"
                    + "John - 3000\n"
                    + "Andrew - 600\n"
                    + "Kate - 1000",
            "Report for period 24.04.2019 - 26.04.2019\n"
                    + "John - 3000\n"
                    + "Andrew - 1200\n"
                    + "Kate - 1900",
            "Report for period 24.04.2019 - 27.04.2019\n"
                    + "John - 3700\n"
                    + "Andrew - 2000\n"
                    + "Kate - 2140"
    };

    public static void main(String[] args) {
        SalaryInfo salaryInfo = new SalaryInfo();
        System.out.println(salaryInfo.getSalaryInfo(sampleNames,scriptArray,
                "24.04.2019", "24.04.2019"));
        System.out.println(salaryInfo.getSalaryInfo(sampleNames,scriptArray,
                "24.04.2019", "25.04.2019"));

    }
}
