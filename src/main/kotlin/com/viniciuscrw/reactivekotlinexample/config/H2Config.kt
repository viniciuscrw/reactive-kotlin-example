package com.viniciuscrw.reactivekotlinexample.config

import java.sql.SQLException
import org.h2.server.TcpServer
import org.h2.tools.Server
import org.springframework.context.event.ContextClosedEvent
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class H2Config(
    private var webServer: Server? = null,
    private var tcpServer: Server? = null
) {
    @EventListener(ContextRefreshedEvent::class)
    @Throws(SQLException::class)
    fun start() {
        webServer = Server.createWebServer("-webPort", "8082", "-tcpAllowOthers")
        tcpServer = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers")
    }

    @EventListener(ContextClosedEvent::class)
    fun stop() {
        webServer?.stop()
        tcpServer?.stop()
    }
}