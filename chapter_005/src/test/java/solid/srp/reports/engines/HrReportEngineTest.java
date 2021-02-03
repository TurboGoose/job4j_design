package solid.srp.reports.engines;

import org.junit.jupiter.api.Test;
import solid.srp.reports.Employee;
import solid.srp.reports.MemStore;
import solid.srp.reports.Report;

import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class HrReportEngineTest {
    @Test
    public void whenHrReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Anton", now, now, 10);
        store.add(worker1);
        store.add(worker2);
        Report engine = new HrReportEngine(store);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;").append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expected.toString()));
    }
}