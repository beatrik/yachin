package ro.kenjiru.yachin.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.kenjiru.yachin.domain.Listing;
import ro.kenjiru.yachin.persistence.ListingDao;

@Service
public class ListingServiceImpl implements ListingService {
	@Autowired(required=true)
	private ListingDao listingDao;
	@Autowired
	private UserService userService;
	
	@Override
	@Transactional(readOnly=false)
	@PreAuthorize("isAuthenticated() and hasPermission(#listing, 'isOwner') or hasRole('ROLE_ADMIN')") 
	public void updateListing(Listing listing) {
		listingDao.saveListing(listing);
	}

	@Override
	@Transactional(readOnly=false)
	@PreAuthorize("isAuthenticated()")
	public void addListing(Listing listing) {
		listing.setSubmitDate(new Date());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = ((User)auth.getPrincipal()).getUsername();
		ro.kenjiru.yachin.domain.User user = userService.getByUsername(username);
		listing.setOwner(user);
		listingDao.saveListing(listing);
	}
	
	@Override
	@Transactional(readOnly=false)
	@PreAuthorize("isAuthenticated() and hasPermission(#listing, 'isOwner') or hasRole('ROLE_ADMIN')")
	public void deleteListing(Listing listing) {
		listingDao.deleteListing(listing);
	}

	@Override
	@Transactional
	public List<Listing> getAll() {
		return listingDao.getAll();
	}

	@Override
	public Listing getById(long id) {
		return listingDao.getById(id);
	}
}
