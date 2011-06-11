package ar.edu.unq.dopplereffect.service.project;

import java.util.List;

import ar.edu.unq.dopplereffect.service.Service;

public interface SkillService extends Service {

    List<SkillDTO> searchAllSkills();

}
