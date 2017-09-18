package com.xl.ads;

import com.xl.ads.domain.Ad;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.FailureMode;
import net.spy.memcached.MemcachedClient;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IndexBuilder {
    private int EXP = 0; //0: never expire

    private String mMemcachedServer = "127.0.0.1";
    private int mMemcachedPortal = 11211;
    private String mysql_host = "127.0.0.1:3306";
    private String mysql_db = "searchads";
    private String mysql_user = "root";
    private String mysql_pass = "";

    private MySQLAccess mysql;
    private MemcachedClient cache;


    public void Close() {
        if (mysql != null) {
            try {
                mysql.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public IndexBuilder() {
        mysql = new MySQLAccess(mysql_host, mysql_db, mysql_user, mysql_pass);
        String address = mMemcachedServer + ":" + mMemcachedPortal;
        try {
            cache = new MemcachedClient(new ConnectionFactoryBuilder().setDaemon(true).setFailureMode(FailureMode.Retry).build(), AddrUtil.getAddresses(address));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Boolean buildInvertIndex(Ad ad) {
        String keyWords = Utility.strJoin(ad.keyWords, ",");
        List<String> tokens = Utility.cleanedTokenize(keyWords);
        for (int i = 0; i < tokens.size(); i++) {
            String key = tokens.get(i);
            if (cache.get(key) instanceof Set) {
                @SuppressWarnings("unchecked")
                Set<Long> adIdList = (Set<Long>) cache.get(key);
                adIdList.add(ad.adId);
                cache.set(key, EXP, adIdList);
            } else {
                Set<Long> adIdList = new HashSet<Long>();
                adIdList.add(ad.adId);
                cache.set(key, EXP, adIdList);
            }
        }
        return true;
    }

    public Boolean buildForwardIndex(Ad ad) {
        try {
            mysql.addAdData(ad);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
