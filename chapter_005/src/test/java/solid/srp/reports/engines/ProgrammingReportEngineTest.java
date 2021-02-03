package solid.srp.reports.engines;

import org.junit.jupiter.api.Test;
import solid.srp.reports.Employee;
import solid.srp.reports.MemStore;
import solid.srp.reports.Report;

import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ProgrammingReportEngineTest {
    @Test
    public void whenProgrammingReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ProgrammingReportEngine(store);
        StringBuilder expected = new StringBuilder().append("<html>\r\n")
                .append("Name; Hired; Fired; Salary;").append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator()).append("</html>");
        assertThat(engine.generate(em -> true), is(expected.toString()));
    }
}