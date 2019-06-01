package pt.jnation.raffle;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@ApplicationScoped
public class CSVTicketGenerator implements TicketGenerator {
    @Override
    public List<String> generate() {
        final List<String> tickets = new ArrayList<>();

        try {
            final URL resource = Thread.currentThread().getContextClassLoader().getResource("csv/import.csv");
            final Reader in = new InputStreamReader(resource.openStream());
            final Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);

            for (final CSVRecord record : records) {
                final String firstName = record.get(1);
                final String lastName = record.get(2);
                final String email = record.get(5);

                final int ticketsPerPerson =
                    IntStream.of(Integer.valueOf(record.get(13)) * 3,   // notebooks
                                 Integer.valueOf(record.get(13)) * 2,   // powerbank
                                 Integer.valueOf(record.get(13)) * 4,   // pi
                                 Integer.valueOf(record.get(13)))       // support
                             .sum();

                for (int i = 0; i < ticketsPerPerson; i++) {
                    tickets.add(firstName + " " + lastName + "<br>" + email);
                }
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return tickets;
    }
}
