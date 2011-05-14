package ar.edu.unq.dopplereffect.persistence.repository;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.edu.unq.dopplereffect.persistence.sevice.ServiceImpl;
import ar.edu.unq.dopplereffect.project.Project;
import ar.edu.unq.dopplereffect.project.Skill;
import ar.edu.unq.dopplereffect.project.SkillLevel;
import ar.edu.unq.dopplereffect.project.SkillType;

public class App {

    public static void main(final String[] args) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/aspects-context.xml");

        ServiceImpl<Project> projectService = (ServiceImpl<Project>) appContext.getBean("service");

        /** insert **/
        Project project = new Project();
        Skill skillPOO = new Skill(new SkillType("POO"), SkillLevel.EXPERT);
        Skill skillAOP = new Skill(new SkillType("AOP"), SkillLevel.MEDIUM);
        project.addSkill(skillAOP);
        project.addSkill(skillPOO);
        project.setName("Devapp");
        project.setMaxEffort(24000);

        projectService.save(project);

        /** select **/
        // Project project2 = projectService.getByName("Devapp");
        // System.out.println(project2);

        /** update **/
        // project2.setName("DopplerEffect");
        // projectService.update(project2);

        /** delete **/
        // projectService.delete(project2);

        // System.out.println("Done");
    }
}
