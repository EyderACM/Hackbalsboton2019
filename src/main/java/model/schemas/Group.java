package model.schemas;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @Column(name = "idgroup")
    private int Id;
    @Column(name = "name")
    private String Name;

    public Group() {
    }

    public Group(int id, String name, House house) {
        Id = id;
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "Id=" + Id +
                ", Name='" + Name +
                '}';
    }
}
