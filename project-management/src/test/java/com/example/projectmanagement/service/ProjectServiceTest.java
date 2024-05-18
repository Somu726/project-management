package com.example.projectmanagement.service;

import com.example.projectmanagement.exceptions.ResourceNotFoundException;
import com.example.projectmanagement.models.*;
import com.example.projectmanagement.repositories.*;
import com.example.projectmanagement.services.ProjectService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProjectServiceTest {

    @InjectMocks
    private ProjectService projectService;

    @Mock
    private ProjectRepository projectRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProject() {
        Project project = new Project();
        project.setName("Test Project");

        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project createdProject = projectService.createProject(project);

        assertNotNull(createdProject);
        assertEquals("Test Project", createdProject.getName());
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    public void testGetAllProjects() {
        Project project1 = new Project();
        project1.setName("Project 1");

        Project project2 = new Project();
        project2.setName("Project 2");

        when(projectRepository.findAll()).thenReturn(Arrays.asList(project1, project2));

        List<Project> projects = projectService.getAllProjects();

        assertEquals(2, projects.size());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    public void testGetProjectById() {
        Project project = new Project();
        project.setId(1L);
        project.setName("Test Project");

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        Project foundProject = projectService.getProjectById(1L).orElse(null);

        assertNotNull(foundProject);
        assertEquals("Test Project", foundProject.getName());
        verify(projectRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateProject() {
        Project existingProject = new Project();
        existingProject.setId(1L);
        existingProject.setName("Old Project");

        Project updatedProjectDetails = new Project();
        updatedProjectDetails.setName("Updated Project");

        when(projectRepository.findById(1L)).thenReturn(Optional.of(existingProject));
        when(projectRepository.save(any(Project.class))).thenReturn(existingProject);

        Project updatedProject = projectService.updateProject(1L, updatedProjectDetails);

        assertNotNull(updatedProject);
        assertEquals("Updated Project", updatedProject.getName());
        verify(projectRepository, times(1)).findById(1L);
        verify(projectRepository, times(1)).save(existingProject);
    }

    @Test
    public void testDeleteProject() {
        Project project = new Project();
        project.setId(1L);
        project.setName("Test Project");

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        doNothing().when(projectRepository).deleteById(1L);

        projectService.deleteProject(1L);

        verify(projectRepository, times(1)).findById(1L);
        verify(projectRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteProject_NotFound() {
        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            projectService.deleteProject(1L);
        });

        verify(projectRepository, times(1)).findById(1L);
        verify(projectRepository, times(0)).deleteById(1L);
    }
    @Test
    public void testGetProjectById_NotFound() {
        when(projectRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            projectService.getProjectById(1L).orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        });
    }
}

