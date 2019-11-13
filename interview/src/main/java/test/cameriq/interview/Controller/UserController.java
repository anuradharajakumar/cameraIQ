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
import test.cameriq.interview.Service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.Set;

import static test.cameriq.interview.Constants.Constant.REGEX_FOR_IDS;
import static test.cameriq.interview.Constants.Constant.REGEX_ID_MISMATCH;

    /*

     2. POST     api/v1/user/                    - create a single user
     6. GET      api/v1/user/{ID}/organizations  - get all organizations of a user

     */

@Validated
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseWrapper<Page<User>> getAllUsers(Pageable pageable) {
        return new ResponseWrapper<>(userService.getallOrganizations(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseWrapper<User> createUser(@Valid @RequestBody User user) {
        return new ResponseWrapper<>(userService.add(user), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseWrapper<User> deleteUser(@Valid @Pattern(regexp = REGEX_FOR_IDS, message = REGEX_ID_MISMATCH) @PathVariable(value = "id") String id) {
        return new ResponseWrapper<>(userService.deleteById(Integer.parseInt(id)), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseWrapper<User> getUserById(@Valid @Pattern(regexp = REGEX_FOR_IDS, message = REGEX_ID_MISMATCH) @PathVariable(value = "id") String id) {
        return new ResponseWrapper<>(userService.getById(Integer.parseInt(id)), HttpStatus.OK);
    }


    @GetMapping(value = "/{id}/organizations")
    public ResponseWrapper<Set<Organization>> getUserOrganizationsById(
            @Valid @Pattern(regexp = REGEX_FOR_IDS, message = REGEX_ID_MISMATCH) @PathVariable(value = "id") String id) {
        return new ResponseWrapper<>(userService.getOrganizationsById(Integer.parseInt(id)), HttpStatus.OK);
    }

    @PostMapping(value = "/{userId}/organization/{orgId}")
    public ResponseWrapper<User> addOrganizationToUser(
            @Valid @Pattern(regexp = REGEX_FOR_IDS, message = REGEX_ID_MISMATCH) @PathVariable(value = "userId") String userId,
            @Valid @Pattern(regexp = REGEX_FOR_IDS, message = REGEX_ID_MISMATCH) @PathVariable(value = "orgId") String orgId) {
        return new ResponseWrapper<>(userService.addOrganizationToUser(Integer.parseInt(userId), Integer.parseInt(orgId)), HttpStatus.OK);
    }


}
