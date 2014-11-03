package com.objectpartners.hello;


import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.platform.Verticle;

public class HelloVerticle extends Verticle {

    public void start() {

        container.deployModule("com.objectpartners~world-mod~0.1", new Handler<AsyncResult<String>>() {
            @Override
            public void handle(AsyncResult<String> event) {
                if (event.succeeded()) {
                    container.logger().info("successfully deployed the world module...");

                    vertx.eventBus().send("world-address", "Hello", new Handler<Message<String>>() {
                        @Override
                        public void handle(Message<String> event) {
                            container.logger().info("Received message: " + event.body());
                        }
                    });
                } else {
                    container.logger().info("world module did not deploy...");
                    event.cause().printStackTrace();
                }
            }
        });
    }
}
