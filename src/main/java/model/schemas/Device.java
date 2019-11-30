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
    @ManyToOne
    @JoinColumn(name = "iduser")
    private User user;
    

    public Device() {
    }

    public Device(String name, String type, String brand, String model, Boolean state, Room room, User user) {
        Name = name;
        Type = type;
        Brand = brand;
        Model = model;
        State = state;
        this.room = room;
        this.user = user;
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
    
    public User getUser(){
        return user;
    }
    
    public void setUser(User user){
        this.user = user;
    }
}
