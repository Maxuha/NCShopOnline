package ua.edu.sumdu.j2ee.zykov.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.edu.sumdu.j2ee.zykov.dao.UserDAO;
import ua.edu.sumdu.j2ee.zykov.dao.UserHasRoleDAO;
import ua.edu.sumdu.j2ee.zykov.model.NCUserDetail;
import ua.edu.sumdu.j2ee.zykov.model.Role;
import ua.edu.sumdu.j2ee.zykov.model.User;

import java.util.HashSet;
import java.util.Set;

public class NCUserDetailsService implements UserDetailsService {
    private final UserDAO userDAO;
    private final UserHasRoleDAO userHasRoleDAO;

    public NCUserDetailsService(UserDAO userDAO, UserHasRoleDAO userHasRoleDAO) {
        this.userDAO = userDAO;
        this.userHasRoleDAO = userHasRoleDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        System.out.println("work");
        User user = userDAO.findByUserName(name);

        Set<Role> roles = new HashSet<>(userHasRoleDAO.getRoleByUserId(user.getId()));

        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.name()));
        }

        NCUserDetail ncUserDetail = new NCUserDetail();
        ncUserDetail.setUser(user);
        ncUserDetail.setAuthorities(authorities);
        return ncUserDetail;
    }
}
