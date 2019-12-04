package model.schemas;
import org.hibernate.annotations.Type;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password", columnDefinition = "LONGTEXT")   
    private String password;
    @Column(name = "question")
    private int question;
    @Column(name = "answer", columnDefinition = "LONGTEXT")
    private String answer;

    public User(){}

    public User(String name, String email, String password, int question, String answer) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.question = question;
        this.answer = answer;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getQuestion() {
        return question;
    }
    public void setQuestion(int question) {
        this.question = question;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }  
    
    
    public String getInfo() {
        return "User{" +
                "Id=" + id +
                ", Name='" + name + '\'' +
                ", Email='" + email + '\'' +
                ", Password='" + password + '\'' +
                ", Question='" + question + '\'' +
                ", Answer='" + answer + '\'' +
                '}';
    }
}