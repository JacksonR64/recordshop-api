package com.northcoders.jr.recordshop_api.service;

import com.northcoders.jr.recordshop_api.model.Album;
import com.northcoders.jr.recordshop_api.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AlbumCacheService {

    AlbumRepository albumRepository;
    AtomicInteger logCounter = new AtomicInteger(0);

    @Autowired
    public AlbumCacheService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        try {
            scheduler.scheduleAtFixedRate(this::cleanUpCache, 20, 20, TimeUnit.SECONDS);
            Thread.sleep(60000);
        }
        catch (InterruptedException e) {
                e.printStackTrace();
        }
        finally {
                // Shutdown the scheduler
                scheduler.shutdown();
        }


    }
    private void cleanUpCache() {
        var then = Instant.now().minus(60, TimeUnit.SECONDS.toChronoUnit());
        // Define a DateTimeFormatter for HH:mm:ss
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Format the Instant to HH:mm:ss in a specific time zone
        String formattedTime = then.atZone(ZoneId.systemDefault()).format(formatter);

        System.out.println("log: " + logCounter.incrementAndGet() + " - About to clean cache from before " + formattedTime + " - size: " + idAlbumCache.size());

        new AlbumCacheService(albumRepository);

        idAlbumCache.entrySet().stream()
                .filter(entry -> entry.getValue().modified.isBefore(then))
                .forEach(entry -> {
                    System.out.println("log: " + logCounter.incrementAndGet() + " - removing a cache entry: \nmodified:  " + entry.getValue().modified + "\nid: " + entry.getKey());
                    idAlbumCache.remove(entry.getKey());
                });
        logCounter.incrementAndGet();
            System.out.println("log: " + logCounter + " - Cleaned cache! - " + idAlbumCache.size());
    }


    private final Map<Long, CacheEntry> idAlbumCache = new HashMap<>();


    public Optional<Album> getByIdWithCache(long id) {
        // Get from the map, or compute a new value
        if (idAlbumCache.containsKey(id)) {
            System.out.printf("log: " + logCounter++ + " - Got id %d from the cache!%n", id);
            System.out.println("Cache Size: " + idAlbumCache.size());
            return Optional.of(idAlbumCache.get(id).album);
        }

        // Lookup the value in the DB
        System.out.printf("log: " + logCounter++ + " - Looking up id %d in the DB!%n", id);
        Optional<Album> entity = albumRepository.findById(id);
        entity.ifPresent(albumEntity -> idAlbumCache.put(id, new CacheEntry(albumEntity)));
        System.out.printf("log: " + logCounter++ + " - For id %d was found? %s!%n", id, entity.isPresent() ? "Yes" : "No");
        return entity;
    }

    public void resetCacheId (Long id) {
        idAlbumCache.remove(id);
    }
    private record CacheEntry (Instant modified, Album album){

        public CacheEntry(Album album) {
            this(Instant.now(), album);
        }
    }
}
