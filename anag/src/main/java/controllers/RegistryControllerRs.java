package controllers;

import business.IBusinessController;
import exception.RegistryBusinessException;
import model.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class RegistryControllerRs {

    private static final Logger logger = LoggerFactory.getLogger(RegistryControllerRs.class);

    @Autowired
    private IBusinessController businessController;


    @RequestMapping(value = "/registry", method = GET)
    public ResponseEntity<List<Registry>> read() throws RegistryBusinessException {
        return ResponseEntity.ok(businessController.getRegistry(null));

    }

    @RequestMapping(value = "/registry/{id}", method = GET)
    public ResponseEntity<List<Registry>> readId(@PathVariable(value = "id", required = true) Long id) throws RegistryBusinessException {
        return ResponseEntity.ok(businessController.getRegistry(id));

    }

    @RequestMapping(value = "/registry", method = POST)
    public ResponseEntity<Registry> create(@RequestBody Registry registry) {

        return ResponseEntity.ok(businessController.insertNewRegistry(registry));
    }

    @RequestMapping(value = "/registry/{id}", method = PUT)
    public ResponseEntity<Registry> update(@PathVariable(value = "id", required = true) Long id, @RequestBody Registry registry) {

        return ResponseEntity.ok(businessController.insertNewRegistry(registry));
    }

}
