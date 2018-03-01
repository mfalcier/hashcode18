package athnn.hashcode;

import com.athnn.hashcode.model.*;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;

public class SolutionTest {

    @Test
    public void foo() {

        Rides rides = new Rides(newArrayList(
                new Ride(0L, new Point(0L,0L), new Point(1L, 3L), 2L, 9L),
                new Ride(1L, new Point(1L,2L), new Point(1L, 0L), 0L, 9L),
                new Ride(2L, new Point(2L,0L), new Point(2L, 2L), 0L, 9L)
        ));
        Vehicles vehicles = new Vehicles(2);
        City city = new City(4L, 3L);
        Configuration configuration = new Configuration(city, 2L, rides, 10L, vehicles);
        Long totalSteps = 10L;

        for(Long step = 0L; step <= totalSteps; step++) {
            configuration.getVehicles().getWaitingVehicles().assignRides(configuration, step);

            configuration.getVehicles().notifyStep(step);
        }

        vehicles.getVehicles().forEach(vehicle -> System.out.println(vehicle.getCompletedRides()));
    }
}
