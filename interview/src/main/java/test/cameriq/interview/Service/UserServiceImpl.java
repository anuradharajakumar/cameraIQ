package test.cameriq.interview.Service;

import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import test.cameriq.interview.Exception.ElementNotFoundException;
import test.cameriq.interview.Models.Organization;
import test.cameriq.interview.Models.User;
import test.cameriq.interview.Repository.OrganizationRepository;
import test.cameriq.interview.Repository.UserRepository;

import java.util.Set;

@Service
public class UserServiceImpl extends UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public Page<User> getallOrganizations(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(int id) {


        if (!userRepository.findById(id).isPresent()) {
            throw new ElementNotFoundException("user id : " + id);
        }
        return userRepository.findById(id).get();
    }

    @Override
    public User update(User user, int id) {

        if (user == null) {
            throw new ElementNotFoundException("User data empty for id : " + id);
        }


        if (!userRepository.findById(id).isPresent()) {
            throw new ElementNotFoundException("user id : " + id);
        }

        User userData = userRepository.findById(id).get();

        userData.setFirstName(user.getFirstName());
        userData.setLastName(user.getLastName());
        userData.setEmail(user.getEmail());
        userData.setAddress(user.getAddress());
        userData.setPhone(user.getPhone());
        if(user.getOrganizations() != null) {
            userData.setOrganizations(user.getOrganizations());
        }

        return userRepository.save(userData);
    }

    @Override
    public User deleteById(int id) {

        if (!userRepository.findById(id).isPresent()) {
            throw new ElementNotFoundException("user id : " + id);
        }
        User user = userRepository.findById(id).get();

        userRepository.deleteById(id);
        return user;
    }

    @Override
    public Set<Organization> getOrganizationsById(int id) {

        if (!userRepository.findById(id).isPresent()) {
            throw new ElementNotFoundException("user id : " + id);
        }
        User user = userRepository.findById(id).get();

        return user.getOrganizations();
    }

    @Override
    public User addOrganizationToUser(int userId, int orgId) {
        if(!userRepository.findById(userId).isPresent()) {
            throw new ElementNotFoundException("User not found");
        }

        if(!organizationRepository.findById(orgId).isPresent()) {
            throw new ElementNotFoundException("Organization not found");
        }

        User user = userRepository.findById(userId).get();
        Organization org = organizationRepository.findById(orgId).get();
        user.addOrganization(org);
        org.addUser(user);

        return userRepository.save(user);
    }


}
