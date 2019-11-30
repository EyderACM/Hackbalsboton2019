package model.schemas;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idarea")
    private int Id;
    @Column(name = "name")
    private String Name;
    @ManyToOne
    @JoinColumn(name = "idhouses")
    private House house;

    public Area() {
    }

    public Area(String name, House house) {
        Name = name;
        this.house = house;
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

    public House getHouse() {
        return house;
    }


    @Override
    public String toString() {
        return "Area{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", house=" + house.getId() +
                '}';
    }
}
