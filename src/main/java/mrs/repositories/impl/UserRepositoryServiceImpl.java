package mrs.repositories.impl;

import mrs.models.entity.UserEntity;
import mrs.repositories.UserDetailsRepository;
import mrs.repositories.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRepositoryServiceImpl implements UserRepositoryService {

    private final UserDetailsRepository userDetailsRepository;

    @Autowired
    public UserRepositoryServiceImpl(
            UserDetailsRepository userDetailsRepository) {this.userDetailsRepository = userDetailsRepository;}

    public UserEntity addUser(final UserEntity userEntity){
        return userDetailsRepository.save(userEntity);
    }

    public UserEntity upgradeUser(final UserEntity userEntity){
        Optional<UserEntity> userEntityOptional = userDetailsRepository.findByName(userEntity.getName());
        if(userEntityOptional.isPresent()) {
            return userDetailsRepository.save(userEntity);
        }
        else {
            throw new RuntimeException("user not found");
        }
    }

    public UserEntity getUser(final String name){
        Optional<UserEntity> userEntityOptional = userDetailsRepository.findByName(name);
        if(userEntityOptional.isPresent())
            return userEntityOptional.get();
        else {
            throw new RuntimeException("user not found");
        }

    }
}
