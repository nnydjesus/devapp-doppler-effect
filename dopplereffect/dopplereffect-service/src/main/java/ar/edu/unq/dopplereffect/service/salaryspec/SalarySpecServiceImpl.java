package ar.edu.unq.dopplereffect.service.salaryspec;

import ar.edu.unq.dopplereffect.salaries.SalarySpecification;

public class SalarySpecServiceImpl implements SalarySpecService {

    @Override
    public void newSalarySpecification(final SalarySpecDTO salarySpecDTO) {
        SalarySpecification salarySpec = this.convert(salarySpecDTO);
        // guardar en el repo
    }

    private SalarySpecification convert(final SalarySpecDTO salarySpecDTO) {
        // buscar plan de carrera por nombre
        // buscar nivel de plan de carrera por nombre
        // armar el resultado
        // retornarlo
        return null;
    }
}
