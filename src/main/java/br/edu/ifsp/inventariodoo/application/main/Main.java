package br.edu.ifsp.inventariodoo.application.main;

import br.edu.ifsp.inventariodoo.application.repository.*;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.Inventory;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.StatusItem;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.entities.user.SecretPhrase;
import br.edu.ifsp.inventariodoo.domain.entities.user.TypeWorker;
import br.edu.ifsp.inventariodoo.domain.usecases.alterItem.*;
import br.edu.ifsp.inventariodoo.domain.usecases.category.*;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.*;
import br.edu.ifsp.inventariodoo.domain.usecases.inventory.CreateInventoryUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.inventory.FindInventoryUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.inventory.InventoryDAO;
import br.edu.ifsp.inventariodoo.domain.usecases.item.*;
import br.edu.ifsp.inventariodoo.domain.usecases.person.*;
import br.edu.ifsp.inventariodoo.domain.usecases.place.*;
import br.edu.ifsp.inventariodoo.domain.usecases.register.CreateRegisterUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.register.FindRegisterUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.register.RegisterDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {


    public static void main(String[] args) {
    }
}
