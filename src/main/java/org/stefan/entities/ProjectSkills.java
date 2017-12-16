package org.stefan.entities;

import javax.persistence.*;

@Entity
@Table(name = "projects_skills")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.project", joinColumns = @JoinColumn(name = "project_id")),
        @AssociationOverride(name = "primaryKey.skill", joinColumns = @JoinColumn(name = "skill_id"))
})
public class ProjectSkills {


    @Id
    private ProjectSkillId primaryKey;

    @Column(name = "level_of_preferences")
    private int levelOfPreference;


    public ProjectSkillId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ProjectSkillId projectSkillId) {
        this.primaryKey = projectSkillId;
    }

    public int getLevelOfPreference() {
        return levelOfPreference;
    }

    public void setLevelOfPreference(int levelOfPreference) {
        this.levelOfPreference = levelOfPreference;
    }

    public void setSkill(Skill skill) {
        primaryKey.setSkill(skill);
    }

    @Transient
    public Skill getSkill() {
        return primaryKey.getSkill();
    }

    public void setProject(Project project) {
        primaryKey.setProject(project);
    }

    @Transient
    public Project getProject() {
        return primaryKey.getProject();
    }


}
