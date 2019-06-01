package pt.jnation.raffle;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class CSVTicketGeneratorTest {
    @Inject
    private CSVTicketGenerator generator;

    @Test
    void generate() {
        final List<String> generate = generator.generate();
        assertEquals(20, generate.size());
    }
}
