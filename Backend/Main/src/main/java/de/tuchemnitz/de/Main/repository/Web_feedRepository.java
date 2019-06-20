package de.tuchemnitz.de.Main.repository;

//import de.tuchemnitz.de.Main.entity.Feed;
import de.tuchemnitz.de.Main.entity.Web_feed;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface Web_feedRepository extends CrudRepository<Web_feed, Integer> {

    public List<Web_feed> findByLink(String link, int providerid);
    public List<Web_feed> findByProviderid(int providerid);

    @Transactional
    @Modifying
    public int rvAllforProviderId(int providerid);

    @Transactional
    @Modifying
    public int updateFeed(String title, String publisheddate, String link);

    public int numOfFeedsOfAProvider(int providerid);

    @Transactional
    @Modifying
    public int updateDeleted(String deleted, int id);

    @Transactional
    @Modifying
    public int updateFeedByUser(String title, String link, int id);
}
