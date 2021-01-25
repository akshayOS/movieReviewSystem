package mrs.services.impl;

import mrs.constants.UserConstants;
import mrs.models.entity.UserEntity;
import mrs.repositories.UserRepositoryService;
import mrs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepositoryService userRepositoryService;

    @Autowired
    public UserServiceImpl(UserRepositoryService userRepositoryService) {
        this.userRepositoryService = userRepositoryService;
    }

    @Override
    public UserEntity addUser(final String userName) {

        return userRepositoryService.addUser(buildUserEntity(userName));
    }

    @Override
    public UserEntity getUserByName(final String name) {
        return userRepositoryService.getUser(name);
    }

    @Override
    public UserEntity upgradeUser(final UserEntity userEntity) {
        return userRepositoryService.upgradeUser(userEntity);
    }

    private UserEntity buildUserEntity(String userName) {
        return UserEntity.builder()
                         .name(userName)
                         .capability(UserConstants.USER_CAPABILITY_VIEWER)
                         .reviewCount(0)
                         .build();

    }
}
