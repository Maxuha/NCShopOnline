package ua.edu.sumdu.j2ee.zykov.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.zykov.dao.UserHasRoleDAO;
import ua.edu.sumdu.j2ee.zykov.dao.UserDAO;
import ua.edu.sumdu.j2ee.zykov.model.Role;
import ua.edu.sumdu.j2ee.zykov.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDAO userDAO;
    private final UserHasRoleDAO userHasRoleDAO;

    public UserDetailsServiceImpl(UserDAO userDAO, UserHasRoleDAO userHasRoleDAO) {
        this.userDAO = userDAO;
        this.userHasRoleDAO = userHasRoleDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = this.userDAO.findByUserName(userName);

        if (user == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        System.out.println("Found User: " + userName);

        List<Role> roles = this.userHasRoleDAO.getRoleByUserId(user.getId());

        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roles != null) {
            for (Role role : roles) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
                grantList.add(authority);
            }
        }

        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(), grantList);
    }

}
