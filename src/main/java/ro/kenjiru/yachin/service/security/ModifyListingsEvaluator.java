package ro.kenjiru.yachin.service.security;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import ro.kenjiru.yachin.domain.Listing;
import ro.kenjiru.yachin.service.UserService;

public class ModifyListingsEvaluator implements PermissionEvaluator {
	@Autowired
	private UserService userService;

	@Override
	public boolean hasPermission(Authentication authentication,
			Object targetDomainObject, Object permission) {
		ro.kenjiru.yachin.domain.User user = userService.getByUsername(((User)authentication.getPrincipal()).getUsername());
		if(isListing(targetDomainObject) && ((Listing)targetDomainObject).getOwner().getId().equals(user.getId())) {
			return true;
		}
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication,
			Serializable targetId, String targetType, Object permission) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private boolean isListing(Object targetDomainObject) {
		return targetDomainObject instanceof Listing;
	}

}