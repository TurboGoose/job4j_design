package io.iostreams.zip;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class ArgZipTest {
    ArgZip argZip;

    @Test
    public void passRequiredAndOptionalArguments() {
        argZip = new ArgZip("-d directory -e exclude -o output".split(" "));
        assertThat(argZip.valid(), is(true));
        assertThat(argZip.directory(), is("directory"));
        assertThat(argZip.exclude(), is("exclude"));
        assertThat(argZip.output(), is("output"));
    }

    @Test
    public void passOnlyRequiredArguments() {
        argZip = new ArgZip("-o output -d directory".split(" "));
        assertThat(argZip.valid(), is(true));
        assertThat(argZip.directory(), is("directory"));
        assertThat(argZip.exclude(), is(nullValue()));
        assertThat(argZip.output(), is("output"));
    }

    @Test
    public void passOnlyOptionalArgument() {
        argZip = new ArgZip("-e exclude".split(" "));
        assertThat(argZip.valid(), is(false));
        assertThrows(UnsupportedOperationException.class, argZip::directory);
        assertThrows(UnsupportedOperationException.class, argZip::exclude);
        assertThrows(UnsupportedOperationException.class, argZip::output);
    }

    @Test
    public void passEmptyString() {
        argZip = new ArgZip("".split(" "));
        assertThat(argZip.valid(), is(false));
        assertThrows(UnsupportedOperationException.class, argZip::directory);
        assertThrows(UnsupportedOperationException.class, argZip::exclude);
        assertThrows(UnsupportedOperationException.class, argZip::output);
    }

    @Test
    public void passEmptyArguments() {
        argZip = new ArgZip(new String[] {"-d", "", "-e", "", "-o", ""});
        assertThat(argZip.valid(), is(true));
        assertThat(argZip.directory(), is(""));
        assertThat(argZip.exclude(), is(""));
        assertThat(argZip.output(), is(""));
    }

    @Test
    public void passLessArguments() {
        argZip = new ArgZip("-d directory -e exclude -o".split(" "));
        assertThat(argZip.valid(), is(false));
        assertThrows(UnsupportedOperationException.class, argZip::directory);
        assertThrows(UnsupportedOperationException.class, argZip::exclude);
        assertThrows(UnsupportedOperationException.class, argZip::output);
    }

    @Test
    public void passMoreArguments() {
        argZip = new ArgZip("-d directory -e exclude -o output -excess".split(" "));
        assertThat(argZip.valid(), is(false));
        assertThrows(UnsupportedOperationException.class, argZip::directory);
        assertThrows(UnsupportedOperationException.class, argZip::exclude);
        assertThrows(UnsupportedOperationException.class, argZip::output);
    }

    @Test
    public void wrongOrder() {
        argZip = new ArgZip("directory -d -e exclude -o output".split(" "));
        assertThat(argZip.valid(), is(false));
        assertThrows(UnsupportedOperationException.class, argZip::directory);
        assertThrows(UnsupportedOperationException.class, argZip::exclude);
        assertThrows(UnsupportedOperationException.class, argZip::output);
    }
}
