package com.company.taskmanagement.security;

import com.company.taskmanagement.entity.Project;
import com.company.taskmanagement.entity.Task;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

@ResourceRole(name = "TaskCreator", code = "task-creator", scope = "UI")
public interface TaskCreatorRole {
    @MenuPolicy(menuIds = {"tm_Task.browse", "tm_Project.browse"})
    @ScreenPolicy(screenIds = {"tm_Task.browse", "tm_Task.edit", "tm_Project.browse"})
    void screens();

    @EntityAttributePolicy(entityClass = Project.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Project.class, actions = EntityPolicyAction.READ)
    void project();

    @EntityAttributePolicy(entityClass = Task.class, attributes = {"id", "name", "assignee", "project", "priority", "subTasks"}, action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Task.class, actions = EntityPolicyAction.ALL)
    void task();
}