package business;

import exception.RegistryBusinessException;
import model.Registry;

import java.util.List;

public interface IBusinessController {

    List<Registry> getRegistry(Long id) throws RegistryBusinessException;

    Registry insertNewRegistry(Registry registry);
}
