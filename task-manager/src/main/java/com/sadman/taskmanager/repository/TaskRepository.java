package com.sadman.taskmanager.repository;

import com.sadman.taskmanager.model.Project;
import com.sadman.taskmanager.model.Status;
import com.sadman.taskmanager.model.Task;
import com.sadman.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sadman
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllByProjectId(int projectId);
    List<Task> findAllByUserId(int userId);
    List<Task> findAllByStatusAndUser(Status status, User user);

    @Query("SELECT t FROM Task t where t.endDate < CURRENT_DATE and t.user.id = :userId")
    List<Task> findExpiredTasks(@Param("userId") int userId);
}
