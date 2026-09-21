package br.com.josecarlosn.auth.service;

import br.com.josecarlosn.auth.entity.User;
import br.com.josecarlosn.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository repository;
    public UserService(UserRepository repository){this.repository = repository;}

    public List<User> list(){
        return repository.findAll();
    }
    public void delete(UUID id){
        repository.deleteById(id);
    }

}
