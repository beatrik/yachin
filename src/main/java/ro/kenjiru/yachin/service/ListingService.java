package ro.kenjiru.yachin.service;

import java.util.List;

import ro.kenjiru.yachin.domain.Listing;
import ro.kenjiru.yachin.domain.User;

public interface ListingService {
	public void updateListing(Listing listing);
	public void addListing(Listing listing);
	public void deleteListing(Listing listing);
	public List<Listing> getAll();
	
	public Listing getById(long id);
}
