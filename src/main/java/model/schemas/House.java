package model.schemas;
import javax.persistence.*;


@Entity
@Table(name = "houses")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhouse")
    private int Id;
    @Column(name = "name")
    private String Name;

    public House() {
    }

    public House(String name) {
        Name = name;
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

    @Override
    public String toString() {
        return "House{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                '}';
    }
}
