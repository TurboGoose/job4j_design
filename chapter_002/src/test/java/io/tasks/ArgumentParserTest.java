package io.tasks;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class ArgumentParserTest {

    @Test
    public void normalUse() {
        ArgumentParser parser = new ArgumentParser("-d c:/ -n *.txt -m -o log.txt".split(" "));
        assertThat(parser.isValid(), is(true));
        assertThat(parser.getFileName(), is("*.txt"));
        assertThat(parser.getOutputFile(), is("log.txt"));
        assertThat(parser.getSearchDirectory(), is("c:/"));
        assertThat(parser.getSearchOption(), is(ArgumentParser.SearchOption.MASK));
    }

    @Test
    public void parseEmptyArguments() {
        ArgumentParser parser = new ArgumentParser(new String[0]);
        assertThat(parser.isValid(), is(false));
        assertThrows(UnsupportedOperationException.class, parser::getFileName);
        assertThrows(UnsupportedOperationException.class, parser::getOutputFile);
        assertThrows(UnsupportedOperationException.class, parser::getSearchDirectory);
        assertThrows(UnsupportedOperationException.class, parser::getSearchOption);
    }

    @Test
    public void parseUnexpectedArguments() {
        ArgumentParser parser = new ArgumentParser("-u -d c:/ -n *.txt -m -o log.txt".split(" "));
        assertThat(parser.isValid(), is(false));
    }

    @Test
    public void parseLessArgumentsThanRequired() {
        ArgumentParser parser = new ArgumentParser("-n *.txt -o log.txt".split(" "));
        assertThat(parser.isValid(), is(false));
    }

    @Test
    public void parseDuplicateArguments() {
        ArgumentParser parser = new ArgumentParser("-d c:/ -d d:/ -n *.txt -m -r -o log.txt".split(" "));
        assertThat(parser.isValid(), is(false));
    }

    @Test
    public void parseArgumentsWithEmptyParameterInTheEnd() {
        ArgumentParser parser = new ArgumentParser("-d c:/ -d d:/ -n *.txt -m -r -o".split(" "));
        assertThat(parser.isValid(), is(false));
    }
}
