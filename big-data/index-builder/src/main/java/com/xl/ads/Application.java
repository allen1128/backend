package com.xl.ads;

import com.rabbitmq.client.*;
import com.xl.ads.domain.Ad;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Application {
    private final static String IN_QUEUE_NAME = "q_product";

    public static void main(String args[]) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel inChannel = connection.createChannel();
        inChannel.queueDeclare(IN_QUEUE_NAME, true, false, false, null);
        inChannel.basicQos(10); //per consumer limit
        System.out.println("Waiting for messages. To exist press CTRL+C");
        Consumer consumer = new DefaultConsumer(inChannel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException

            {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" +envelope.getRoutingKey() + ":" + message + "'");
                String[] msgList = message.split(",");
                Ad ad = new Ad();
                ad.adId = Long.valueOf(msgList[0]);
                ad.campaignId = Long.valueOf(msgList[1]);
                //ad.keyWords = msgList[3];
                ad.pClick = Double.valueOf(msgList[4]);
                ad.bidPrice = Double.valueOf(msgList[5]);
                ad.rankScore = Double.valueOf(msgList[6]);
                ad.qualityScore = Double.valueOf(msgList[7]);
                ad.costPerClick = Integer.valueOf(msgList[8]);
                ad.position = Integer.valueOf(msgList[9]);
                ad.title = msgList[10];
                ad.price = Integer.valueOf(msgList[11]);
                ad.thumbnail = msgList[12];
                ad.description = msgList[13];
                ad.brand = msgList[14];
                ad.detail_url = msgList[15];
                ad.query = msgList[16];
                ad.category = msgList[18];

                IndexBuilder indexBuilder = new IndexBuilder();
                indexBuilder.buildInvertIndex(ad);
            }
        };
        inChannel.basicConsume(IN_QUEUE_NAME, true, consumer);
    }
}
