package ar.edu.unq.dopplereffect.service.project;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.dopplereffect.persistence.project.SkillRepositoryImpl;
import ar.edu.unq.dopplereffect.projects.Skill;

@Service
public class SkillServiceImpl implements SkillService {

    private static final long serialVersionUID = -4976424657737545501L;

    private SkillRepositoryImpl skillRepo;

    public SkillRepositoryImpl getSkillRepo() {
        return skillRepo;
    }

    public void setSkillRepo(final SkillRepositoryImpl skillRepo) {
        this.skillRepo = skillRepo;
    }

    @Override
    @Transactional
    public List<SkillDTO> searchAllSkills() {
        return this.convertAll(this.getSkillRepo().searchAll());
    }

    public List<SkillDTO> convertAll(final Set<Skill> skills) {
        return this.convertAll(new LinkedList<Skill>(skills));
    }

    public List<SkillDTO> convertAll(final List<Skill> skills) {
        List<SkillDTO> results = new LinkedList<SkillDTO>();
        for (Skill sk : skills) {
            results.add(this.convert(sk));
        }
        return results;
    }

    public SkillDTO convert(final Skill sk) {
        SkillDTO result = new SkillDTO();
        result.setLevel(sk.getLevel());
        result.setName(sk.getType());
        return result;
    }
}
