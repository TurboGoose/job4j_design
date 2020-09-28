package io;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ConfigTest {
    @Test
    public void emptyPropertyFile() {
        Config conf = new Config("../files/testss/ConfigTest/empty.txt");
        conf.load();
        assertThat(conf.value(""), is(nullValue()));
    }

    @Test
    public void propertyFileWithoutCommentsAndEmptyLines() {
        Config conf = new Config("../files/tests/ConfigTest/withoutCommentsAndEmptyLines.txt");
        conf.load();
        assertThat(conf.value("property1"), is("1"));
        assertThat(conf.value("property2"), is("2"));
        assertThat(conf.value("property3"), is("3"));
    }

    @Test
    public void propertyFileWithEmptyLines() {
        Config conf = new Config("../files/tests/ConfigTest/withEmptyLines.txt");
        conf.load();
        assertThat(conf.value("property1"), is("1"));
        assertThat(conf.value("property2"), is("2"));
        assertThat(conf.value("property3"), is("3"));
        assertThat(conf.value(""), is(nullValue()));
    }

    @Test
    public void propertyFileWithComments() {
        Config conf = new Config("../files/tests/ConfigTest/withComments.txt");
        conf.load();
        assertThat(conf.value("property1"), is("1"));
        assertThat(conf.value("property2"), is("2"));
        assertThat(conf.value("property3"), is("3"));
        assertThat(conf.value("//comment"), is(nullValue()));
    }

    @Test
    public void propertyFileWithCommentsAndEmptyLines() {
        Config conf = new Config("../files/tests/ConfigTest/withCommentsAndEmptyLines.txt");
        conf.load();
        assertThat(conf.value("property1"), is("1"));
        assertThat(conf.value("property2"), is("2"));
        assertThat(conf.value("property3"), is("3"));
        assertThat(conf.value(""), is(nullValue()));
        assertThat(conf.value("//comment"), is(nullValue()));
    }
}
