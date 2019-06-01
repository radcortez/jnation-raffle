package pt.jnation.raffle;

import org.eclipse.microprofile.faulttolerance.Bulkhead;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/raffle")
public class RaffleResource {
    @Inject
    private Instance<TicketGenerator> ticketGenerators;

    private Random random = new Random();
    private List<String> tickets;

    @PostConstruct
    private void init() {
        tickets =
            ticketGenerators.stream()
                            .map(TicketGenerator::generate)
                            .flatMap(Collection::stream)
                            .collect(Collectors.toList());

        Collections.shuffle(tickets);
        Collections.shuffle(tickets);
        Collections.shuffle(tickets);
        Collections.shuffle(tickets);
        Collections.shuffle(tickets);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Bulkhead(1)
    public String raffle() {
        final String winner = tickets.get(random.nextInt(tickets.size()));
        tickets.removeIf(winner::equals);
        return winner;
    }
}
