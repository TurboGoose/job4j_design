package tdd.templates;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@Disabled
class TemplateEngineTest {
    @Test
    public void produce_WithoutArguments_StringWithoutChanges() {
        TemplateEngine engine = new TemplateEngine();
        String template = "Hello!";
        assertThat(engine.produce(template, Collections.emptyMap()), is(template));
    }

    @Test
    public void produce_OneArgument_FormattedString() {
        TemplateEngine engine = new TemplateEngine();
        String template = "Hello, ${user}!";
        Map<String, String> values = Map.of("user", "Anton");
        assertThat(engine.produce(template, values), is("Hello, Anton!"));
    }

    @Test
    public void produce_OneArgumentUsedTwoTimes_FormattedString() {
        TemplateEngine engine = new TemplateEngine();
        String template = "Is your name ${user}? Hello, ${user}!";
        Map<String, String> values = Map.of("user", "Anton");
        assertThat(engine.produce(template, values), is("Is your name Anton? Hello, Anton!"));
    }

    @Test
    public void produce_UnusedArgumentsInMap_ThrowIllegalArgumentException() {
        TemplateEngine engine = new TemplateEngine();
        String template = "Is your name ${user}? Hello, ${user}!";
        Map<String, String> values = Map.of("user", "Anton", "city", "Moscow");
        assertThrows(IllegalArgumentException.class, () -> engine.produce(template, values));
    }

    @Test
    public void produce_UnexpectedArgumentInTemplate_ThrowIllegalArgumentException() {
        TemplateEngine engine = new TemplateEngine();
        String template = "Hello, ${user}! Are you live in ${city}?";
        Map<String, String> values = Map.of("user", "Anton");
        assertThrows(IllegalArgumentException.class, () -> engine.produce(template, values));
    }
}
