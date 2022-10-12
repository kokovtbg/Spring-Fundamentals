package bg.softuni.judje.service;

import bg.softuni.judje.model.Problem;
import bg.softuni.judje.model.dto.ProblemCreateDTO;
import bg.softuni.judje.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemService {
    private ProblemRepository problemRepository;

    @Autowired
    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public List<Problem> findAll() {
        return this.problemRepository.findAll();
    }

    public void createProblem(ProblemCreateDTO problemCreate) {
        Problem problem = new Problem();
        problem.setName(problemCreate.getName());
        problem.setPoints(problemCreate.getPoints());
        this.problemRepository.save(problem);
    }

    public Problem findByName(String name) {
        return this.problemRepository.findByName(name);
    }
}
