package ro.kenjiru.yachin.service;

import java.util.List;

import ro.kenjiru.yachin.domain.Listing;

public interface ListingService {
	public void addListing(Listing listing);
	public void deleteListing(Listing listing);
	public List<Listing> getAll();
}
