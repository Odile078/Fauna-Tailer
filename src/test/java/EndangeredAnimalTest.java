import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EndangeredAnimalTest {
    @Rule
    public DatabaseRule databaseRule=new DatabaseRule();

    @Test
    public void EndangeredAnimal_instantiatesCorrectly_true() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Monkey","endangered","healthy","newborn");
        assertEquals(true, testEndangeredAnimal instanceof EndangeredAnimal);
    }

    @Test
    public void EndangeredAnimall_instantiatesWithName_String() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Monkey","endangered","healthy","newborn");
        assertEquals("Monkey", testEndangeredAnimal.getName());
    }

    @Test
    public void EndangeredAnimall_instantiatesWithType_String() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Monkey","endangered","healthy","newborn");
        assertEquals("endangered", testEndangeredAnimal.getType());
    }
    @Test
    public void EndangeredAnimall_instantiatesWithHealth_String() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Monkey","endangered","healthy","newborn");
        assertEquals("healthy", testEndangeredAnimal.getHealth());
    }
    @Test
    public void EndangeredAnimall_instantiatesWithAge_String() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Monkey","endangered","healthy","newborn");
        assertEquals("newborn", testEndangeredAnimal.getAge());
    }
    @Test(expected = IllegalArgumentException.class)
    public void EndangeredAnimal_throwsExceptionIfNameTypeHealthAgeCanNotBeEmpty(){
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Monkey","endangered","healthy","");

        if(testEndangeredAnimal.name.equals("")||testEndangeredAnimal.type.equals("")||testEndangeredAnimal.health.equals("")||testEndangeredAnimal.age.equals("")||testEndangeredAnimal.name.equals(null)||testEndangeredAnimal.type.equals(null)){
            testEndangeredAnimal.save();
        }
    }

    @Test
    public void save_assignsIdToObject() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Monkey","endangered","healthy","newborn");
        testEndangeredAnimal.save();
        Animal savedEndangeredAnimal = Animal.all().get(0);
        assertEquals(testEndangeredAnimal.getId(), savedEndangeredAnimal.getId());
    }

    @Test
    public void save_assignsNameToObjectAndCanNotBeEmpty() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Monkey","endangered","healthy","newborn");
        testEndangeredAnimal.save();
        Animal savedEndangeredAnimal = Animal.all().get(0);
        assertEquals(testEndangeredAnimal.getName(), savedEndangeredAnimal.getName());
    }
    @Test
    public void save_assignsHealthToObjectAndCanNotBeEmpty() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Monkey","endangered","healthy","newborn");
        testEndangeredAnimal.save();
        Animal savedEndangeredAnimal = Animal.all().get(0);
        assertEquals(testEndangeredAnimal.getHealth(), savedEndangeredAnimal.getHealth());
    }
    @Test
    public void save_assignsAgeToObjectAndCanNotBeEmpty() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Monkey","endangered","healthy","newborn");
        testEndangeredAnimal.save();
        Animal savedEndangeredAnimal = Animal.all().get(0);
        assertEquals(testEndangeredAnimal.getAge(), savedEndangeredAnimal.getAge());
    }



    @Test
    public void deleteById() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Monkey","endangered","healthy","newborn");
        testEndangeredAnimal.save();
        testEndangeredAnimal.delete();
        assertEquals(null,Animal.find(testEndangeredAnimal.getId()));
    }

    @Test
    public void deleteAllEntries() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Monkey","endangered","healthy","newborn");
        EndangeredAnimal otherEndangeredAnimal = new EndangeredAnimal("Giraffe","endangered","healthy","newborn");
        testEndangeredAnimal.save();
        otherEndangeredAnimal.save();
        Animal.deleteAll();
        List<Animal> animals=Animal.all();
        assertEquals(0,animals.size());
    }


}