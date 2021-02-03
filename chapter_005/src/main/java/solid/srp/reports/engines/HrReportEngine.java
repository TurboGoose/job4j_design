package solid.srp.reports.engines;

import solid.srp.reports.Employee;
import solid.srp.reports.Report;
import solid.srp.reports.Store;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class HrReportEngine implements Report {
    private final Store store;

    public HrReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        employees.sort(Comparator.comparing(Employee::getSalary).reversed());
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;").append(System.lineSeparator());
        for (Employee employee : employees) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
