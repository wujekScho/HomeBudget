package pl.piotrschodzinski.homebudget.mapper;

import pl.piotrschodzinski.homebudget.dto.UserDto;
import pl.piotrschodzinski.homebudget.entity.User;
import pl.piotrschodzinski.homebudget.repository.UserRoleRepository;

import java.util.stream.Collectors;

public class UserMapper implements DtoMapper<User, UserDto> {

    private UserRoleRepository userRoleRepository;

    public UserMapper(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public User mapToEntity(UserDto dtoObject) {
        User user = new User();
        user.setId(dtoObject.getId());
        user.setUsername(dtoObject.getUsername());
        user.setEmail(dtoObject.getEmail());
        user.setPassword(dtoObject.getPassword());
        user.setRoles(dtoObject.getRoles().stream()
                .map(e -> userRoleRepository.findByRole(e))
                .collect(Collectors.toSet()));
        return user;
    }

    @Override
    public UserDto mapToDto(User entityObject) {
        UserDto userDto = new UserDto();
        userDto.setId(entityObject.getId());
        userDto.setUsername(entityObject.getUsername());
        userDto.setEmail(entityObject.getEmail());
        userDto.setPassword(entityObject.getPassword());
        userDto.setRoles(entityObject.getRoles().stream()
                .map(d -> d.getRole())
                .collect(Collectors.toList()));
        return userDto;
    }
}
