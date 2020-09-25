package tasks.analyzer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class AnalyzerTest {
    List<Analyzer.User> previous = new ArrayList<>();
    List<Analyzer.User> current = new ArrayList<>();
    Analyzer analyzer = new Analyzer();

    @Test
    public void emptyUserLists() {
        assertThat(analyzer.diff(previous, current), is(new Analyzer.Info()));
    }

    @Test
    public void equalUserLists() {
        previous.add(new Analyzer.User(1, "user 1"));
        current.add(new Analyzer.User(1, "user 1"));
        assertThat(analyzer.diff(previous, current), is(new Analyzer.Info()));
    }

    @Test
    public void allUsersWereDeleted() {
        previous.add(new Analyzer.User(1, "user 1"));
        assertThat(analyzer.diff(previous, current), is(new Analyzer.Info(0, 0, 1)));
    }

    @Test
    public void allUsersWereAdded() {
        current.add(new Analyzer.User(1, "user 1"));
        assertThat(analyzer.diff(previous, current), is(new Analyzer.Info(1, 0, 0)));
    }

    @Test
    public void allUsersWereModified() {
        previous.add(new Analyzer.User(1, "user 1"));
        current.add(new Analyzer.User(1, "user 1 modified"));
        assertThat(analyzer.diff(previous, current), is(new Analyzer.Info(0, 1, 0)));
    }

    @Test
    public void userIdWasChanged() {
        previous.add(new Analyzer.User(1, "user 1"));
        current.add(new Analyzer.User(2, "user 1"));
        assertThat(analyzer.diff(previous, current), is(new Analyzer.Info(1, 0, 1)));
    }

    @Test
    public void someUsersWereAddedModifiedAndDeleted() {
        previous.add(new Analyzer.User(1, "user 1"));
        previous.add(new Analyzer.User(2, "user 2"));
        previous.add(new Analyzer.User(3, "user 3"));
        current.add(new Analyzer.User(1, "user 1"));
        current.add(new Analyzer.User(2, "user 2 modified"));
        current.add(new Analyzer.User(4, "user 4 new"));
        assertThat(analyzer.diff(previous, current), is(new Analyzer.Info(1, 1, 1)));
    }
}