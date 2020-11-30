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
    public void getlocation_id_SightinInstantiatesWithName_true() {
        Sighting testSighting = new Sighting(1, 1,1);
        assertEquals(1, testSighting.getLocation_id());
    }
    @Test
    public void getranger_id_sightingInstantiatesWithName_true() {
        Sighting testSighting = new Sighting(1, 1,1);
        assertEquals(1, testSighting.getRanger_id());
    }
    @Test
    public void getanimal_id_sightingInstantiatesWithName_true() {
        Sighting testSighting = new Sighting(1, 1,1);
        assertEquals(1, testSighting.getAnimal_id());
    }

    @Test
    public void allInstancesAreSaved() {
        Sighting sighting=new Sighting(1, 1,1);
        Sighting otherSighting=new Sighting(-1,1,1);
        try {
            sighting.save();
            otherSighting.save();
            assertTrue(Sighting.find(sighting.getId()).equals(sighting));
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }
    }


    @Test
    public void deleteSightingByID() {
        Sighting sighting=new Sighting(1, 1,1);
        sighting.save();
        sighting.delete();
        assertEquals(null,Sighting.find(sighting.getId()));

    }
    @Test
    public void deleteAll() {
        Sighting sighting=new Sighting(1, 1,1);
        Sighting otherSightings=new Sighting(1, 1,1);
        sighting.save();
        otherSightings.save();
        Sighting.deleteAll();

        assertEquals(0,Sighting.all().size());

    }







}