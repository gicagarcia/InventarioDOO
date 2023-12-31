package br.edu.ifsp.inventariodoo.application.repository.inmemory;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.usecases.register.RegisterDAO;

import java.util.*;

public class InMemoryRegisterDAO implements RegisterDAO {

    private static final Map<Integer, Register> db = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Integer create(Register register) {
        idCounter++;
        register.setId(idCounter);
        db.put(idCounter, register);
        return idCounter;
    }

    @Override
    public Optional<Register> findOne(Integer id) {
        return db.values().stream()
                .filter(register -> register.getId().equals(id))
                .findAny();
    }

    @Override
    public List<Register> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Register type) {
        return false;
    }
}
