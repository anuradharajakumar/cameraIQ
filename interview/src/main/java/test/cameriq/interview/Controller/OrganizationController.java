package test.cameriq.interview.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import test.cameriq.interview.Models.Organization;
import test.cameriq.interview.Models.User;
import test.cameriq.interview.Response.ResponseWrapper;
import test.cameriq.interview.Service.OrganizationService;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import java.util.Set;

import static test.cameriq.interview.Constants.Constant.REGEX_FOR_IDS;
import static test.cameriq.interview.Constants.Constant.REGEX_ID_MISMATCH;



    /*

     1. POST    api/v1/organization/                - create a single organization
     3. PATCH   api/v1/organization/{ID}/user/{ID}  - add user to organization
     4. DELETE  api/v1/organization/{ID}/user/{ID}  - remove a user from organization
     5. GET     api/v1/organization/{ID}/users      - get all users from organization

    */

@Validated
@RestController
@RequestMapping("api/v1/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping
    public ResponseWrapper<Page<Organization>> getAllOrganizations(Pageable pageable) {
        return new ResponseWrapper<>(organizationService.getallOrganizations(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseWrapper<Organization> createOrganization(@Valid @RequestBody Organization organization) {
        return new ResponseWrapper<>(organizationService.add(organization), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseWrapper<Organization> deleteOrganization(@Valid @Pattern(regexp = REGEX_FOR_IDS, message = REGEX_ID_MISMATCH) @PathVariable(value = "id") String id) {
        return new ResponseWrapper<>(organizationService.deleteById(Integer.parseInt(id)), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseWrapper<Organization> getOrganizationById(@Valid @Pattern(regexp = REGEX_FOR_IDS, message = REGEX_ID_MISMATCH) @PathVariable(value = "id") String id) {
        return new ResponseWrapper<>(organizationService.getById(Integer.parseInt(id)), HttpStatus.OK);
    }

    @PatchMapping(value = "/{orgId}/user/{userId}")
    public ResponseWrapper<Organization> addUserToOrganization(
            @Valid @Pattern(regexp = REGEX_FOR_IDS, message = REGEX_ID_MISMATCH) @PathVariable(value = "orgId") String orgId,
            @Valid @Pattern(regexp = REGEX_FOR_IDS, message = REGEX_ID_MISMATCH) @PathVariable(value = "userId") String userId) {
        return new ResponseWrapper<>(organizationService.addUserToOrganization(
                Integer.parseInt(orgId), Integer.parseInt(userId)
        ), HttpStatus.OK);
    }

    @GetMapping(value ="/{id}/users")
    public ResponseWrapper<Set<User>> getAllUsersFromOrganization(
            @Valid @Pattern(regexp = REGEX_FOR_IDS, message = REGEX_ID_MISMATCH) @PathVariable(value = "id") String id) {
        return new ResponseWrapper<>(organizationService.getUsersById(Integer.parseInt(id)),HttpStatus.OK);
    }

    @DeleteMapping(value="/{orgId}/user/{userId}")
    public ResponseWrapper<Organization> deleteUserFromOrganization(
            @Valid @Pattern(regexp = REGEX_FOR_IDS, message = REGEX_ID_MISMATCH) @PathVariable(value = "orgId") String orgId,
            @Valid @Pattern(regexp = REGEX_FOR_IDS, message = REGEX_ID_MISMATCH) @PathVariable(value = "userId") String userId) {
        return new ResponseWrapper<>(organizationService.deleteUserFromOrganization(
                Integer.parseInt(orgId), Integer.parseInt(userId)
        ), HttpStatus.OK);
    }

}
