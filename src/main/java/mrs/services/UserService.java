package mrs.services;

import mrs.models.entity.UserEntity;

public interface UserService {

    UserEntity addUser(String UserName);

    UserEntity upgradeUser(UserEntity userEntity);

    UserEntity getUserByName(String userName);
}
