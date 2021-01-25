package mrs.repositories.impl;

import lombok.extern.log4j.Log4j2;
import mrs.models.entity.UserEntity;
import mrs.repositories.UserDetailsRepository;
import mrs.repositories.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class UserRepositoryServiceImpl implements UserRepositoryService {

    private final UserDetailsRepository userDetailsRepository;

    @Autowired
    public UserRepositoryServiceImpl(
            UserDetailsRepository userDetailsRepository) {this.userDetailsRepository = userDetailsRepository;}

    public UserEntity addUser(final UserEntity userEntity){
        return userDetailsRepository.save(userEntity);
    }

    public UserEntity upgradeUser(final UserEntity userEntity){
        Optional<UserEntity> userEntityOptional = userDetailsRepository.findFirstByName(userEntity.getName());
        if(userEntityOptional.isPresent()) {
            return userDetailsRepository.save(userEntity);
        }
        else {
            log.error("user not found");
            return new UserEntity();
        }
    }

    public UserEntity getUser(final String name){
        Optional<UserEntity> userEntityOptional = userDetailsRepository.findFirstByName(name);
        if(userEntityOptional.isPresent())
            return userEntityOptional.get();
        else {
            log.error("user not found");
            return new UserEntity();
        }

    }
}
