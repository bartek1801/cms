package pl.com.bottega.cms.domain;


import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CinemaHallTest {

    private static final int ROWS = 10;
    private static final int SEATS = 15;
    private boolean[][] seats = new boolean[ROWS][SEATS];
    private CinemaHall sut = new CinemaHall(seats);


    public void shouldCheckSeatsAvailability(){
        //given

        //when

        //then


    }


}
