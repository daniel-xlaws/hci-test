package com.test.hci.service;

import com.test.hci.model.Module;
import com.test.hci.repository.ModuleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ModuleService {
    @Autowired
    private ModuleRepo moduleRepo;

    @Transactional
    public void insertModules(Module ... modules) {
        moduleRepo.saveAll(Arrays.asList(modules));
    }

    public Module selectOneModuleByName(String name) {
        try {
            return moduleRepo.findById(name).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
