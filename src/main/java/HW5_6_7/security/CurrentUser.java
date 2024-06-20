package HW5_6_7.security;

import HW5_6_7.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class CurrentUser implements AuthorizationManager<RequestAuthorizationContext> {

    private final ReaderService readerService;

    public boolean hasReaderId(Authentication authentication, long userId) {
        return readerService.getReaderByName(authentication.getName()).get(0).getId() == userId;
    }

    @Override
    public AuthorizationDecision check(Supplier authentication, RequestAuthorizationContext context) {
        long userId = Long.parseLong(context.getVariables().get("userId"));
        return new AuthorizationDecision(hasReaderId((Authentication) authentication.get(), userId));
    }

}
