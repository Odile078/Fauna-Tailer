import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {
    @Rule
    public DatabaseRule databaseRule=new DatabaseRule();



    @Test
    public void location_instantiatesCorrectly_true() {
        Location testLocation = new Location("ZoneA");
        assertEquals(true, testLocation instanceof Location);
    }

    @Test
    public void getName_locationInstantiatesWithName() {
        Location testLocation = new Location("ZoneA");
        assertEquals("ZoneA", testLocation.getName());
    }
    @Test(expected = IllegalArgumentException.class)
    public void EndangeredAnimal_throwsExceptionIfNameTypeHealthAgeCanNotBeEmpty(){
        Location testLocation = new Location("");

        if(testLocation.name.equals("")){
            testLocation.save();
        }
    }

    @Test
    public void save_insertsObjectIntoDatabase_locationAndNamecanNotBeEmpty() {
        Location testLocation = new Location("ZoneA");
        testLocation.save();
        assertTrue(Location.all().get(0).equals(testLocation));
    }

    @Test
    public void all_returnsAllInstancesOfLocation_true() {
        Location firstLocation = new Location("ZoneA");
        firstLocation.save();
        Location secondLocation = new Location("ZoneB");
        secondLocation.save();
        assertEquals(true, Location.all().get(0).equals(firstLocation));
        assertEquals(true, Location.all().get(1).equals(secondLocation));
    }
    @Test
    public void delete_deletesLocation_true() {
        Location testLocation = new Location("ZoneA");
        testLocation.save();
        testLocation.delete();
        assertEquals(0, Location.all().size());
    }

    @Test
    public void save_assignsIdToObject() {
        Location testLocation = new Location("ZoneA");
        testLocation.save();
        Location savedLocation = Location.all().get(0);
        assertEquals(testLocation.getId(), savedLocation.getId());
    }

    @Test
    public void find_returnsPersonWithSameId_secondPerson() {
        Location firstLocation = new Location("ZoneA");
        firstLocation.save();
        Location secondLocation = new Location("ZoneB");
        secondLocation.save();
        assertEquals(Location.find(secondLocation.getId()), secondLocation);
    }






    @Test
    public void equals_returnsTrueIfNameAreSame_true() {
        Location firstLocation = new Location("ZoneA");
        Location anotherLocation = new Location("ZoneA");
        assertTrue(firstLocation.equals(anotherLocation));
    }








}