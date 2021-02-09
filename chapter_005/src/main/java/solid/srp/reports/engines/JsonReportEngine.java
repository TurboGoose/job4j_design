package solid.srp.reports.engines;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import solid.srp.reports.Employee;
import solid.srp.reports.Report;
import solid.srp.reports.Store;

import java.util.function.Predicate;

public class JsonReportEngine implements Report {
    private final Store store;
    private final Gson gson = new GsonBuilder().create();

    public JsonReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder report = new StringBuilder();
        for (Employee employee : store.findBy(filter)) {
            report.append(convertToJson(employee)).append(System.lineSeparator());
        }
        return report.toString();
    }

    private String convertToJson(Employee employee) {
        return gson.toJson(employee);
    }
}
