package de.tuchemnitz.de.Main.repository;

import de.tuchemnitz.de.Main.entity.Web_feed_providers;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

//@Repository
public interface Web_feed_providersRepository extends CrudRepository<Web_feed_providers, Integer> {

    public List<Web_feed_providers> findByUserid(int userid);

    @Transactional
    @Modifying
    public int deleteProvider(int id, int userid);

    @Transactional
    @Modifying
    public int updateProvider(int numfeeds, String updateddate, String lastattempt, String latestrecorddate, int id);

    @Transactional
    @Modifying
    public int updateProviderOnly(String name, String link, int id);

    @Transactional
    @Modifying
    public int updateNumfeeds(int numfeeds, int id);
}
