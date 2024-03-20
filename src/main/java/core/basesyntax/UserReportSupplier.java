package core.basesyntax;

public class UserReportSupplier {
    private final static String HYPHEN_WITH_SPACES = " - ";
    private final UserWithSalary user;

    public UserReportSupplier(UserWithSalary user) {
        this.user = user;
    }

    public String createUserReportTextLine() {
        return System.lineSeparator() + user.getUserName()
                + HYPHEN_WITH_SPACES + user.getSalaryAmount();
    }
}

