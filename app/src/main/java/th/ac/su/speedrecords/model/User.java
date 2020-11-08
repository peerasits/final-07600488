package th.ac.su.speedrecords.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey(autoGenerate = true)
    public final int id;

    @ColumnInfo(name = "speed")
    public final double speed;

    @ColumnInfo(name = "distance")
    public final double distance;

    @ColumnInfo(name = "time")
    public final double time;

    @ColumnInfo(name = "over")
    public final boolean over;


    public User(int id, double speed,double distance,double time,boolean over) {
        this.id = id;
        this.speed=speed;
        this.distance= distance;
        this.time = time;
        this.over = over;

    }
}
