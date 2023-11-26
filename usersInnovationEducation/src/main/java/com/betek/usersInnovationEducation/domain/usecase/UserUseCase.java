package com.betek.usersInnovationEducation.domain.usecase;

import com.betek.usersInnovationEducation.adapters.driven.jpa.mysql.entity.UserEntity;
import com.betek.usersInnovationEducation.adapters.driving.http.dto.request.UserUpdateRequestDto;
import com.betek.usersInnovationEducation.adapters.driving.http.dto.response.ChooseUserResponseDto;
import com.betek.usersInnovationEducation.domain.api.IAuthenticationUserInfoServicePort;
import com.betek.usersInnovationEducation.domain.api.IUserServicePort;
import com.betek.usersInnovationEducation.domain.model.Profile;
import com.betek.usersInnovationEducation.domain.model.User;
import com.betek.usersInnovationEducation.domain.spi.IUserPersistencePort;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IAuthenticationUserInfoServicePort authenticationUserInfoServicePort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IAuthenticationUserInfoServicePort authenticationUserInfoServicePort) {
        this.userPersistencePort = userPersistencePort;
        this.authenticationUserInfoServicePort = authenticationUserInfoServicePort;
    }

    @Override
    public void createUser(User user) {
        user.setState(true);
        user.setIs_admin(false);
        userPersistencePort.createUser(user);
    }

    @Override
    public void createProfile(Profile profile) {
        Long idUser = authenticationUserInfoServicePort.getIdentifierUserFromToken();
        profile.setId(idUser);
        userPersistencePort.createProfile(profile);
    }

    @Override
    public void updateAllInformation(UserUpdateRequestDto userUpdateRequestDto) {
        Long idUser = authenticationUserInfoServicePort.getIdentifierUserFromToken();

        User useToChange = userPersistencePort.findByIdUser(idUser);
        Profile profileToChange = userPersistencePort.findByIdProfile(idUser);

        Long idFound = useToChange.getId();


        if(useToChange.getId() == idUser){

            useToChange.setMember_name(userUpdateRequestDto.getUserRequestDto().getMember_name());
            useToChange.setEmail(userUpdateRequestDto.getUserRequestDto().getEmail());
            useToChange.setPassword(userUpdateRequestDto.getUserRequestDto().getPassword());
            useToChange.setPhone(userUpdateRequestDto.getUserRequestDto().getPhone());
            useToChange.setIdCountry(userUpdateRequestDto.getUserRequestDto().getIdCountry());

            profileToChange.setDescription(userUpdateRequestDto.getProfileRequestDto().getDescription());
            profileToChange.setProfile_image(userUpdateRequestDto.getProfileRequestDto().getDescription());
            profileToChange.setLink_website(userUpdateRequestDto.getProfileRequestDto().getLink_website());

            userPersistencePort.updateAllInformation(useToChange, profileToChange, idUser);
        }

    }


    @Override
    public ChooseUserResponseDto getAnUserInformation() {
        Long idUser = authenticationUserInfoServicePort.getIdentifierUserFromToken();
        User user = userPersistencePort.getAnUserInformation(idUser); //TODO: IS CORRECT TO USE USER ENTITY ??
        ChooseUserResponseDto chooseUserResponseDto = new ChooseUserResponseDto(user.getId(), user.getIs_admin(), user.getState());

        return chooseUserResponseDto;
    }

    @Override
    public void enabledAndDisableAccount() {
        Long idUser = authenticationUserInfoServicePort.getIdentifierUserFromToken();
        User user = userPersistencePort.findByIdUser(idUser);
        if(user.getState() == true){
            user.setState(false);
            userPersistencePort.saveUser(user);
        } else if (user.getState() == false) {
            user.setState(true);
            userPersistencePort.saveUser(user);
        }

    }

}
