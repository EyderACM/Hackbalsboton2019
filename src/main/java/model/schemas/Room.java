package model.schemas;

import javax.persistence.*;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @Column(name = "idroom")
    private int Id;
    @Column(name = "name")
    private String Name;
    @Column(name = "idarea")
    private String IdArea;

    public Room(){}

    public Room(String name, String idarea) {
        Name = name;
        IdArea = idarea;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIdArea() {
        return IdArea;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", IdArea='" + IdArea + '\'' +
                '}';
    }
}
