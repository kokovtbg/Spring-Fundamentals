package bg.softuni.judje.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "submissions")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ElementCollection
    private List<String> code;
    private int achievedResult;
    private LocalDateTime createdOn;
    @ManyToOne
    private Problem problem;
    @ManyToOne
    private User user;

    public Submission() {
        this.code = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<String> getCode() {
        return code;
    }

    public void setCode(List<String> code) {
        this.code = code;
    }

    public int getAchievedResult() {
        return achievedResult;
    }

    public void setAchievedResult(int achievedResult) {
        this.achievedResult = achievedResult;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
