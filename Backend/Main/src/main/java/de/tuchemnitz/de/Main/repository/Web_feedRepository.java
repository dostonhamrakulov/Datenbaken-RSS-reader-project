package de.tuchemnitz.de.Main.repository;

//import de.tuchemnitz.de.Main.entity.Feed;
import de.tuchemnitz.de.Main.entity.Web_feed;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Web_feedRepository extends CrudRepository<Web_feed, Integer> {

    public List<Web_feed> findByLink(String link);
}
