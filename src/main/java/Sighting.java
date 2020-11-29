import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Sighting {
    private int id;
    private int location_id;
    private int ranger_id;
    private int animal_id;
    private Date date= new Date();
    private Timestamp time;

    public Sighting(int location_id, int ranger_id, int animal_id) {
        this.location_id = location_id;
        this.ranger_id = ranger_id;
        this.animal_id = animal_id;
        this.time = new Timestamp(date.getTime());

    }

    public int getId() {
        return id;
    }

    public int getLocation_id() {
        return location_id;
    }

    public int getRanger_id() {
        return ranger_id;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public Timestamp getTime() {
        return time;
    }

    public static List<Sighting> all(){
        try (Connection con =DB.sql2o.open()){
            String sql=("SELECT * FROM sightings");
            return con.createQuery(sql)
                    .executeAndFetch(Sighting.class);

        }
    }

    public static Sighting find(int id){
        try (Connection con=DB.sql2o.open()){
            String sql="SELECT * FROM sightings WHERE id=:id";
            return con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Sighting.class);

        }
    }

    public void delete(){
        try (Connection con=DB.sql2o.open()){
            String sql="DELETE FROM sightings WHERE id=:id";
            con.createQuery(sql)
                    .addParameter("id",this.id)
                    .executeUpdate();
        }

    }
    public static void deleteAll(){
        try (Connection con=DB.sql2o.open()){
            String sql="DELETE FROM sightings";
            con.createQuery(sql)
                    .executeUpdate();
        }

    }

    public void save(){

        if(this.animal_id==-1||this.location_id==-1||this.ranger_id==-1){
            throw new IllegalArgumentException("fill all the fields");
        }
        try (Connection con=DB.sql2o.open()){
            String sql= "INSERT INTO sightings (animal_id,ranger_id,location_id,time) VALUES (:animal_id,:ranger_id," +
                    ":location_id,:time)";
            String joinRanger="INSERT INTO rangers_sightings (ranger_id,sighting_id) VALUES (:ranger_id,:sighting_id)";
            String joinLocation="INSERT INTO locations_sightings (location_id,sighting_id) VALUES (:location_id," +
                    ":sighting_id)";

            this.id=(int) con.createQuery(sql,true)
                    .addParameter("animal_id",this.animal_id)
                    .addParameter("ranger_id",this.ranger_id)
                    .addParameter("location_id",this.location_id)
                    .addParameter("time",this.time)
                    .executeUpdate()
                    .getKey();

            con.createQuery(joinRanger).addParameter("ranger_id",this.getRanger_id()).addParameter("sighting_id",
                    this.getId()).executeUpdate();
            con.createQuery(joinLocation).addParameter("location_id",this.getLocation_id()).addParameter("sighting_id",
                    this.id).executeUpdate();

        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sighting sighting = (Sighting) o;
        return id == sighting.id &&
                location_id == sighting.location_id &&
                ranger_id == sighting.ranger_id &&
                animal_id == sighting.animal_id &&
                Objects.equals(date, sighting.date) &&
                Objects.equals(time, sighting.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location_id, ranger_id, animal_id, date, time);
    }
}
