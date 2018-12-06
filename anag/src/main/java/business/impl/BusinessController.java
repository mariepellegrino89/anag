package business.impl;

import business.IBusinessController;
import exception.RegistryBusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import repository.RegistryRepository;
import model.Registry;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@Service
public class BusinessController implements IBusinessController {

    private static final Logger logger = LoggerFactory.getLogger(BusinessController.class);

    @Autowired
    private RegistryRepository registryRepository;

    @Override
    public List<Registry> getRegistry(Long id) throws RegistryBusinessException {
        List<Registry> responseList = null;
        if(id != null){
            responseList = registryRepository.findAllById(Collections.singletonList(id));
        } else {
            responseList = registryRepository.findAll();
        }
        if(responseList == null || responseList.isEmpty()){
            throw new RegistryBusinessException(String.valueOf(id), "BUSINESS_CONTROLLER", "No registry record was found", logger);
        }
        return responseList;
    }

    @Override
    public Registry insertNewRegistry(Registry registry) {
        return registryRepository.save(registry);
    }
}
