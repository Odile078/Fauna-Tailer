import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class RangerTest {
    @Rule
    public DatabaseRule databaseRule=new DatabaseRule();

    @Test
    public void ranger_instantiatesCorrectly_true() {
        Ranger testRanger = new Ranger("Annita","R1","+0784455678");
        assertEquals(true, testRanger instanceof Ranger);
    }
    @Test
    public void getName_rangerInstantiatesWithName() {
        Ranger testRanger = new Ranger("Annita","R1","+0784455678");
        assertEquals("Annita", testRanger.getName());
    }
    @Test
    public void getbadge_rangerInstantiatesWithbadge() {
        Ranger testRanger = new Ranger("Annita","R1","+0784455678");
        assertEquals("R1", testRanger.getBadge_id());
    }
    @Test
    public void getphone_rangerInstantiatesWithphone() {
        Ranger testRanger = new Ranger("Annita","R1","+0784455678");
        assertEquals("+0784455678", testRanger.getPhone_number());
    }

    @Test(expected = IllegalArgumentException.class)
    public void EndangeredAnimal_throwsExceptionIfNameTypeHealthAgeCanNotBeEmpty(){
        Ranger testRanger = new Ranger("","R1","+0784455678");

        if(testRanger.name.equals("")||testRanger.badge_id.equals("")||testRanger.phone_number.equals("")){
            testRanger.save();
        }
    }
    @Test
    public void save_insertsObjectIntoDatabase_locationAndNamecanNotBeEmpty() {
        Ranger testRanger = new Ranger("Annita","R1","+0784455678");
        testRanger.save();
        assertTrue(Ranger.all().get(0).equals(testRanger));
    }

    @Test
    public void all_returnsAllInstancesOfRanger_true() {
        Ranger firstRanger = new Ranger("Annita","R1","+0784455678");
        firstRanger.save();
        Ranger secondRanger = new Ranger("Ange","R2","+0784458678");
        secondRanger.save();
        assertEquals(true, Ranger.all().get(0).equals(firstRanger));
        assertEquals(true, Ranger.all().get(1).equals(secondRanger));
    }

    @Test
    public void delete_deletesLocation_true() {
        Ranger testRanger = new Ranger("Annita","R1","+0784455678");
        testRanger.save();
        testRanger.delete();
        assertEquals(0, Ranger.all().size());
    }
    @Test
    public void save_assignsIdToObject() {
        Ranger testRanger = new Ranger("Annita","R1","+0784455678");
        testRanger.save();
        Ranger savedRanger = Ranger.all().get(0);
        assertEquals(testRanger.getId(), savedRanger.getId());
    }

    @Test
    public void find_returnsPersonWithSameId_secondRanger() {
        Ranger firstRanger = new Ranger("Annita","R1","+0784455678");
        firstRanger.save();
        Ranger secondRanger = new Ranger("Anne","R2","+0784455878");
        secondRanger.save();
        assertEquals(Ranger.find(secondRanger.getId()), secondRanger);
    }

    @Test
    public void equals_returnsTrueIfNameAreSame_true() {
        Ranger firstRanger = new Ranger("Annita","R1","+0784455678");
        Ranger anotherRanger = new Ranger("Annita","R1","+0784455678");
        assertTrue(firstRanger.equals(anotherRanger));
    }

    /*
    @Test
    public void allSightingsAreReturnedForRanger() {
        Rangers ranger=setUpNewRanger();
        try {
            Locations location=new Locations("Zone A");
            ranger.save();
            location.save();
            Sightings sighting=new Sightings(location.getId(),ranger.getId(),1);
            Sightings otherSighting=new Sightings(1,ranger.getId(),1);
            sighting.save();
            otherSighting.save();
            assertEquals(ranger.getRangerSightings().get(0),sighting);
            assertEquals(ranger.getRangerSightings().get(1),otherSighting);
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }

    }
    */



}