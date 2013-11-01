package ro.kenjiru.yachin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import ro.kenjiru.yachin.domain.Listing;
import ro.kenjiru.yachin.service.ListingService;

@Component
@Scope("session")
public class ListingBean {
	@Autowired
	private ListingService listingService;
	
	private Listing listing = new Listing();

	public ListingService getListingService() {
		return listingService;
	}

	public void setListingService(ListingService listingService) {
		this.listingService = listingService;
	}
	
	public Listing getListing() {
		return listing;
	}

	public void setListing(Listing listing) {
		this.listing = listing;
	}
	
	public String saveListing() {
		listingService.updateListing(listing);
		return "/view/viewListing?faces-redirect=true&amp;includeViewParams=true";
	}
	
	public String addListing() {
		listingService.addListing(listing);
		return "/view/viewListing?faces-redirect=true&amp;includeViewParams=true";
	}
	
	public String editListing(Listing listing) {
		this.listing = listing;
		return "/edit/editListing";
	}
	
	public String deleteListing(Listing listingToDelete) {
		listingService.deleteListing(listingToDelete);
		
		return "/view/viewAllListings";
	}
	
	public List<Listing> getAllListings() {
		return listingService.getAll();
	}
	
	public void loadListing() {
		listing = listingService.getById(listing.getId());
	}
	
	public void loadListing(Long id) {
		listing = listingService.getById(id);
	}
	 
	public void resetListing() {
		listing = new Listing();
	}
	
	public boolean showEditDelLink(Listing listing) {
		if(getAuthentication().getPrincipal() instanceof User && listing != null) {
			for(GrantedAuthority ga: ((User)getAuthentication().getPrincipal()).getAuthorities()) {
				System.out.println(((SimpleGrantedAuthority)ga).getAuthority());
			}
			String authUsername = ((User)getAuthentication().getPrincipal()).getUsername();
			return authUsername.equals(listing.getOwner().getUsername());
		}
		return false;
	}
	
	private Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
