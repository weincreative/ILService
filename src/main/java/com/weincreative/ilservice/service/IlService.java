package com.weincreative.ilservice.service;

import com.weincreative.ilservice.exception.IlAlreadyExistException;
import com.weincreative.ilservice.exception.IlNotFoundException;
import com.weincreative.ilservice.model.Il;
import com.weincreative.ilservice.repository.IlRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IlService {

    private final IlRepository ilRepository;

    public List<Il> getIller(String name) {
        if(name == null){
            return ilRepository.findAll();
        }else {
            return ilRepository.findAllByName(name);
        }
    }

    public Il createIl(Il il) {
        Optional<Il> ilByName = ilRepository.findByName(il.getName());
        if(ilByName.isPresent()){
            throw new IlAlreadyExistException("Il already exist with id: "+ il.getName() );
        }
        return ilRepository.save(il);
    }

    public void deleteId(String id) {
        ilRepository.deleteById(id);
    }

    public Il getIlById(String id) {
        return ilRepository.findById(id)
                .orElseThrow(() -> new IlNotFoundException("Il not found with id: "+ id ));
    }

    public void updateIl(String id, Il newIl) {
         Il oldIl = getIlById(id);
         oldIl.setName(newIl.getName());
         ilRepository.save(oldIl);
    }
}
