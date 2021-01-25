package mrs.repositories;

import mrs.models.entity.UserEntity;

public interface UserRepositoryService {

     UserEntity addUser(UserEntity userEntity);

     UserEntity upgradeUser(UserEntity userEntity);

     UserEntity getUser(String userName);
}
