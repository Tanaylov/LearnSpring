package HW5_6_7.security;

import HW5_6_7.model.Reader;
import HW5_6_7.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Component
public class CustomUserDetailService implements UserDetailsService {

    private final ReaderService readerService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Reader readerFromDB = readerService.getReaderByName(username).stream()
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException(username));
        System.out.println(readerFromDB);
        return new User(readerFromDB.getName(),
                readerFromDB.getPass(),
                List.of(new SimpleGrantedAuthority(readerFromDB.getAuthRole())));

    }
}
