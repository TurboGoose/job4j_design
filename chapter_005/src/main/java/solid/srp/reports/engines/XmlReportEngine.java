package solid.srp.reports.engines;

import solid.srp.reports.Employee;
import solid.srp.reports.Report;
import solid.srp.reports.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.function.Predicate;

public class XmlReportEngine implements Report {
    private final Store store;
    private Marshaller marshaller;

    public XmlReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder report = new StringBuilder();
        for (Employee employee : store.findBy(filter)) {
            report.append(convertToXml(employee)).append(System.lineSeparator());
        }
        return report.toString();
    }

    private String convertToXml(Employee employee) {
        if (marshaller == null) {
            init();
        }
        String resultXml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(employee, writer);
            resultXml = writer.getBuffer().toString();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return resultXml;
    }

    private void init() {
        try {
            JAXBContext context = JAXBContext.newInstance(Employee.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException exc) {
            throw new IllegalStateException(exc);
        }
    }
}
