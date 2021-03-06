package gov.loc.repository;

import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Assert;
import org.junit.Test;

import gov.loc.repository.task.UploadTask;

public class ArtifactoryPluginTest extends Assert {

  @Test
  public void testApplyByClass(){
    Project project = ProjectBuilder.builder().build();
    project.getPluginManager().apply(SimpleArtifactUploaderPlugin.class);
    
    assertTrue(project.getTasks().getByName("uploadToArtifactory") instanceof UploadTask);
  }
  
  @Test
  public void testApplyByName(){
    Project project = ProjectBuilder.builder().build();
    project.getPlugins().apply("gov.loc.repository.simple-artifact-uploader");

    assertTrue(project.getTasks().getByName("uploadToArtifactory") instanceof UploadTask);
  }
  
  @Test
  public void testAutoDependsOn(){
    Project project = ProjectBuilder.builder().build();
    project.getPluginManager().apply("java");
    project.getPluginManager().apply(SimpleArtifactUploaderPlugin.class);
    
    assertTrue("Should have at least 2 depends on, the first a file collection, and the second the jar task.", 
        project.getTasks().getByName("uploadToArtifactory").getDependsOn().size() >= 2);
  }
}
