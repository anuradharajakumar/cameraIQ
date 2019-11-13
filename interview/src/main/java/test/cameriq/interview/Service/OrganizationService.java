package test.cameriq.interview.Service;

import test.cameriq.interview.Models.Organization;
import test.cameriq.interview.Models.User;

import java.util.Set;

public abstract class OrganizationService implements MainService<Organization> {

    public abstract Set<User> getUsersById(int id);
    public abstract Organization addUserToOrganization(int orgId, int userId);
    public abstract Organization deleteUserFromOrganization(int orgId, int userId);
}
