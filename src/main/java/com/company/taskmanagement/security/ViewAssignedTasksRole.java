package com.company.taskmanagement.security;

import com.company.taskmanagement.entity.Task;
import com.company.taskmanagement.entity.TaskPriority;
import io.jmix.security.model.RowLevelPolicyAction;
import io.jmix.security.model.RowLevelPredicate;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.PredicateRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;

@RowLevelRole(name = "ViewAssignedTasks", code = "view-assigned-tasks")
public interface ViewAssignedTasksRole {

    @JpqlRowLevelPolicy(entityClass = Task.class, where = "{E}.assignee.id = :current_user_id")
    void task();

    @PredicateRowLevelPolicy(
            entityClass = Task.class,
            actions = {RowLevelPolicyAction.CREATE})
    default RowLevelPredicate<Task> createOnlyLowPriorityTasks() {
        return task -> task.getPriority() != TaskPriority.HIGH;
    }
}