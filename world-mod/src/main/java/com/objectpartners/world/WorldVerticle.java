package com.objectpartners.world;


import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.platform.Verticle;

public class WorldVerticle extends Verticle {

  public void start() {

    vertx.eventBus().registerHandler("world-address", new Handler<Message<String>>() {

        @Override
      public void handle(Message<String> message) {
        message.reply("world!");
      }
    });
  }
}
