package solid.srp.reports.engines;

import solid.srp.reports.Employee;
import solid.srp.reports.Report;
import solid.srp.reports.Store;

import java.util.function.Predicate;

public class AccountingReportEngine implements Report {
    private final Store store;

    public AccountingReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;").append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(convertRublesToDollars(employee.getSalary())).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    private double convertRublesToDollars(double rubles) {
        return rubles / 80;
    }
}
