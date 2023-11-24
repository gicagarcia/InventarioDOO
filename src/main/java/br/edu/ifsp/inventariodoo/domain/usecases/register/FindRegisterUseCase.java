package br.edu.ifsp.inventariodoo.domain.usecases.register;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;

import java.util.List;
import java.util.Optional;

public class FindRegisterUseCase {
    private RegisterDAO registerDAO;

    public FindRegisterUseCase(RegisterDAO registerDAO) {
        this.registerDAO = registerDAO;
    }

    public Optional<Register> findOne(Integer id){
        if (id == null)
            throw new IllegalArgumentException("ID can not be null");
        return registerDAO.findOne(id);
    }

    public List<Register> findAll(){
        return registerDAO.findAll();
    }
}
