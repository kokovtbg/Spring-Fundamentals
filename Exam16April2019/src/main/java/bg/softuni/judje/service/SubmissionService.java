package bg.softuni.judje.service;

import bg.softuni.judje.model.Problem;
import bg.softuni.judje.model.Submission;
import bg.softuni.judje.model.User;
import bg.softuni.judje.model.dto.SubmissionCreateDTO;
import bg.softuni.judje.repository.ProblemRepository;
import bg.softuni.judje.repository.SubmissionRepository;
import bg.softuni.judje.repository.UserRepository;
import bg.softuni.judje.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SubmissionService {
    private SubmissionRepository submissionRepository;
    private UserRepository userRepository;
    private ProblemRepository problemRepository;
    private CurrentUser currentUser;

    @Autowired
    public SubmissionService(SubmissionRepository submissionRepository,
                             UserRepository userRepository,
                             ProblemRepository problemRepository,
                             CurrentUser currentUser) {
        this.submissionRepository = submissionRepository;
        this.userRepository = userRepository;
        this.problemRepository = problemRepository;
        this.currentUser = currentUser;
    }

    public List<Submission> findAllByUserAndProblem(Problem problem) {
        User user = this.userRepository.findByUsername(this.currentUser.getUsername());
        return this.submissionRepository.findAllByUserAndProblem(user, problem);
    }

    public void createSubmission(SubmissionCreateDTO submissionCreate, Problem problem) {
        Submission submission = new Submission();
        List<String> submissionCode = Arrays.stream(submissionCreate.getCode().split("\n")).toList();
        submission.setCode(submissionCode);
        Random random = new Random();
        int randomResult = random.nextInt(problem.getPoints() + 1);
        submission.setAchievedResult(randomResult);
        submission.setCreatedOn(LocalDateTime.now());
        submission.setProblem(problem);
        User user = this.userRepository.findByUsername(this.currentUser.getUsername());
        submission.setUser(user);
        this.submissionRepository.save(submission);
    }

    public Submission findById(long id) {
        return this.submissionRepository.findById(id).get();
    }
}
