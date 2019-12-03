package model.schemas;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddevice")
    private int Id;
    @Column(name = "name")
    private String Name;
    @Column(name = "type")
    private String Type;
    @Column(name = "brand")
    private String Brand;
    @Column(name = "model")
    private String Model;
    @Column(name = "state", columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean State;
    @ManyToOne
    @JoinColumn(name = "idroom")
    private Room room;
    

    public Device(int id, String name, String type, String brand, String model, Boolean state, Group group) {
        Id = id;
        Name = name;
        Type = type;
        Brand = brand;
        Model = model;
        State = state;
        this.room = room;
    }

    public Device() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public Boolean getState() {
        return State;
    }

    public void setState(Boolean state) {
        State = state;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    
}
