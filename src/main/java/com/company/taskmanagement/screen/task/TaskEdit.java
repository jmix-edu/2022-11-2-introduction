package com.company.taskmanagement.screen.task;

import com.company.taskmanagement.entity.Project;
import com.company.taskmanagement.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.core.usersubstitution.CurrentUserSubstitution;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.company.taskmanagement.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@UiController("tm_Task.edit")
@UiDescriptor("task-edit.xml")
@EditedEntityContainer("taskDc")
public class TaskEdit extends StandardEditor<Task> {

    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Autowired
    private CurrentUserSubstitution currentUserSubstitution;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Task> event) {
        User user = (User) currentUserSubstitution.getEffectiveUser();
        event.getEntity().setAssignee(user);
    }

    @Subscribe("projectField")
    public void onProjectFieldValueChange(HasValue.ValueChangeEvent<Project> event) {
        if (event.isUserOriginated()) {
            Project project = event.getValue();
            if (project != null) {
                getEditedEntity().setPriority(project.getDefaultTaskPriority());
            }
        }
    }

   /* @Subscribe(id = "taskDc", target = Target.DATA_CONTAINER)
    public void onTaskDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<Task> event) {
        if ("project".equals(event.getProperty())) {
            Project newProject = (Project) event.getValue();
            if (newProject != null) {
                event.getItem().setPriority(newProject.getDefaultTaskPriority());
            } else {
                event.getItem().setPriority(null);
            }
        }
    }*/


}