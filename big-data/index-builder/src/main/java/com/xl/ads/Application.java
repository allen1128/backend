package com.xl.ads;

import com.rabbitmq.client.*;
import com.xl.ads.domain.Ad;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

public class Application {

    private final static String IN_QUEUE_NAME = "q_product";

    public static void main(String args[]) throws IOException, TimeoutException {

        final IndexBuilder indexBuilder = new IndexBuilder();
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel inChannel = connection.createChannel();
        inChannel.queueDeclare(IN_QUEUE_NAME, true, false, false, null);
        inChannel.basicQos(10); //per consumer limit
        System.out.println("Waiting for messages. To exist press CTRL+C");
        Consumer consumer = new DefaultConsumer(inChannel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + envelope.getRoutingKey() + ":" + message + "'");
                JSONObject adJson = new JSONObject(message);
                Ad ad = new Ad();

                if (adJson.isNull("adId") || adJson.isNull("campaignId")) {
                    return;
                }

                ad.adId = adJson.getLong("adId");
                ad.campaignId = adJson.getLong("campaignId");
                ad.brand = adJson.isNull("brand") ? "" : adJson.getString("brand");
                ad.price = adJson.isNull("price") ? 100.0 : adJson.getDouble("price");
                ad.thumbnail = adJson.isNull("thumbnail") ? "" : adJson.getString("thumbnail");
                ad.title = adJson.isNull("title") ? "" : adJson.getString("title");
                ad.detail_url = adJson.isNull("detail_url") ? "" : adJson.getString("detail_url");
                ad.bidPrice = adJson.isNull("bidPrice") ? 1.0 : adJson.getDouble("bidPrice");
                ad.pClick = adJson.isNull("pClick") ? 0.0 : adJson.getDouble("pClick");
                ad.category = adJson.isNull("category") ? "" : adJson.getString("category");
                ad.description = adJson.isNull("description") ? "" : adJson.getString("description");
                ad.keyWords = new ArrayList<String>();
                JSONArray keyWords = adJson.isNull("keyWords") ? null : adJson.getJSONArray("keyWords");

                for (int j = 0; j < keyWords.length(); j++) {
                    ad.keyWords.add(keyWords.getString(j));
                }
                if (!indexBuilder.buildInvertIndex(ad) || (!indexBuilder.buildForwardIndex(ad))) {
                    System.out.println("error saving " + ad.toString());
                }
            }
        };
        inChannel.basicConsume(IN_QUEUE_NAME, true, consumer);
    }
}
