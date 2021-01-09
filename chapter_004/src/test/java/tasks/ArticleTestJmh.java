package tasks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;

public class ArticleTestJmh {
    public static void main(String[] args) throws IOException, RunnerException {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Warmup(iterations = 5)
    public void processLittleText() {
        Article.generateBy(
                "Мама мыла раму и окно",
                "мыла окно"
        );
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Warmup(iterations = 5)
    public void processBigText() {
        Article.generateBy(
                "Мой дядя самых честных правил, " +
                        "Когда не в шутку занемог, " +
                        "Он уважать себя заставил " +
                        "И лучше выдумать не мог. " +
                        "Его пример другим наука; " +
                        "Но, боже мой, какая скука " +
                        "С больным сидеть и день и ночь, " +
                        "Не отходя ни шагу прочь! " +
                        "Какое низкое коварство " +
                        "Полуживого забавлять, " +
                        "Ему подушки поправлять, " +
                        "Печально подносить лекарство, " +
                        "Вздыхать и думать про себя: " +
                        "Когда же черт возьмет тебя!",
                "Мой дядя мог думать про тебя и день и ночь"
        );
    }
}
