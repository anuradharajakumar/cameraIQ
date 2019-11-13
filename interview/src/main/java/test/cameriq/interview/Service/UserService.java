package test.cameriq.interview.Service;

import test.cameriq.interview.Models.Organization;
import test.cameriq.interview.Models.User;

import java.util.*;

public abstract class UserService implements MainService<User> {

    public abstract Set<Organization> getOrganizationsById(int id);

    public abstract User addOrganizationToUser(int userId, int orgId);
}
