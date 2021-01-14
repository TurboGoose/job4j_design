package tdd.cinema;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import tdd.cinema.impls.AccountCinema;
import tdd.cinema.impls.Cinema3D;
import tdd.cinema.impls.Session3D;
import tdd.cinema.impls.Ticket3D;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Disabled
class CinemaTest {
    @Test
    public void whenBuyTicketThenReturnTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test
    public void whenBuyTicketWithIncorrectDateThenReturnNull() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(3020, Calendar.NOVEMBER, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(nullValue()));
    }

    @Test
    public void whenAddAndFindActiveSessionsThenReturnListOfOneSession() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(List.of(new Session3D())));
    }

    @Test
    public void whenFindActiveSessionsInEmptyCinemaThenReturnEmptyList() {
        Cinema cinema = new Cinema3D();
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Collections.emptyList()));
    }
}