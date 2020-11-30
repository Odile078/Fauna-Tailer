import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SightingTest {
    @Rule
    public DatabaseRule databaseRule=new DatabaseRule();

    @Test
    public void sighting_instantiatesCorrectly_true() {
        Sighting testSighting = new Sighting(1, 1,1);
        assertEquals(true, testSighting instanceof Sighting);
    }

    @Test
    public void getName_communityInstantiatesWithName_true() {
        Sighting testSighting = new Sighting(1, 1,1);
        assertEquals(1, testSighting.getLocation_id());
    }




}