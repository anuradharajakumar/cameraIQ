package test.cameriq.interview.Service;

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
public class OrganizationServiceImpl extends OrganizationService {


    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Page<Organization> getallOrganizations(Pageable pageable) {
        return organizationRepository.findAll(pageable);
    }

    @Override
    public Organization add(Organization org) {
        return organizationRepository.save(org);
    }

    @Override
    public Organization getById(int id) {

        if (!organizationRepository.findById(id).isPresent()) {
            throw new ElementNotFoundException("Organization id : " + id);
        }
        return organizationRepository.findById(id).get();
    }

    @Override
    public Organization update(Organization org, int id) {

        if (org == null) {
            throw new ElementNotFoundException("Organization data empty for id : " + id);
        }


        if (!organizationRepository.findById(id).isPresent()) {
            throw new ElementNotFoundException("Organization id : " + id);
        }

        Organization orgData = organizationRepository.findById(id).get();

        orgData.setName(org.getName());
        orgData.setAddress(org.getAddress());
        orgData.setPhone(org.getPhone());
        if (org.getUsers() != null) {
            orgData.setUsers(org.getUsers());
        }

        return organizationRepository.save(orgData);
    }

    @Override
    public Organization deleteById(int id) {

        if (!organizationRepository.findById(id).isPresent()) {
            throw new ElementNotFoundException("organization id : " + id);
        }
        Organization organization = organizationRepository.findById(id).get();

        organizationRepository.deleteById(id);
        return organization;
    }

    @Override
    public Set<User> getUsersById(int id) {

        if (!organizationRepository.findById(id).isPresent()) {
            throw new ElementNotFoundException("Organization id : " + id);
        }
        Organization organization = organizationRepository.findById(id).get();

        return organization.getUsers();
    }

    @Override
    public Organization addUserToOrganization(int orgId, int userId) {
        if (!organizationRepository.findById(orgId).isPresent()) {
            throw new ElementNotFoundException("Organization not found");
        }

        if (!userRepository.findById(userId).isPresent()) {
            throw new ElementNotFoundException("User not found");
        }

        Organization org = organizationRepository.findById(orgId).get();
        User user = userRepository.findById(userId).get();
        org.addUser(user);
        user.addOrganization(org);
        userRepository.save(user);
//        return organizationRepository.save(org);
        return org;
    }

    @Override
    public Organization deleteUserFromOrganization(int orgId, int userId) {
        if (!organizationRepository.findById(orgId).isPresent()) {
            throw new ElementNotFoundException("Organization not found");
        }

        if (!userRepository.findById(userId).isPresent()) {
            throw new ElementNotFoundException("User not found");
        }

        Organization org = organizationRepository.findById(orgId).get();
        User user = userRepository.findById(userId).get();

        org.getUsers().remove(user);
        user.getOrganizations().remove(org);

        return organizationRepository.save(org);
    }


}
