package bg.softuni.gira.service;

import bg.softuni.gira.model.Classification;
import bg.softuni.gira.model.Progress;
import bg.softuni.gira.model.Task;
import bg.softuni.gira.model.User;
import bg.softuni.gira.model.dto.TaskAddDTO;
import bg.softuni.gira.repository.ClassificationRepository;
import bg.softuni.gira.repository.TaskRepository;
import bg.softuni.gira.repository.UserRepository;
import bg.softuni.gira.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private ClassificationRepository classificationRepository;
    private CurrentUser currentUser;

    @Autowired
    public TaskService(TaskRepository taskRepository,
                       UserRepository userRepository,
                       ClassificationRepository classificationRepository,
                       CurrentUser currentUser) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.classificationRepository = classificationRepository;
        this.currentUser = currentUser;
    }

    public void addTask(TaskAddDTO addTask) {
        Task task = new Task();
        task.setName(addTask.getName());
        task.setDescription(addTask.getDescription());
        task.setDueDate(addTask.getDueDate());
        Classification classification = this.classificationRepository
                .findByClassificationName(addTask.getClassification());
        task.setClassification(classification);
        task.setProgress(Progress.OPEN);
        User user = this.userRepository.findByEmail(this.currentUser.getEmail());
        task.setUser(user);
        this.taskRepository.save(task);
    }

    public boolean checkForNameAndProgress(TaskAddDTO addTask) {
        Task taskByName = this.taskRepository.findByName(addTask.getName());
        if (taskByName != null) {
            return false;
        }
//        Task taskByProgress = this.taskRepository.findByProgress(Progress.OPEN);
//        if (taskByProgress != null) {
//            return false;
//        }
        return true;
    }

    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    public void progressTask(long id) {
        Task task = this.taskRepository.findById(id).get();
        String progress = task.getProgress().toString();
        switch (progress) {
            case "OPEN" -> {
                task.setProgress(Progress.IN_PROGRESS);
                this.taskRepository.save(task);
            }
            case "IN_PROGRESS" -> {
                task.setProgress(Progress.COMPLETED);
                this.taskRepository.save(task);
            }
            case "COMPLETED" -> this.taskRepository.delete(task);
        }
    }
}
