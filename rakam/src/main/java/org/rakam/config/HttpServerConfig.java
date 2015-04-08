package org.rakam.config;

import io.airlift.configuration.Config;
import org.rakam.util.HostAddress;

/**
 * Created by buremba <Burak Emre Kabakcı> on 14/02/15 22:46.
 */
public class HttpServerConfig {
    private static final int RAKAM_DEFAULT_PORT = 9999;
    private static final String RAKAM_DEFAULT_HOST = "0.0.0.0";

    private HostAddress address;
    private boolean disabled;

    @Config("http.server.address")
    public HttpServerConfig setAddress(String address) {
        if(address == null)
            this.address = HostAddress.fromParts(RAKAM_DEFAULT_HOST, RAKAM_DEFAULT_PORT);
        else
            this.address = HostAddress.fromString(address).withDefaultPort(RAKAM_DEFAULT_PORT);
        return this;
    }

    public HostAddress getAddress() {
        return address;
    }

    @Config("http.server.disabled")
    public HttpServerConfig setDisabled(boolean disabled) {
        this.disabled = disabled;
        return this;
    }

    public boolean getDisabled() {
        return disabled;
    }
}
