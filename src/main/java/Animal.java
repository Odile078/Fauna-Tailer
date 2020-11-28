public class Animal {
    public int id;
    public String name;
    public String type;
    public String health;
    public String age;

    public static final String ANIMAL_TYPE = "safe";

    public Animal(String name, String  type){
        this.name = name;
        this.type = ANIMAL_TYPE;
        this.health = "";
        this.age ="";
    }
    

}
