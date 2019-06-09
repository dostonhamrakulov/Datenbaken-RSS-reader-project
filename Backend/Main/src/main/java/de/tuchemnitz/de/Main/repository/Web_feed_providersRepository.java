package de.tuchemnitz.de.Main.repository;

import de.tuchemnitz.de.Main.entity.Web_feed_providers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface Web_feed_providersRepository extends CrudRepository<Web_feed_providers, Integer> {

    public List<Web_feed_providers> findByUserid(int userid);
}
