import org.junit.Rule;
import org.junit.Test;
import org.sql2o.*;

import java.util.List;

import static org.junit.Assert.*;

public class AnimalTest {
    @Rule
   public DatabaseRule databaseRule=new DatabaseRule();

    @Test
    public void Animal_instantiatesCorrectly_true() {
        Animal testAnimal = new Animal("Monkey","safe");
        assertEquals(true, testAnimal instanceof Animal);
    }
    @Test
    public void Animal_instantiatesWithName_String() {
        Animal testAnimal = new Animal("Monkey","safe");
        assertEquals("Monkey", testAnimal.getName());
    }
    @Test
    public void Animal_instantiatesWithType_StringAndIsTheAssignedConstant() {
        Animal testAnimal = new Animal("Monkey","safe");
        assertEquals("safe", testAnimal.getType());
    }
    @Test
    public void Animal_instantiatesWithHealth_StringAndIsTheAssignedvalue() {
        Animal testAnimal = new Animal("Monkey","");
        assertEquals("", testAnimal.getHealth());
    }
    @Test
    public void Animal_instantiatesWithAge_StringAndIsTheAssignedvalue() {
        Animal testAnimal = new Animal("Monkey","");
        assertEquals("", testAnimal.getAge());
    }

    @Test(expected = IllegalArgumentException.class)
    public void Animal_throwsExceptionIfNameTypeCanNotBeEmpty(){
        Animal testAnimal = new Animal("","safe");

        if(testAnimal.name.equals("")||testAnimal.type.equals("")||testAnimal.name.equals(null)||testAnimal.type.equals(null)){
            testAnimal.save();
        }
    }

   @Test
   public void save_assignsIdToObject() {
        Animal testAnimal=new Animal("Monkey","");
        testAnimal.save();
        Animal savedAnimal = Animal.all().get(0);
        assertEquals(testAnimal.getId(), savedAnimal.getId());
   }
    @Test
    public void save_assignsNameToObjectAndCanNotBeEmpty() {
        Animal testAnimal=new Animal("Monkey","");
        testAnimal.save();
        Animal savedAnimal = Animal.all().get(0);
        assertEquals(testAnimal.getName(), savedAnimal.getName());
    }
    @Test
    public void allInstancesAreSaved() {
        Animal testAnimal=new Animal("Monkey","");
        testAnimal.save();
        assertTrue(Animal.all().get(0).equals(testAnimal));
    }

    @Test
    public void ensureEntryIsUpdatedCorrectlyAndNonCanBeEmpty() {
        Animal testAnimal=new Animal("Monkey","");
        Animal otherAnimal=testAnimal;
        testAnimal.save();
        try {
            testAnimal.update(testAnimal.getId(),"endangered","ill","newborn");
            Animal updatedAnimal=  Animal.find(testAnimal.getId());
            assertEquals(updatedAnimal.getId(),otherAnimal.getId());
            assertNotEquals(updatedAnimal.getHealth(),otherAnimal.getHealth());
        }catch (IllegalArgumentException e){

        }
    }

    @Test
    public void findByIdReturnsCorrectInfo() {
        Animal testAnimal=new Animal("Monkey","");
        testAnimal.save();
        Animal foundAnimal= Animal.find(testAnimal.getId());
        assertTrue(foundAnimal.equals(testAnimal));
    }

    @Test
    public void deleteById() {
        Animal testAnimal=new Animal("Monkey","");
        testAnimal.save();
        testAnimal.delete();
        assertEquals(null,Animal.find(testAnimal.getId()));
    }

    @Test
    public void deleteAllEntries() {
        Animal testAnimal=new Animal("Monkey","");
        Animal otherAnimal=new Animal("Giraffe","");
        testAnimal.save();
        otherAnimal.save();
        Animal.deleteAll();
        List<Animal> animals=Animal.all();
        assertEquals(0,animals.size());
    }



}