package edu.sjsu.cmpe.cache.client;

/**
 * Created by Vaidehi on 5/08/15.
 */

import edu.sjsu.cmpe.cache.client.consistentcache.ConsistentHash;

import java.util.ArrayList;

public class ConsistentCacheClient {

    public static void main(String[] args) throws Exception {
        System.out.println("\nClient: Consistent Cache\n");


        String cache1 = "http://localhost:3000";
        String cache2 = "http://localhost:3001";
        String cache3 = "http://localhost:3002";


        ArrayList collection = new ArrayList();
        collection.add(cache1);
        collection.add(cache2);
        collection.add(cache3);



        System.out.println("\nAdd to cash\n");



        ConsistentHash consistentHash = new ConsistentHash(collection);

        for (int i = 1; i <= 10; i++) {

            add_Cache(i, String.valueOf((char) (i + 96)), consistentHash);
        }
        System.out.println("\nGet from cash\n");

        for (int i = 1; i <= 10; i++) {
            String value = (String) get_Cache(i, consistentHash);

        }

        System.out.println("\nEnd\n");
    }

    public static Object get_Cache(int key, ConsistentHash consistentHash) {
        String url = (String) consistentHash.getCache(key);
        CacheServiceInterface cacheInterface = new DistributedCacheService(url);
        String value = cacheInterface.get(key);
        System.out.println("Retrieve: Key: " + key + " Value: " + value);
        return value;
    }

    public static void add_Cache(int key, String value, ConsistentHash consistentHash) {

        String url = (String) consistentHash.getCache(key);
        CacheServiceInterface cacheInterface = new DistributedCacheService(url);
        cacheInterface.put(key, value);
        System.out.println("Add to Cache: Key: " + key + " ,Value: " + value);
    }

}
