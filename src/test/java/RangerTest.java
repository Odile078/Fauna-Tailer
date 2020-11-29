import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class RangerTest {
    @Rule
    public DatabaseRule databaseRule=new DatabaseRule();

    @Test
    public void location_instantiatesCorrectly_true() {
        Ranger testRanger = new Ranger("Annita","R1","+0784455678");
        assertEquals(true, testRanger instanceof Ranger);
    }
    @Test
    public void getName_locationInstantiatesWithName() {
        Ranger testRanger = new Ranger("Annita","R1","+0784455678");
        assertEquals("Annita", testRanger.getName());
    }


}